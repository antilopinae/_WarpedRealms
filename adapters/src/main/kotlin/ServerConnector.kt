package adapters

import adapters.grpc.GRpcLayer
import adapters.grpc.client.GRpcClient
import adapters.grpc.client.dao.RequestMessage
import adapters.grpc.client.dao.ResponseMessage
import adapters.rest.dao.token.TokenResponse
import io.ktor.client.plugins.*

//Подключение к серверу, передача событий.
class ServerConnector() {
    val GRpcLayer = GRpcLayer()
    init {
        println("==========Server To Connect==========")
    }
    val token: String = "ajajajajajjajajajajajajaj"

//    fun setToken(authorizationWindow: AuthorizationWindow) {
//        if (authorizationWindow.token is TokenResponse) {
//            this.token = (authorizationWindow.token as TokenResponse).token
//        }
//    }

    fun sendRequest(p: RequestMessage) {
        p.token =token
        GRpcLayer.sendRequest(p)
    }

    fun getResponse(): ResponseMessage? {
        return GRpcLayer.getResponse()
    }

    fun dispose() {
        GRpcLayer.stopConnection()
    }
}
