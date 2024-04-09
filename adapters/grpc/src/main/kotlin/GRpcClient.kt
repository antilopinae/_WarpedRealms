package adapters.grpc.client

import adapters.grpc.client.dao.Position
import adapters.grpc.client.dao.RequestMessage
import adapters.grpc.client.dao.ResponseMessage
import com.google.protobuf.ByteString
import com.google.protobuf.kotlin.toByteString
import com.grpc.*
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver
import io.ktor.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock

class GRpcClient {
    val channel: ManagedChannel =
        ManagedChannelBuilder.forTarget("localhost:8000")
            .executor(Executors.newCachedThreadPool())
            .usePlaintext()
            .build()
    val stub: GreetingServiceGrpc.GreetingServiceStub =
        GreetingServiceGrpc.newStub(channel)

    val service = GRpcService()
    val toServer = stub.greeting(service)

    val response_messages: ConcurrentLinkedQueue<HelloResponse> = service.messages
    val request_messages: ConcurrentLinkedQueue<HelloRequest> = ConcurrentLinkedQueue()

    val request = HelloRequest.newBuilder()

    val lock: ReentrantLock = ReentrantLock()

    val send = Thread{
        for(i in 0..1000){
            lock.lock()
            lock.unlock()

            val request = request_messages.poll()
            toServer.onNext(request)
        }
    }

    init{
        println("GRpc client started")
    }
    fun sendMessage(){
        val request = request_messages.poll()
        toServer.onNext(request)
    }
    fun getMessage(): HelloResponse {
        if(response_messages.size!=0)
            return response_messages.peek()
        else return HelloResponse.newBuilder()
            .setToken("")
            .addPositions(
                PlayerPosition.newBuilder()
                    .setPositionX(1000)
                    .setPositionY(100)
                    .build()
            )
            .build()
    }
    fun mapRequest(packClient: RequestMessage): HelloRequest{
        return request
            .setToken(packClient.token)
            .setMessage(
                InputMessage.newBuilder()
                    .setInput(
                        InputMap.newBuilder()
                            .setInputX(packClient.byteArray_x.toByteString())
                            .setInputY(packClient.byteArray_y.toByteString())
                            .setInputZ(packClient.byteArray_z.toByteString())
                            .setMouseX(packClient.byteArray_mouse_x.toByteString())
                            .setMouseY(packClient.byteArray_mouse_y.toByteString())
                    )
                    .addActions(Action.ATTACK)
                    .addActions(Action.ATTACK)
            )
            .build()
    }
    fun unmapResponse(packServer: HelloResponse): ResponseMessage {
        val response = ResponseMessage(
            packServer.token,
            packServer.positionsList.map { x -> Position(x.positionX, x.positionY) }
        )
        return response
    }
    fun stopSending(){
        channel.shutdownNow()
    }
//    fun sendMessage(){
//        val request = HelloRequest
//            .newBuilder()
//            .setName("Client1")
//            .build()
//        val response: GreetingServiceOuterClass.HelloResponse = stub.greeting(request) - not stream!
//
//        val response: Iterator<HelloResponse> = stub.greeting(request)
//        while(response.hasNext()){
//            println("${response.next()}")
//        }
//        channel.shutdownNow()
//    }
}
