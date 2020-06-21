package com.transformations

import com.LocalSpark
import com.data.SampleOrganizationData
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.api.{Test, TestInstance}

@TestInstance(Lifecycle.PER_CLASS)
class OrganizationTransformTest extends LocalSpark {

  var org: OrganizationTransform = _

  override def initialize(): Unit = org = new SampleOrganizationTransform(spark, new SampleOrganizationData)

  @Test
  def validateColumnCount(): Unit = {
    assertEquals(3, org.country.columns.length)
    assertEquals(3, org.region.columns.length)
    assertEquals(3, org.department.columns.length)
    assertEquals(6, org.employee.columns.length)
  }

  @Test
  def validateDataCount(): Unit = {
    assertEquals(6, org.country.count)
    assertEquals(10, org.region.count)
    assertEquals(10, org.department.count)
    assertEquals(13, org.employee.count)
  }
}
