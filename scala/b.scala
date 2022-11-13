import scala.io.StdIn.readLine

object b {
  def main(args: Array[String]): Unit = {
    println("Введите значение годового дохода до вычета налогов: ")
    val yearSalary = readLine().toInt
    println("Введите размер премии – в процентах от годового дохода: ")
    val bonus = readLine().toFloat
    println("Введите значение компенсация питания: ")
    val eatCompinsation = readLine().toInt
    var salarymonth = (yearSalary + yearSalary * bonus + eatCompinsation) * 0.87 / 12
    println("Ежемесечный оклад: " + salarymonth)
  }
}