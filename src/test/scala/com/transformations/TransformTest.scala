package com.transformations

import org.apache.spark.sql.SparkSession
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.api.{BeforeAll, Test, TestInstance}

@TestInstance(Lifecycle.PER_CLASS)
class TransformTest {

  var transform: Transform = _
  var spark: SparkSession = _

  @BeforeAll
  def init(): Unit = {
    spark = setupLocalSpark
    transform = new SampleTransform(spark)
  }

  @Test
  def validateColumnCount(): Unit = {
    assertEquals(3, transform.country.columns.length)
    assertEquals(3, transform.region.columns.length)
    assertEquals(3, transform.department.columns.length)
    assertEquals(6, transform.employee.columns.length)
  }

  private def setupLocalSpark: SparkSession = SparkSession.builder().master("local[*]").getOrCreate()
}
