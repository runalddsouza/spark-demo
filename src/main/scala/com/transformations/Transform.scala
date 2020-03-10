package com.transformations

import org.apache.spark.sql.DataFrame

trait Transform {
  def country: DataFrame

  def region: DataFrame

  def department: DataFrame

  def employee: DataFrame
}
