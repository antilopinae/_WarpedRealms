package warped.realms.actor

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Null
import com.badlogic.gdx.utils.Scaling

class FlipImage(): Image() {
    var flipX = false
    constructor (drawable: Drawable, scaling: Scaling, align: Int) : this() {
        this.setDrawable(drawable)
        this.setScaling(scaling)
        this.align = align
        this.setSize(prefWidth, prefHeight)
    }

    constructor (drawable: Drawable) : this(drawable, Scaling.stretch, Align.center)

    constructor (region: TextureRegion) : this(TextureRegionDrawable(region), Scaling.stretch, Align.center)


    override fun draw(batch: Batch, parentAlpha: Float) {
        validate()
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha)

        val toDraw = drawable

        if(toDraw is TransformDrawable && (scaleX != 1f || scaleY != 1f || rotation != 0f))
        {
            toDraw.draw(
                batch,
                if(flipX) x + imageX + imageWidth * scaleX else x + imageX,
                y + imageY,
                originX - imageX,
                originY - imageY,
                imageWidth,
                imageHeight,
                if(flipX) -scaleX else scaleX,
                scaleY,
                rotation
            )
        } else
        {
            toDraw?.draw(
                batch,
                if(flipX) x + imageX + imageWidth * scaleX else x + imageX,
                y + imageY,
                if(flipX) -imageWidth * scaleX else imageWidth * scaleX,
                imageHeight * scaleY
            )
        }
    }
}
