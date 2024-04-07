package adapters

import adapters.grpc.client.GRpcClient
import adapters.request.ServerRequest

//Подключение к серверу, передача событий.
class ServerConnector() {
    private val serverRequest = ServerRequest()
    //private val server_builder = ServerBuilder().also { serverRequest.serverBuilder = it }

    init {
        println("==========Server To Connect==========")
        //serverRequest.startConnection()
        val server = GRpcClient()
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
