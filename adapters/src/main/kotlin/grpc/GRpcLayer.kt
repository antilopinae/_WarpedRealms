package adapters.grpc

import adapters.grpc.client.GRpcClient
import adapters.grpc.client.dao.RequestMessage
import adapters.grpc.client.dao.ResponseMessage
import com.grpc.*
import java.util.concurrent.ConcurrentLinkedQueue

class GRpcLayer {
    val client = GRpcClient()

    val request_messages: ConcurrentLinkedQueue<HelloRequest> = client.request_messages

    val token: String = "ajksakjdajksdjkdsjksdgfdfsdfgsdgfgddgdfgfgdf"
//    val byteArray1 = byteArrayOf(1).toByteString()
    fun sendRequest(clientPac: RequestMessage){
        val sendPack = client.mapRequest(clientPac)
        request_messages.add(sendPack)
        client.sendMessage()
    }
    fun getResponse(): ResponseMessage? {
        val getPack = client.getMessage() ?: return null
        println("SERVER NOT NULL")
        return client.unmapResponse(getPack)
    }
    fun stopConnection(){
        println("Stop connection")
        TODO()
    }
}
