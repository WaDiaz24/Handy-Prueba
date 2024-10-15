package handy

import grails.gorm.transactions.Transactional
import org.springframework.http.HttpStatus

@Transactional
class OrderController {

    SyncOrderService syncOrderService

    def ordersLocal() {
        try {
            def orders = Order.list()
            respond orders, status: HttpStatus.OK
        } catch (Exception e) {
            render(e.message, status: HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}