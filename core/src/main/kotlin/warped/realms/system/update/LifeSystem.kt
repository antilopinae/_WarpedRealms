package warped.realms.system.update

import PutComponent
import System
import Update
import warped.realms.component.LifeComponent

@System
@Update(10)
@PutComponent(LifeComponent::class) //NoneOf(DeadComponent::class)
class LifeSystem {
    fun Update(deltaTime: Float) {

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
