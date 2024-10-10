package handy

import org.springframework.http.HttpStatus


class PedidoController {

    PedidoService pedidoService
    SyncOrderService syncOrderService

    def getOrdersFromHandy(){

        try {
            def response = pedidoService.getOrdersFromHandy()
            render( response, status: HttpStatus.OK)
        }catch (Exception e){
            render(e.message, status: 500)
        }
    }

    def ordersLocal(){
        def response = syncOrderService.syncOrders()

        def orders = Pedido.list()

        respond orders
    }
}