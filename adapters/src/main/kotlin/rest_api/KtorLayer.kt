package adapters.rest_api

import adapters.rest.KtorClient
import adapters.rest.dao.Response
import adapters.rest.dao.SignType
import com.badlogic.gdx.scenes.scene2d.ui.Label


class KtorLayer {
    private var baseUrl: String = "http://localhost:8080"

    val client = KtorClient(baseUrl)

    fun getToken(signType: SignType, label: Label): Response {
        return client.httpConnect(signType, label)
    }
}
