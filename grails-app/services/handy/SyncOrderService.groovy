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

    OrderService orderService
    static final long syncInterval = 10000L // milisegundos

    @Scheduled(fixedDelay = syncInterval)
    def syncOrders(){
        def orderApiJson = orderService.getOrdersFromHandy()
        def ordersList = new JsonSlurper().parseText(orderApiJson).salesOrders as List
        log.info("${ordersList.size()} Ordenes obtenidas de Handy")

        orderService.saveOrdersToDB(ordersList)
        log.info("${Order.count()} Ordenes se encuentran guardadas en la base de datos local")

        def deleteOrdersResult = orderService.deleteOrders(ordersList)
        log.info("${deleteOrdersResult} Ordenes eliminadas en la base de datos local")
    }
}