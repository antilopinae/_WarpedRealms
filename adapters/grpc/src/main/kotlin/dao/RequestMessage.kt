package adapters.grpc.client.dao

import com.google.protobuf.ByteString

class RequestMessage (
    val input_x: Int,
    val input_y: Int,
    val input_z: Int,
    val input_mouse_x: Int,
    val input_mouse_y: Int,
){
    lateinit var token: String
}

