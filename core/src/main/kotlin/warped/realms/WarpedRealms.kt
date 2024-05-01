package warped.realms

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import ktx.app.KtxGame
import ktx.app.KtxScreen
import adapters.ServerConnector
import warped.realms.screen.EnumScreen
import warped.realms.screen.KeeperGame
import warped.realms.screen.ScreenManager

//Игровая логика, работа с server_connector
class WarpedRealms : KtxGame<KtxScreen>() {
    //private val camera: OrthographicCamera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    val serverConnector = ServerConnector()

    val keeperGame = KeeperGame.apply { this.game = this@WarpedRealms }

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        ScreenManager.getInstance().init(this)
//        ScreenManager.getInstance().show(EnumScreen.AUTHORIZATION_SCREEN)
        ScreenManager.getInstance().show(EnumScreen.GAME)
//        ScreenManager.getInstance().hide(EnumScreen.GAME)
    }
    override fun dispose() {
        serverConnector.dispose()
        ScreenManager.getInstance().dispose()
        super.dispose()
    }
}
