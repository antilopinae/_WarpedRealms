package adapters.grpc.client

import adapters.grpc.client.dao.Position
import adapters.grpc.client.dao.RequestMessage
import adapters.grpc.client.dao.ResponseMessage
import com.google.protobuf.ByteString
import com.google.protobuf.kotlin.toByteString
import com.grpc.*
import io.grpc.*
import io.grpc.internal.DnsNameResolver
import io.grpc.internal.DnsNameResolverProvider
import io.grpc.stub.StreamObserver
import io.ktor.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock
import io.grpc.internal.PickFirstLoadBalancerProvider

class GRpcClient {
    init{
        NameResolverRegistry.getDefaultRegistry().register(DnsNameResolverProvider())
        LoadBalancerRegistry.getDefaultRegistry().register(PickFirstLoadBalancerProvider())
        println("GRpc client started")
    }
    val channel: ManagedChannel =
        //localhost:8000
        ManagedChannelBuilder.forTarget("localhost:8000")
            .executor(Executors.newCachedThreadPool())
            .defaultLoadBalancingPolicy("pick_first")
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
        {
            val el = response_messages.poll()
            response_messages.clear()
            return el
        }
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
