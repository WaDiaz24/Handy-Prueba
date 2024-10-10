package handy

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.scheduling.annotation.Scheduled

@Slf4j
@Transactional
class SyncOrderService {

    PedidoService pedidoService

    @Scheduled(fixedDelay = 10000L)
    def syncOrders(){
        def orderJson = pedidoService.getOrdersFromHandy()
        log.info("Ordenes obtenidas de Handy: ${orderJson}")

        def ordersList = new JsonSlurper().parseText(orderJson).salesOrders
        log.info(log.info("Ordenes obtenidas de Handy en lista: ${ordersList}"))

        def result = pedidoService.saveOrdersToDB(ordersList as List)
        println("Resultado"+result)

        pedidoService.deleteOrders(ordersList as List)
    }
}