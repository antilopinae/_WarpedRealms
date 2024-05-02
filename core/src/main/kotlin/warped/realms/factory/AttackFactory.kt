package warped.realms.factory

import Factory
import warped.realms.component.AttackComponent

@Factory(AttackComponent::class)
class AttackFactory {
    fun Factory(lambda: AttackComponent.() -> AttackComponent): AttackComponent {
        return AttackComponent().lambda()
    }
    fun Delete(cmp: AttackComponent) {

    }
}
