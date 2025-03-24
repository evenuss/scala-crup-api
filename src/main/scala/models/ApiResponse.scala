package models

import spray.json.{DefaultJsonProtocol, DeserializationException, JsNull, JsValue, JsonFormat, RootJsonFormat, JsObject, JsString}

case class ApiResponse[T](status: String, message: String, data: Option[T] = None)

object ApiResponse extends DefaultJsonProtocol {
  // Custom formatter untuk Option[T]
  override implicit def optionFormat[T: JsonFormat]: JsonFormat[Option[T]] = new JsonFormat[Option[T]] {
    override def write(option: Option[T]): JsValue = option match {
      case Some(value) => implicitly[JsonFormat[T]].write(value) // Serialize value normally
      case None        => JsNull // Jika None, kembalikan `null` (agar field tetap muncul)
    }

    override def read(json: JsValue): Option[T] = json match {
      case JsNull    => None
      case other     => Some(implicitly[JsonFormat[T]].read(other))
    }
  }

  // Implicit formatter untuk ApiResponse[T] dengan support Option[T]
  implicit def apiResponseFormat[T: JsonFormat]: RootJsonFormat[ApiResponse[T]] =
    jsonFormat3(ApiResponse.apply[T])
}
