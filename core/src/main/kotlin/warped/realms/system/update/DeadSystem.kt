package warped.realms.system.update

import PutComponent
import System
import Update
import warped.realms.component.DeadComponent
import warped.realms.component.LifeComponent
import warped.realms.component.MoveComponent
import warped.realms.component.PhysicComponent
import warped.realms.entity.Entity

@System
@Update(5)
@PutComponent(DeadComponent::class) //NoneOf(DeadComponent::class)
class DeadSystem {
    private val deadCmps: MutableList<DeadComponent> = mutableListOf()
//    private val lifeCmps: MutableList<LifeComponent> = mutableListOf()

    fun Update(deltaTime: Float){
//        deadCmps.forEach { deadCmp ->
//            if(deadCmp.reviveTime == 0f)
//            {
////                world.remove(entity)
//                return
//            }
//            deadCmp.reviveTime -= deltaTime
//
//            if(deadCmp.reviveTime <= 0f)
//            {
////                with(lifeCmps[entity]) {life = max}
////                configureEntity(entity) {deadCmps.remove(deadCmp)}
//            }
//        }
    }
    fun PutComponent(deadComponent: DeadComponent) {

    }
    fun DeleteComponent(deadComponent: DeadComponent) {

    }
    fun Dispose() {

    }
    companion object{

    }
}
