package warped.realms.factory

import Factory
import warped.realms.actor.FlipImage
import warped.realms.component.ImageComponent

@Factory(ImageComponent::class)
class ImageFactory {
    fun Factory(lambda: ImageComponent.() -> ImageComponent): ImageComponent {
        return ImageComponent(FlipImage()).lambda()
    }

    fun Delete(cmp: ImageComponent) {

    }
}
