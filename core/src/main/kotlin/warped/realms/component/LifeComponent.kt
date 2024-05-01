package warped.realms.component

import Component

@Component
class LifeComponent(
    var life: Float = 30f,
    var max: Float = 30f,
    var regeneration: Float = 1f,
    var takeDamage: Float = 0f
)
{
    val isDead: Boolean
        get() = life <= 0f
}
