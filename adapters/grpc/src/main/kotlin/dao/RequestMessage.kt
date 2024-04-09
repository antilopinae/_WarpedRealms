package adapters.grpc.client.dao

import com.google.protobuf.ByteString

class RequestMessage (
    val byteArray_x: ByteArray,
    val byteArray_y: ByteArray,
    val byteArray_z: ByteArray,
    val byteArray_mouse_x: ByteArray,
    val byteArray_mouse_y: ByteArray,
){
    lateinit var token: String
}

