package warped.realms.system.update.mapper

import Update
import System
import adapters.ServerConnector
import warped.realms.entity.mapper.EntityMapper

@System
@Update(9)
class ServerDismapperSystem {
    private val entityMappers = mutableListOf<EntityMapper>()
    lateinit var serverConnector: ServerConnector
    fun PutComponent(cmp: EntityMapper) {
        entityMappers.add(cmp)
    }
    fun Update(delta: Float) {
        val response = serverConnector.getResponse()

        entityMappers.first().dismapEntity(ResponseDismapper(response.positions.first()))
    }
    fun Dispose() {
        entityMappers.clear()
    }
}
