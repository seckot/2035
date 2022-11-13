import scala.io.StdIn.readLine

object c {
  def main(args: Array[String]): Unit = {
    val salaries = List(100, 150, 200, 80, 120, 75)
    println("Введите размер премии – в процентах от годового дохода: ")
    val bonus = readLine().toFloat
    println("Введите значение компенсация питания: ")
    val eatCompinsation = readLine().toInt
    val Sal = salaries.sum / salaries.size
    salaries.foreach(
      (s: Int) => {

        val sNew = (s + s * bonus / 100 + eatCompinsation.toInt)
        var diff: Double = (100 - (sNew / Sal) * 100).round
        if (sNew < Sal) println(f"$sNew меньше средней зарплаты на $diff%%")
        else {
          diff = diff * -1
          println(s"$sNew больше средней зарплаты на $diff%%")
        }
      })

  }
}
