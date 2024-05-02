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
@Update(4)
@PutComponent(LifeComponent::class) //NoneOf(DeadComponent::class)
class LifeSystem {
    private val lifeCmps: MutableList<LifeComponent> = mutableListOf()
//    private val deadCmps: MutableList<DeadComponent> = mutableListOf()
//    private val playerCmps: MutableList<Entity> = mutableListOf()

    fun Update(deltaTime: Float) {
//        lifeCmps.forEach { lifeComponent ->
//            lifeComponent.life = (lifeComponent.life + lifeComponent.regeneration * deltaTime).coerceAtMost(lifeComponent.max)
//
//            if(lifeComponent.takeDamage > 0f)
//            {
//                lifeComponent.life -= lifeComponent.takeDamage
//                lifeComponent.takeDamage = 0f
//            }
//
//            if(lifeComponent.isDead)
//            {
//                configureEntity(entity){
//                    deadCmps.add(it){
//                        if(it in playerCmps){
//                            reviveTime = 7f
//                        }
//                    }
//                }
//            }
//        }
    }
    fun PutComponent(lifeCmp: LifeComponent) {

    }
    fun DeleteComponent(lifeCmp: LifeComponent) {

    }
    fun Dispose() {

    }
    companion object{

    }
}
