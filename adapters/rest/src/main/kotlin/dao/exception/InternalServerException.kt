package adapters.rest.dao.exception

import io.ktor.http.*

class InternalServerException: AbstractApiException(
    status = HttpStatusCode.InternalServerError,
    message = "Something went wrong on server-side"
)
