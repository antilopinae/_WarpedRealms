package adapters.grpc.client

import com.grpc.HelloRequest
import com.grpc.HelloResponse
import io.grpc.stub.StreamObserver
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicInteger

class GRpcService: StreamObserver<HelloResponse> {
    val messages: ConcurrentLinkedQueue<HelloResponse> = ConcurrentLinkedQueue()
    override fun onNext(p0: HelloResponse?) {
        messages.add(p0!!)
        println(p0)
    }
    override fun onCompleted() {
        //nothing
    }
    override fun onError(p0: Throwable?) {
        p0!!.printStackTrace()
    }
}
