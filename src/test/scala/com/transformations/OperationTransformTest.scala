package com.transformations

import com.LocalSpark
import com.data.SampleOperationData
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.api.{Test, TestInstance}

@TestInstance(Lifecycle.PER_CLASS)
class OperationTransformTest extends LocalSpark {

  var ops: OperationTransform = _

  override def initialize(): Unit = ops = new SampleOperationTransform(spark, new SampleOperationData)

  @Test
  def validateColumnCount(): Unit = {
    assertEquals(2, ops.transaction.columns.length)
    assertEquals(5, ops.stock.columns.length)
  }

  @Test
  def validateDataCount(): Unit = {
    assertEquals(100, ops.transaction.count)
    assertEquals(7, ops.stock.count)
  }
}
