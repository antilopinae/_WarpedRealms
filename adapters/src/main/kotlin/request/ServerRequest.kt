package adapters.request

import ktx.log.logger
import adapters.getter.GetterRequest
import adapters.getter.IGetRequest
import adapters.setter.ISetRequest
import adapters.setter.SetterRequest
import adapters.queue.ServerQueue
import java.util.concurrent.locks.ReentrantLock

class ServerRequest() {
    val serverQueue: ServerQueue = ServerQueue()
    val clientQueue: ServerQueue = ServerQueue()

    //lateinit var serverBuilder: ServerBuilder

//    val client = HttpClient{
//        install(WebSockets)
//    }

    private val setterRequest: ISetRequest = SetterRequest(clientQueue)
    private val getterRequest: IGetRequest = GetterRequest(serverQueue)

    val lock = ReentrantLock()

    val got = Thread {
///
    }
    val send = Thread {

//        runBlocking{
//            client.webSocket(HttpMethod.Get, host = "127.0.0.1", port = 8000, path = "/gate") {
//                var cord = 0
//                while (lock.isLocked) {
//                    val message = "[CLIENT]-Try Get $cord + ${java.time.LocalTime.now()}"
//                    send(message)
//                    cord++
//                    delay(1000L)
//                }
//            }
//        }
    }

    fun startConnection() {
        lock.lock()

        got.start()
        send.start()
    }

    fun endConnection() {
        lock.unlock()
        serverQueue.stackEmptyCondition.signalAll()
        got.join()
        clientQueue.stackFullCondition.signalAll()
        send.join()
    }
    fun dispose() {
        endConnection()
    }

    companion object {
        val log = logger<ServerRequest>()
    }
}
