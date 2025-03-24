package models

import slick.jdbc.SQLiteProfile.api._

case class Employee(id: Option[Int], name: String, email: String)

class Employees(tag: Tag) extends Table[Employee](tag, "employees") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def email = column[String]("email")

  def * = (id.?, name, email) <> (Employee.tupled, Employee.unapply)
}

object EmployeeTable {
  val employees = TableQuery[Employees]
}
