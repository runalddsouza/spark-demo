package com.transformations

import com.data.{SampleOperationData, SampleOrganizationData}
import org.apache.spark.sql.SparkSession
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.api.{BeforeAll, Test, TestInstance}

@TestInstance(Lifecycle.PER_CLASS)
class TransformTest {

  var org: OrganizationTransform = _
  var ops: OperationTransform = _
  var spark: SparkSession = _

  @BeforeAll
  def init(): Unit = {
    spark = setupLocalSpark
    org = new SampleOrganizationTransform(spark, new SampleOrganizationData)
    ops = new SampleOperationTransform(spark, new SampleOperationData)
  }

  @Test
  def validateColumnCount(): Unit = {
    assertEquals(3, org.country.columns.length)
    assertEquals(3, org.region.columns.length)
    assertEquals(3, org.department.columns.length)
    assertEquals(6, org.employee.columns.length)

    assertEquals(2, ops.transaction.columns.length)
    assertEquals(5, ops.stock.columns.length)
  }

  private def setupLocalSpark: SparkSession = SparkSession.builder().master("local[*]").getOrCreate()
}
