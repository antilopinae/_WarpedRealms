package warped.realms.system.update.mapper

import Update
import System
import adapters.ServerConnector
import adapters.grpc.client.dao.RequestMessage
import warped.realms.entity.mapper.EntityMapper

import java.util.*


@System
@Update(12)
class ServerMapperSystem {
    private val entityMappers = mutableListOf<EntityMapper>()
    lateinit var serverConnector: ServerConnector

    val byteArray0 = arrayOf((0).toByte()).toByteArray()
    val byteArray1 = arrayOf((1).toByte()).toByteArray()
    val byteArray2 = arrayOf((2).toByte()).toByteArray()

    fun Update(deltaTime: Float) {
        entityMappers.forEach { it.Update() }
        serverConnector.sendRequest(entityMappers.push())
    }
    fun PutComponent(cmp: EntityMapper) {
        entityMappers.add(cmp)
    }

    fun Dispose() {
        entityMappers.clear()
    }

    fun Float.toByteArray() = if (this>0) byteArray2 else if(this<0) byteArray0 else byteArray1

    fun MutableList<EntityMapper>.push(): RequestMessage {
        val player = this.first()
        return RequestMessage(
            player.moveMapper.mapperCmp.cos.toByteArray(),
            player.moveMapper.mapperCmp.sin.toByteArray(),
            byteArray1,
            byteArray0,
            byteArray0
        )
    }
}
