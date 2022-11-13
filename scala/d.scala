import scala.io.StdIn.readLine

object d {
  def main(args: Array[String]): Unit = {
    val salaries = List(100, 150, 200, 80, 120, 75)
    println("Введите размер премии – в процентах от годового дохода: ")
    val bonus = readLine().toFloat
    println("Введите значение компенсация питания: ")
    val eatCompinsation = readLine().toInt
    val Sal = salaries.sum / salaries.size
    val newSalaries = salaries.map((s: Int) => if (s < Sal) s + 15 else s)
    println("максимальное значение: " + newSalaries.max)
    println("Минимальное значение: " + newSalaries.min)
  }
}