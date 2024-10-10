package handy

import grails.gorm.transactions.Transactional
import groovy.util.logging.Slf4j
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

@Slf4j
@Transactional
class PedidoService {

    def getOrdersFromHandy() {
        def apiUrl = "https://hub.handy.la/api/v2/salesOrder"
        def apiToken = "Bearer sbujadsavf5fr7hq0asjv0kp97uiie6c"

        OkHttpClient client = new OkHttpClient()

        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("Authorization", apiToken)
                .build()

        Response response = client.newCall(request).execute()

        if(response.isSuccessful()){
            String responseBody = response.body().string()
            log.info("Datos de pedidos: ${responseBody}")
            return responseBody
        }else{
            log.error("Error en la solicitud: ${response.code()} -- ${response.message()}")
            return "ERROR en la solicitud: ${response.message()}"
        }
    }

    def saveOrdersToDB(List orders){
        orders.each {orderData ->

            def product = orderData.items?.get(0)?.product // data product
            def customer = orderData.customer

            new Pedido(
                  id_order: orderData.id as Long,
                  id_user: orderData.id as Long,
                    descriptionCustomer: customer.description,
                    productCode: product.code,
                    totalSales: product.price * product.quantity
            ).save(flush: true)

        }
    }

    def deleteOrders(List ordersFromApi){
        def apiOrders = ordersFromApi.collect{
            it.id as Long
        }

        def savedOrders = Pedido.findAll()

        savedOrders.each {savedOrder ->
            if(!apiOrders.contains(savedOrder.id_order)){
                log.info("Eliminando orden ${savedOrder.id_order}")
                savedOrder.delete(flush: true)
            }
        }
    }
}