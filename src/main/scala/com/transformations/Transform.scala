package com.transformations

import com.data.Organization
import com.model._
import org.apache.spark.sql.{Dataset, SparkSession}

abstract class Transform(spark: SparkSession, organization: Organization) {
  def country: Dataset[Country]

  def region: Dataset[Region]

  def department: Dataset[Department]

  def employee: Dataset[Employee]
}
