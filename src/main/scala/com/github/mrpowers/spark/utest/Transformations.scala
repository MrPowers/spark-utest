package com.github.mrpowers.spark.utest

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object Transformations {

  def happyData()(df: DataFrame): DataFrame = {
    df.withColumn("happy", lit("data is fun"))
  }

}
