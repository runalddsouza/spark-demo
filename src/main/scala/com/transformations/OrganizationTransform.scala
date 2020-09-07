package com.transformations

import com.model._
import org.apache.spark.sql.Dataset

trait OrganizationTransform {
  def country: Dataset[Country]

  def region: Dataset[Region]

  def department: Dataset[Department]

  def employee: Dataset[Employee]
}
