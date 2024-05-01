package warped.realms.screen

import ktx.app.KtxScreen
import warped.realms.WarpedRealms

enum class EnumScreen
{
    AUTHORIZATION_SCREEN{
        override fun ScreenInstance(): KtxScreen {
            return AuthorizationScreen(KeeperGame.game)
        }
    },
    GAME {
        override fun ScreenInstance(): AScreen {
            return GameScreen(KeeperGame.game)
        }
    };
    protected abstract fun ScreenInstance(): KtxScreen
    val screenInstance: KtxScreen
        get() = this.ScreenInstance()
}
class KeeperGame{
    companion object{
        lateinit var game: WarpedRealms
    }
}
