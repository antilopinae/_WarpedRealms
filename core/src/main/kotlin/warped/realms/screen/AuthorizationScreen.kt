package warped.realms.screen

import adapters.rest.KtorClient
import adapters.rest.dao.Response
import adapters.rest.dao.SignType
import adapters.rest.dao.exception.ClientError
import adapters.rest.dao.token.TokenResponse
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.Align.center
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.actors.onClick
import warped.realms.WarpedRealms

class AuthorizationScreen(game: WarpedRealms): AScreen(game) {
    private val stage: Stage = Stage(
        FitViewport(640f, 480f)
    )

    private lateinit var skin: Skin
    private lateinit var sendButton: TextButton
    private lateinit var signInButton: TextButton
    private lateinit var signUpButton: TextButton
    private lateinit var backButton: TextButton
    private lateinit var closeButton: TextButton
    private lateinit var infoLabel: Label
    private lateinit var sendTable: Table
    private lateinit var chooseTable: Table
    private lateinit var textField1: TextField
    private lateinit var textField2: TextField
    private lateinit var signType: SignType
    lateinit var token: Response

    private fun sendEvent(): Response {
        sendButton.setText("Data sent")
        client.email = textField1.text
        client.password = textField2.text
        textField2.text = ""
        return try {
            client.httpConnect(signType, infoLabel)
        } catch (e: Exception) {
            infoLabel.color = Color.PURPLE
            infoLabel.setText("Connection rejected")
            infoLabel.isVisible = true
            return ClientError()
        }
    }

    override fun show() {
        super.show()

        skin = Skin(Gdx.files.internal("ui/uiskin.json"))

        sendButton = TextButton("Send", skin, "default").apply {
            pad(8f)
            center()
            onClick {
                token = sendEvent()
                if (token is TokenResponse) {
                    closeButton.isDisabled = true
                    sendButton.isDisabled = true
                    backButton.isDisabled = true
                    Gdx.app.exit()
                }
            }
        }

        closeButton = TextButton("Close", skin, "default").apply {
            pad(8f)
            x = (Gdx.graphics.width.toFloat() * 0.9).toFloat()
            y = (Gdx.graphics.height.toFloat() * 0.1).toFloat()
            onClick {
                Gdx.app.exit()
                //exitProcess(0)
            }
        }

        infoLabel = Label("default", skin, "default").apply {
            setAlignment(center)
            isVisible = false
        }

        val listener = object: ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                sendButton.setText("Send")
                infoLabel.isVisible = false
            }
        }

        signInButton = TextButton("Sign in", skin, "default").apply {
            pad(8f)
            center()
            onClick {
                chooseTable.isVisible = false
                sendTable.isVisible = true
                signType = SignType.SIGN_IN
            }
        }

        signUpButton = TextButton("Sign up", skin, "default").apply {
            pad(8f)
            center()
            onClick {
                chooseTable.isVisible = false
                sendTable.isVisible = true
                signType = SignType.SIGN_UP
            }
        }

        backButton = TextButton("Back", skin, "default").apply {
            pad(8f)
            center()
            onClick {
                sendTable.isVisible = false
                chooseTable.isVisible = true
                textField1.text = ""
                signType = SignType.DEFAULT
            }
            addListener(listener)
        }

        textField1 = TextField("", skin, "spinner").apply {
            messageText = "username"
            alignment = center
            addListener(listener)
        }

        textField2 = TextField("", skin, "spinner").apply {
            messageText = "password"
            alignment = center
            addListener(listener)
        }

        sendTable = Table(skin).apply {
            isVisible = false
            center()
            addListener {
                if (it is InputEvent && it.type == InputEvent.Type.keyDown && it.keyCode == Input.Keys.ENTER) {
                    token = sendEvent()
                    if (token is TokenResponse) {
                        closeButton.isDisabled = true
                        sendButton.isDisabled = true
                        backButton.isDisabled = true

                        //START GAME
                        game.startGameScreen()
                    }
                    return@addListener true
                }
                false
            }
            setFillParent(true)
            add(infoLabel).padBottom(10f).row()
            add(textField1).padBottom(10f).row()
            add(textField2).padBottom(10f).row()
            add(sendButton).padBottom(10f).row()
            add(backButton)
        }

        chooseTable = Table(skin).apply {
            isVisible = true
            center()
            setFillParent(true)
            add(signUpButton).padBottom(10f).row()
            add(signInButton)
        }

        stage.apply {
            addActor(sendTable)
            addActor(chooseTable)
            addActor(closeButton)
        }

        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height)
    }

    override fun dispose() {
        stage.dispose()
        skin.dispose()
    }
    companion object{
        lateinit var client: KtorClient
    }
}
