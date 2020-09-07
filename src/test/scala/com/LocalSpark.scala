package com

import org.apache.spark.sql.SparkSession
import org.junit.jupiter.api.{AfterAll, BeforeAll}

trait LocalSpark {
  var spark: SparkSession = _

  @BeforeAll
  def initializeSpark(): Unit = {
    spark = SparkSession.builder.master("local[*]").getOrCreate()
    initialize()
  }

  @AfterAll
  def stopSpark(): Unit = spark.stop()

  def initialize()
}
