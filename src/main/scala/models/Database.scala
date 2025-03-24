package models

import slick.jdbc.SQLiteProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class Database(implicit ec: ExecutionContext) {
  val db = Database.forURL("jdbc:sqlite:employees.db", driver = "org.sqlite.JDBC")

  val employees = EmployeeTable.employees

  def init(): Future[Unit] = db.run(employees.schema.createIfNotExists)

  def insertEmployee(employee: Employee): Future[Int] = db.run(employees += employee)

  def getAllEmployee: Future[Seq[Employee]] = db.run(employees.result)

  def getEmployeeById(id: Int): Future[Option[Employee]] = db.run(employees.filter(_.id === id).result.headOption)

  def updateEmployee(id: Int, user: Employee): Future[Int] =
    db.run(employees.filter(_.id === id).update(user.copy(id = Some(id))))

  def deleteEmployee(id: Int): Future[Int] = db.run(employees.filter(_.id === id).delete)
}
