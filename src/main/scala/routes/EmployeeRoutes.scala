package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import models.{ApiResponse, Database, Employee}
import spray.json.DefaultJsonProtocol._
import scala.concurrent.ExecutionContext
import akka.http.scaladsl.model.StatusCodes._

class EmployeeRoutes(db: Database)(implicit ec: ExecutionContext) {

  // Import JSON formatters
  import models.ApiResponse.apiResponseFormat

  implicit val employeeFormat = jsonFormat3(Employee)

  val route =
    pathPrefix("employees") {
      concat(
        get {
          pathEndOrSingleSlash {
            onSuccess(db.getAllEmployee) { employees =>
              complete(OK, ApiResponse("success", "Employees retrieved", Some(employees)))
            }
          } ~
            path(IntNumber) { id =>
              onSuccess(db.getEmployeeById(id)) {
                case Some(employee) =>
                  complete(OK, ApiResponse("success", s"Employee with ID $id retrieved", Some(employee)))
                case None =>
                  complete(NotFound, ApiResponse[String]("error", s"Employee with ID $id not found", None))
              }
            }
        },
        post {
          entity(as[Employee]) { user =>
            onSuccess(db.insertEmployee(user)) { id =>
              complete(Created, ApiResponse[Int]("success", s"User added with ID: $id", Some(id)))
            }
          }
        },
        put {
          path(IntNumber) { id =>
            entity(as[Employee]) { user =>
              onSuccess(db.updateEmployee(id, user)) {
                case 1 => complete(OK, ApiResponse[String]("success", "User updated", None))
                case _ => complete(NotFound, ApiResponse[String]("error", s"Employee with ID $id not found", None))
              }
            }
          }
        },
        delete {
          path(IntNumber) { id =>
            onSuccess(db.deleteEmployee(id)) {
              case 1 => complete(OK, ApiResponse[String]("success", "User deleted", None))
              case _ => complete(NotFound, ApiResponse[String]("error", s"Employee with ID $id not found", None))
            }
          }
        }
      )
    }
}
