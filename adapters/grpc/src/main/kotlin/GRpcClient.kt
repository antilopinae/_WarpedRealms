package adapters.grpc.client

import adapters.grpc.client.dao.Position
import adapters.grpc.client.dao.RequestMessage
import adapters.grpc.client.dao.ResponseMessage
import com.google.protobuf.ByteString
import com.google.protobuf.kotlin.toByteString
import com.grpc.*
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.NameResolverRegistry
import io.grpc.internal.DnsNameResolverProvider
import io.grpc.stub.StreamObserver
import io.ktor.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock

class GRpcClient {
    init{
        NameResolverRegistry.getDefaultRegistry().register(DnsNameResolverProvider())
        println("GRpc client started")
    }
    val channel: ManagedChannel =
        //localhost:8000
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

    fun sendMessage(){
        val request = request_messages.poll()
        toServer.onNext(request)
    }
    fun getMessage(): HelloResponse? {
        if(response_messages.size!=0)
            return response_messages.poll()
        else return null
    }
    fun mapRequest(packClient: RequestMessage): HelloRequest{
        return request
            .setToken(packClient.token)
            .setMessage(
                InputMessage.newBuilder()
                    .setInput(
                        InputMap.newBuilder()
                            .setInputX(packClient.input_x)
                            .setInputY(packClient.input_y)
                            .setInputZ(packClient.input_z)
                            .setMouseX(packClient.input_mouse_x)
                            .setMouseY(packClient.input_mouse_y)
                    )
                    .addActions(Action.ATTACK)
                    .addActions(Action.ATTACK)
            )
            .build()
    }
    fun unmapResponse(packServer: HelloResponse): ResponseMessage {
        val response = ResponseMessage(
            packServer.token,
            packServer.positionsList.map { x -> x.player to  Position(x.positionX, x.positionY) }.toMap()
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
