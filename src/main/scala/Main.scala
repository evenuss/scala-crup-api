import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import models.Database
import routes.EmployeeRoutes

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object Main {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem("scala-crud")
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val db = new Database()
    db.init()

    val routes = new EmployeeRoutes(db).route

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(routes)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")

    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
