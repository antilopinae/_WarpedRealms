package adapters

import adapters.grpc.client.GRpcClient
import adapters.request.ServerRequest
import io.ktor.client.plugins.*

//Подключение к серверу, передача событий.
class ServerConnector() {
    private val serverRequest = ServerRequest()
    //private val server_builder = ServerBuilder().also { serverRequest.serverBuilder = it }
    val client = GRpcClient()
    init {
        println("==========Server To Connect==========")
        //serverRequest.startConnection()
        send()
    }
    fun send(){
        client.sendMessage()
    }
    fun push(p: ByteArray): Boolean {
        return true
    }

    fun pop(): ByteArray {
        val b = byteArrayOf(0, 0, 0, 0, 0)
        return b
    }

    fun dispose() {
        serverRequest.dispose()
    }
}
