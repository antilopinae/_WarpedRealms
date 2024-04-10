package adapters.grpc.client.dao

class ResponseMessage(
    val token: String,
    val positions: Map<String,Position>
)

class Position(
    val position_x: Float,
    val position_y: Float
)
