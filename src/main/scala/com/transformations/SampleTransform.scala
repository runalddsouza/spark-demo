package com.transformations

import com.data.Sample
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.storage.StorageLevel

class SampleTransform(spark: SparkSession) extends Transform {

  def country: DataFrame = spark.createDataFrame(Sample.country).persist(StorageLevel.MEMORY_AND_DISK)

  def region: DataFrame = spark.createDataFrame(Sample.region).persist(StorageLevel.MEMORY_AND_DISK)

  def department: DataFrame = spark.createDataFrame(Sample.department).persist(StorageLevel.MEMORY_AND_DISK)

  def employee: DataFrame = spark.createDataFrame(Sample.employee).persist(StorageLevel.MEMORY_AND_DISK)
}
