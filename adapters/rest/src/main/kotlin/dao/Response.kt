package adapters.rest.dao

import adapters.rest.dao.exception.*
import adapters.rest.dao.token.TokenResponse
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = InternalServerException::class, name = "exc1"),
    JsonSubTypes.Type(value = NotFoundException::class, name = "exc2"),
    JsonSubTypes.Type(value = UnauthorizedException::class, name = "exc3"),
    JsonSubTypes.Type(value = AlreadyExistException::class, name = "exc4"),
    JsonSubTypes.Type(value = ClientError::class, name = "exc5"),
    JsonSubTypes.Type(value = TokenResponse::class, name = "token"),
)
interface Response
