package adapters.grpc.client

import com.grpc.GreetingServiceGrpc
import com.grpc.GreetingServiceOuterClass
import com.grpc.HelloRequest
import com.grpc.HelloResponse
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver
import io.ktor.util.*
import java.util.concurrent.Executors

class GRpcClient {
    val messages: MutableList<String> = mutableListOf()
    val channel: ManagedChannel =
        ManagedChannelBuilder.forTarget("localhost:8000")
            .executor(Executors.newCachedThreadPool())
            .usePlaintext()
            .build()
    val stub: GreetingServiceGrpc.GreetingServiceStub =
        GreetingServiceGrpc.newStub(channel)
    val toServer = stub.greeting(
        object : StreamObserver<HelloResponse> {
            override fun onNext(p0: HelloResponse?) {
                messages.add(p0!!.greeting)
                println("${p0!!}")
            }
            override fun onCompleted() {
                //nothing
            }
            override fun onError(p0: Throwable?) {
                p0!!.printStackTrace()
            }
        }
    )
    init{
        println("GRpc client started")
    }
    fun sendMessage2(){
        val request = HelloRequest
            .newBuilder()
            .setName("Client1")
            .addHobbies("player")
            .addHobbies("admin")
            .putBagOfTricks("live coding", "not very good")
            .build()
        //val response: GreetingServiceOuterClass.HelloResponse = stub.greeting(request) - not stream!
        toServer.onNext(request)
//        channel.shutdownNow()
    }
    fun sendMessage(){
        val request = HelloRequest
            .newBuilder()
            .setName("Client1")
            .addHobbies("player")
            .addHobbies("admin")
            .putBagOfTricks("live coding", "not very good")
            .build()
        //val response: GreetingServiceOuterClass.HelloResponse = stub.greeting(request) - not stream!

//        val response: Iterator<HelloResponse> = stub.greeting(request)
//        while(response.hasNext()){
//            println("${response.next()}")
//        }
        channel.shutdownNow()
    }
}
