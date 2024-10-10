package handy

import org.springframework.http.HttpStatus


class PedidoController {

    PedidoService pedidoService

    def getOrdersFromHandy(){


        try {
            def response = pedidoService.getOrdersFromHandy()
            render( response, status: HttpStatus.OK)
        }catch (Exception e){
            render(e.message, status: 500)
        }
    }
}