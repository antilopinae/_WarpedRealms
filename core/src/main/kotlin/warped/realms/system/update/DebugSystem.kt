package warped.realms.system.update

import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import ktx.assets.disposeSafely
import System
import Update
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import ktx.graphics.use

@System
//@Update(-1)
class DebugSystem {
    private val phWorld: World = PhysicSystem.phWorld
    private val stage: Stage = RenderSystem.stage

    private val physicRenderer: Box2DDebugRenderer = Box2DDebugRenderer()
    private val shapeRenderer: ShapeRenderer = ShapeRenderer()

    fun Update(deltaTime: Float) {
        physicRenderer.render(phWorld, stage.camera.combined)
        shapeRenderer.use(ShapeRenderer.ShapeType.Line, stage.camera.combined){
            it.setColor(1f, 0f, 0f, 0f)
//            it.rect(AABB_RECT.x, AABB_RECT.y, AABB_RECT.width - AABB_RECT.x, AABB_RECT.height - AABB_RECT.y)
        }
    }

    fun Dispose() {
        println("[DISPOSE] ${this::class.simpleName}")
        physicRenderer.disposeSafely()
        shapeRenderer.disposeSafely()
    }
}
