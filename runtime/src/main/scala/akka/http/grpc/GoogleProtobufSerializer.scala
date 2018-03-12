package akka.http.grpc

class GoogleProtobufSerializer[T <: com.google.protobuf.Message](clazz: Class[T]) extends ProtobufSerializer[T] {
  override def serialize(t: T) = akka.util.ByteString(t.toByteArray)
  override def deserialize(bytes: akka.util.ByteString): T = {
    val parser = clazz.getMethod("parseFrom", classOf[Array[Byte]])
    parser.invoke(clazz, bytes.toArray).asInstanceOf[T]
  }
}