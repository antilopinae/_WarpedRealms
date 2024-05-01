package adapters.rest

import adapters.rest.dao.Response
import adapters.rest.dao.SignType
import adapters.rest.dao.exception.AlreadyExistException
import adapters.rest.dao.exception.ClientError
import adapters.rest.dao.exception.InternalServerException
import adapters.rest.dao.token.TokenResponse
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import kotlinx.coroutines.runBlocking

class KtorClient(private val baseUrl: String) {
    var email: String = ""
    var password: String = ""
    private lateinit var infoLabel: Label
    private val objectMapper = jacksonObjectMapper()

    init{
        println("Ktor client started")
    }

    fun httpConnect(signType: SignType, label: Label): Response {
        infoLabel = label
        return when (signType) {
            SignType.SIGN_IN -> signIn()
            SignType.SIGN_UP -> signUp()
            SignType.DEFAULT -> ClientError()
        }
    }

    private fun getResponseBody(httpResponse: HttpResponse): Response {
        lateinit var response: Response
        runBlocking {
            response = objectMapper.readValue<Response>(httpResponse.body<String>())
        }
        when (response) {
            is TokenResponse -> {
                infoLabel.color = Color.GREEN
                infoLabel.setText("Token got")
            }
            is InternalServerException -> {
                infoLabel.color = Color.PURPLE
                infoLabel.setText("Server crush\nYou may try late")
            }
            is AlreadyExistException -> {
                infoLabel.color = Color.RED
                infoLabel.setText("User already exist")
            }
            else -> {
                infoLabel.color = Color.RED
                infoLabel.setText("Wrong data sent")
            }
        }
        infoLabel.isVisible = true
        return response
    }

    private fun getRequestBody(): String =
        """{
            "email": "$email",
            "password": "$password"
        }""".trimIndent()

    private fun createHttpClient() = HttpClient(CIO) {
        install(ContentNegotiation) {
            jackson()
        }
        engine {
            requestTimeout = 10_000
        }
    }

    private suspend fun createPostRequest(client: HttpClient, route: String)
        = client.post("$baseUrl/$route") {
        contentType(ContentType.Application.Json)
        setBody(getRequestBody())
    }

    private fun signUp() = runBlocking {
        val client = createHttpClient()
        val signUpResponse = createPostRequest(client, "users/sign-up")
        return@runBlocking getResponseBody(signUpResponse)
    }

    private fun signIn() = runBlocking {
        val client = createHttpClient()
        val signInResponse = createPostRequest(client, "users/sign-in")
        return@runBlocking getResponseBody(signInResponse)
    }
}

