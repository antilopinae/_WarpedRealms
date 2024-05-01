package adapters.rest.dao.exception


import io.ktor.http.*

class UnauthorizedException: AbstractApiException(
    status = HttpStatusCode.Unauthorized,
    message = "Credits is not valid"
)
