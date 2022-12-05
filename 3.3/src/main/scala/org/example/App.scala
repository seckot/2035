package org.example
import org.apache.spark.sql.functions.{asc, col, count, countDistinct, desc, floor, from_unixtime, hour, lit, to_timestamp, when}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types._


object App {
  def main(args : Array[String]): Unit = {
    val spark = SparkSession.builder.master(master = "local[1]").appName(name = "Simple Application").getOrCreate()

    val schema = StructType(Array(
      StructField("id", IntegerType, true),
      StructField("timestamp", IntegerType, true),
      StructField("type", StringType, true),
      StructField("pageid", IntegerType, true),
      StructField("tag", StringType, true),
      StructField("sign", BooleanType, true)))

    val datasite = Seq(Row(1, 1637440253, "click", 46, "отдых", true),
      Row(1, 1637840253, "scroll", 16, "спорт", true),
      Row(1, 1670172777, "visit", 43, "спорт", true),
      Row(2, 1669195403, "scroll", 43, "высокие технологии", true),
      Row(3, 1669195403, "visit", 26, "политика", true),
      Row(4, 1669195403, "scroll", 41, "спорт", true),
      Row(5, 1669195403, "click", 21, "политика", true),
      Row(6, 1669195403, "click", 12, "спорт", true),
      Row(7, 1669195403, "scroll", 32, "спорт", true),
      Row(8, 1637840253, "visit", 42, "высокие технологии", true),
      Row(9, 1670172777, "scroll", 5, "спорт", true),
      Row(10, 1670172777, "scroll", 11, "высокие технологии", true),
      Row(11, 1669195403, "scroll", 33,"политика", true),
      Row(12, 1637840253, "visit", 21, "спорт", true),
      Row(13, 1670172777, "click", 6, "высокие технологии", true),
      Row(14, 1670172777, "visit", 4, "спорт", true),
      Row(15, 1637840253, "scroll", 6, "спорт", false),
      Row(16, 1637840253, "click", 43, "отдых", true),
      Row(17, 1670172777, "scroll", 16, "спорт", true),
      Row(18, 1637840253, "visit", 11, "политика", false),
      Row(19, 1670172777, "scroll", 10, "спорт", true),
      Row(20, 1637840253, "visit", 1, "высокие технологии", true),
      Row(21, 1637840253, "click", 2, "отдых", true),
      Row(22, 1670172777, "visit", 2, "спорт", true),
      Row(23, 1637840253, "scroll", 3, "высокие технологии", true),
      Row(24, 1670172777, "click", 4, "спорт", false),
      Row(1, 1669195403, "scroll", 4, "политика", true),
      Row(2, 1637840153, "click", 5, "спорт", true),
      Row(3, 1669115303, "scroll", 6, "высокие технологии", true),
      Row(4, 1669185201, "click", 4, "отдых", true),
      Row(5, 1669105103, "scroll", 8, "спорт", true),
      Row(6, 1669115303, "visit", 41, "отдых", true),
    )
    val df = spark.createDataFrame(spark.sparkContext.parallelize(datasite),schema)

    println(df.show())

    val topactiveusers = df
      .groupBy("id")
      .count()
      .orderBy(desc("count"))
      println(topactiveusers.show(numRows = 5))

    val signyes = df.select("id").where(col("sign") === true).select(countDistinct("id")).collect()(0)(0).toString.toInt
    val userall = df.select("id").select(countDistinct("id")).collect()(0)(0).toString.toInt
    println("процент посетителей, у которых есть ЛК %s %%".format(100 * signyes / userall))

    val topclickbysiste = df
      .filter(col("type") === "click")
      .groupBy("pageid")
      .agg(count("pageid").as("countPage"))
      .select("pageid", "countPage").orderBy(desc("countPage"))
      println(topclickbysiste.show(numRows = 5))

    val timeRange = df
      .withColumn("time_range", floor(hour(from_unixtime(col("timestamp"))) / lit(4)))
    println(timeRange.show(numRows = 20))

    val timeRangePeriod = timeRange
      .groupBy("time_range")
      .agg(count("time_range")
        .alias("count"))
      .select("time_range", "count")
      .orderBy(desc("count"))

    println(timeRangePeriod.show(numRows = 4))

    val schemaUsers = StructType(Array(
      StructField("id", IntegerType, true),
      StructField("user_id", IntegerType, true),
      StructField("user_name", StringType, true),
      StructField("birst_day", IntegerType, true),
      StructField("lc_create_date", IntegerType, true)))


    val usersData = Seq(Row(12, 1, "Волков Касьян Валентинович", 623517003, 1638717003),
      Row(11, 2, "Кондратьев Донат Рудольфович",  657731403, 1633446603),
      Row(22, 3, "Колобов Аскольд Геннадьевич", 623517003, 1633456603),
      Row(23, 4, "Ершов Всеволод Авдеевич", 623517003, 1633443603),
      Row(43, 5, "Чернов Лавр Олегович", 657731403, 1067627426),
      Row(54, 6, "Федотов Вальтер Демьянович", 623517003, 1638712003),
      Row(65, 7, "Силин Демьян Федорович", 623517003, 1633360203),
      Row(71, 8, "Панов Пантелеймон Семёнович", 1637627426, 1633360203),
      Row(84, 9, "Смирнов Федор Андреевич", 657731403, 1633443603),
      Row(39, 10, "Александров Трофим Макарович", 623517003, 1633443603),
      Row(120, 11, "Цветков Емельян Васильевич", 1667624426, 1067237426),
      Row(111, 12, "Елисеев Роман Яковлевич", 623517003, 1067627426),
      Row(121, 13, "Логинов Болеслав Константинович", 1667627426, 1067627426),
      Row(133, 14, "Гаврилов Соломон Фролович", 623517003, 1633443603),
      Row(144, 15, "Шестаков Глеб Николаевич", 657731403, 1638712003),
      Row(155, 16, "Лыткин Авраам Михайлович", 623517003, 1633456603),
      Row(161, 17, "Архипов Тимур Семенович", 657731403, 1067627426),
      Row(173, 18, "Князев Виссарион Миронович", 623517003, 1633446603),
      Row(192, 19, "Мясников Григорий Васильевич", 657731403, 1067644426),
      Row(203, 20, "Лапин Власий Тихонович", 1637627426, 1638712003),
    )

    val df2 = spark.createDataFrame(spark.sparkContext.parallelize(usersData),schemaUsers)
    println(df2.show())


    val fio_sport = df
      .join(df2, df("id") === df2("user_id"), "inner")
      .filter(df("tag") === "спорт")
      .select("user_name").distinct()
    println(fio_sport.show())
  }

}