package handy

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Slf4j
@Transactional
@EnableScheduling
class SyncOrderService {

    PedidoService pedidoService

    @Scheduled(fixedDelay = 600000L)
    def syncOrders(){
        def orderJson = pedidoService.getOrdersFromHandy()
        log.info("Ordenes obtenidas de Handy: ${orderJson}")

        def ordersList = new JsonSlurper().parseText(orderJson).salesOrders
        log.info("Ordenes obtenidas de Handy en lista: ${ordersList}")

        def result = pedidoService.saveOrdersToDB(ordersList as List)
        log.info("Ordenes guardadas en la base de datos local: ${result}")

        def deleteOrdersResult = pedidoService.deleteOrders(ordersList as List)
        log.info("Ordenes eliminadas en la base de datos local: ${deleteOrdersResult}")
    }
}