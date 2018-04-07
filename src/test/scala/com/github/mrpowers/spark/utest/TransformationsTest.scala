package com.github.mrpowers.spark.utest

import org.apache.spark.sql.types.{StructField, StructType, StringType}
import org.apache.spark.sql.Row

import utest._

object TransformationsTest
    extends TestSuite
    with SparkSessionTestWrapper {

  val tests = Tests {
    'test4 - {
      import spark.implicits._
      val sourceDF = Seq(
        ("jose"),
        ("li"),
        ("luisa")
      ).toDF("name")

      val actualDF = sourceDF.transform(Transformations.happyData())

      val expectedData = List(
        Row("jose", "data is fun"),
        Row("li", "data is fun"),
        Row("luisa", "data is fun")
      )

      val expectedSchema = List(
        StructField("name", StringType, true),
        StructField("happy", StringType, false)
      )

      val expectedDF = spark.createDataFrame(
        spark.sparkContext.parallelize(expectedData),
        StructType(expectedSchema)
      )

      actualDF.collect() ==> expectedDF.collect()
    }
  } // end val tests

}