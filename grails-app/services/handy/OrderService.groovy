package handy

import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

@Slf4j
@Transactional
class OrderService {

    def getOrdersFromHandy() {
        def apiUrl = "https://hub.handy.la/api/v2/salesOrder"
        String apiToken = System.getProperty('API_TOKEN')

        OkHttpClient client = new OkHttpClient()

        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("Authorization", "Bearer ${apiToken}")
                .build()

        Response response = client.newCall(request).execute()

        if(response.isSuccessful()){
            log.info("Datos de pedidos obtenidos con éxito")
            return response.body().string()
        }else{
            log.error("Error en la solicitud: ${response.code()} -- ${response.message()}")
        }
    }

    def saveOrdersToDB(List orders){
        orders.each {orderData ->

            def product = orderData.items?.get(0)?.product // data product
            def customer = orderData.customer

            Order order = new Order(
                  id_order: orderData.id,
                  id_user: customer.id,
                    descriptionCustomer: customer.description,
                    productCode: product.code,
                    totalSales: product.price * product.quantity
            )
            if(order.save(flush: true)){
                log.info("Pedido agregado en la base de datos local con el ID: ${order?.id}")
            }
        }
    }

    def deleteOrders(List ordersFromApi){
        int deletedCount = 0

        def apiOrdersIds = ordersFromApi.collect{
            it.id as Long
        }

        def savedOrders = Order.findAllById_orderNotInList(apiOrdersIds)

        savedOrders.each { orderToDelete ->
            log.info("Eliminando orden ${orderToDelete.id_order}")
            orderToDelete.delete(flush: true)
            deletedCount++
        }
        return deletedCount
    }
}