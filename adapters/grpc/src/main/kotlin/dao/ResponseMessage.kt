package adapters.grpc.client.dao

class ResponseMessage(
    val token: String,
    val positions: List<Position>
)

class Position(
    val position_x: Long,
    val position_y: Long
)
