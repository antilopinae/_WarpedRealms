package warped.realms.factory

import Factory
import warped.realms.component.DeadComponent
import warped.realms.component.LifeComponent

@Factory(DeadComponent::class)
class DeadFactory {
    fun Factory(lambda: DeadComponent.() -> DeadComponent): DeadComponent {
        return DeadComponent().lambda()
    }
    fun Delete(cmp: DeadComponent) {

    }
}
