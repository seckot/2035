object h {
  def main(args: Array[String]): Unit = {
    var salaries = Array(100, 150, 200, 80, 120, 75, 350, 90, 130)
    for (i <- salaries.indices)
      salaries(i) = (salaries(i) * 1.07).toInt

    println(salaries.mkString("[",",","]"))

  }
}