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

    fun Float._toInt() = if (this>0) 1 else if(this<0) -1 else 0

    fun MutableList<EntityMapper>.push(): RequestMessage {
        val player = this.first()
        return RequestMessage(
            player.moveMapper.mapperCmp.cos._toInt(),
            player.moveMapper.mapperCmp.sin._toInt(),
            0,
            0,
            0
        )
    }
}
