package adapters.rest.dao.exception

import io.ktor.http.*

class AlreadyExistException: AbstractApiException(
    status = HttpStatusCode.BadRequest,
    message = "User with this username already exist"
)
