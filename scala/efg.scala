import scala.io.StdIn.readLine

object efg {
  def main(args: Array[String]): Unit = {
    var salaries = List(100, 150, 200, 80, 120, 75)
    var newEmployee = List(350, 90)
    var newSalary = salaries ::: newEmployee
    var sortedSalary = newSalary.sortWith(_ < _)
    println(sortedSalary) // e

    var veryNewEmployee = 130
    var sortedArraySalary = sortedSalary.toArray
    var i: Int = 0
    for (s <- sortedArraySalary)
      if (s <= veryNewEmployee) (i = i + 1)

    var veryNewSortedArraySalary = sortedArraySalary.slice(0, i) ++ Array(veryNewEmployee) ++ sortedArraySalary.slice(i, sortedArraySalary.length)
    println(veryNewSortedArraySalary.mkString(", "))

    var minSalary = readLine("Введите нижнее значение зарплаты Middle: ")
    var maxSalary = readLine("Введите верхнее значение зарплаты Middle: ")

    for (s <- veryNewSortedArraySalary) {

      if ((s >= minSalary.toInt) && (s <= maxSalary.toInt)) {
        println("Индекс сотрудников уровня Middle - " + veryNewSortedArraySalary.indexOf(s).toString + ", значение зарплаты - " + s.toString)
      }
    }

  }
}