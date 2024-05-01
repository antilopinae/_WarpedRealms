package adapters.rest.dao.exception

import io.ktor.http.*

class ClientError: AbstractApiException(
    status = HttpStatusCode.BadRequest,
    message = "Something went wrong on client-side"
)
