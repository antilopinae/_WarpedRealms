package warped.realms.system.update

import PutComponent
import Update
import System
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.World
import ktx.box2d.query
import warped.realms.component.AttackComponent
import warped.realms.component.AttackState
import warped.realms.component.ImageComponent
import warped.realms.component.PhysicComponent
import warped.realms.entity.Entity
import warped.realms.system.SpawnSystem.Companion.HIT_BOX_SENSOR

@System
@Update(3)
@PutComponent(AttackComponent::class)
class AttackSystem {
    private val attackCmps: MutableList<AttackComponent> = mutableListOf()
//    private val physicCmps: MutableList<PhysicComponent> = mutableListOf()
//    private val imgCmps: MutableList<ImageComponent> = mutableListOf()
//    private lateinit var phWorld: World

    fun Update(deltaTime: Float){
//        attackCmps.forEach { attackCmp ->
//            if(attackCmp.isReady && !attackCmp.doAttack)
//            {
//                // entity does not want to attack and is not executing an attack -> do nothing
//                return@forEach
//            }
//            if(attackCmp.isPrepared && attackCmp.doAttack)
//            {
//                // attack intention and is ready to attack  -> start attack
//                attackCmp.doAttack = false
//                attackCmp.state = AttackState.ATTACKING
//                attackCmp.delay = attackCmp.maxDelay
//                return@forEach
//            }
//
//            attackCmp.delay -= deltaTime
//
//            if(attackCmp.delay <= 0f && attackCmp.isAttacking)
//            {
//                //deal damage to nearby enemies
//                attackCmp.state = AttackState.DEAL_DAMAGE
////                val image = imgCmps[entity].image
////                val physCmp = phCmps[entity]
////                val attackLeft = image.flipX
////                val (x,y) = physCmp.body.position
////                val (offX, offY) = physCmp.offset
////                val (w, h) = physCmp.size
////                val halfW = w * 0.5f
////                val halfH = h * 0.5f
//
//                if(attackLeft)
//                {
//                    AABB_RECT.set(
//                        x + offX - halfW - attackCmp.extraRange,
//                        y + offY - halfH,
//                        x + offX + halfW,
//                        y + offY + halfH
//                    )
//                }
//                else{
//                    AABB_RECT.set(
//                        x + offX - halfW,
//                        y + offY - halfH,
//                        x + offX + halfW + attackCmp.extraRange,
//                        y + offY + halfH
//                    )
//                }
//                phWorld.query(AABB_RECT.x, AABB_RECT.y, AABB_RECT.width, AABB_RECT.height) { fixture: Fixture ->
//                    if(fixture.userData != HIT_BOX_SENSOR){
//                        return@query false
//                    }
//
//                    val fixtureEntity = fixture.entity
//
//                    if(fixtureEntity == entity)
//                    {
//                        // we don't want to attack ourselves!
//                        return@query true
//                    }
//
//                    configureEntity(fixtureEntity){
//                        lifeCmps.getOrNull(it)?.let{ lifeCmp ->
//                            lifeCmp.takeDamage += attackCmp.damage * MathUtils.random(0.9f, 1.1f)
//                        }
//                    }
//                    return@query true
//                }
//
//                attackCmp.state = AttackState.READY
//            }
//        }
    }
    private val Fixture.entity: Entity
        get() = this.body.userData as Entity
    fun PutComponent(attackCmp: AttackComponent) {
        attackCmps.add(attackCmp)
    }
    fun DeleteComponent(attackCmp: AttackComponent) {
        attackCmps.remove(attackCmp)
    }
    fun Dispose() {
        attackCmps.clear()
    }
    companion object{
//        val AABB_RECT = Rectangle()
    }
}
