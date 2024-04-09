package warped.realms.entity.mapper

import warped.realms.component.MoveComponent
import warped.realms.component.PhysicComponent
import warped.realms.component.TransformComponent
import warped.realms.entity.GameEntity
import warped.realms.component.mapper.MoveMapper
import warped.realms.component.mapper.PhysicMapper
import warped.realms.component.mapper.PhysicMapper.Companion.dismap
import warped.realms.component.mapper.TransformMapper
import warped.realms.component.mapper.TransformMapper.Companion.dismap
import warped.realms.system.update.mapper.ResponseDismapper

class EntityMapper(
    var entity: GameEntity
) {
    val moveMapper = MoveMapper(entity.getCmp<MoveComponent>())
    val transMapper = TransformMapper(entity.getCmp<TransformComponent>())
    val physMapper = PhysicMapper(entity.getCmp<PhysicComponent>())

    fun Update() {
        moveMapper.Update()
        transMapper.Update()
        physMapper.Update()
    }

//    fun mapEntity(): ByteArray {
//        val p1 = moveMapper.mapperCmp.map()
//        val p2 = transMapper.mapperCmp.map()
//        return p1 + p2
//    }

    fun dismapEntity(p: ResponseDismapper) {
//        moveMapper.mapperCmp.dismap(p.copyOfRange(0, MoveMapper.SIZE_ARRAY))
//        transMapper.trackerCmp.dismap(p.position)
        physMapper.trackerCmp.dismap(p.position)
    }
}
