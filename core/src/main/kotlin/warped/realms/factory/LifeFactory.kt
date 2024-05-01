package warped.realms.factory

import Factory
import warped.realms.component.ImageComponent
import warped.realms.component.LifeComponent


@Factory(LifeComponent::class)
class LifeFactory {
    fun Factory(lambda: LifeComponent.() -> LifeComponent): LifeComponent {
        return LifeComponent().lambda()
    }
    fun Delete(cmp: LifeComponent) {

    }
}
