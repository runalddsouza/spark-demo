package com.job

import com.configuration.SparkConfiguration
import org.apache.spark.sql.SparkSession

trait SparkJob {

  var spark: SparkSession = _

  def init(configuration: SparkConfiguration): SparkSession

  def execute(configuration: SparkConfiguration): Unit

  def run(configuration: SparkConfiguration): Unit = {
    spark = init(configuration)
    execute(configuration)
    finish()
  }

  def finish(): Unit = spark.stop
}
