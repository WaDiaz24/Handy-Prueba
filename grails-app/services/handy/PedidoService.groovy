package handy

import groovy.transform.CompileStatic
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response


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
}