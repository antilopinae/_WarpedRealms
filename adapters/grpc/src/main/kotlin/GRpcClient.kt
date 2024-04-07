package adapters.grpc.client

import com.grpc.GreetingServiceGrpc
import com.grpc.GreetingServiceOuterClass
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

class GRpcClient {
    init{
        println("GRpc client started")

    }
    val channel: ManagedChannel =
        ManagedChannelBuilder.forTarget("localhost:8080")
        .usePlaintext()
        .build()
    val stub: GreetingServiceGrpc.GreetingServiceBlockingStub =
        GreetingServiceGrpc.newBlockingStub(channel)
    fun sendMessage(){
        val request = GreetingServiceOuterClass.HelloRequest
            .newBuilder()
            .setName("Client1")
            .build()
        val response: GreetingServiceOuterClass.HelloResponse = stub.greeting(request)
        println("$response")
        channel.shutdownNow()
    }
}
