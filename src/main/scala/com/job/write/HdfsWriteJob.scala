package com.job.write

import com.configuration.SparkConfiguration
import com.data.SampleOrganization
import com.transformations.{SampleTransform, Transform}
import org.apache.spark.sql.SparkSession

class HdfsWriteJob(configuration: SparkConfiguration) extends WriteJob[Transform](configuration: SparkConfiguration) with Format {

  override def init: SparkSession = SparkSession.builder.master(configuration.master)
    .config("spark.hadoop.fs.defaultFS", configuration.hdfs.uri)
    .getOrCreate

  override def transform: Transform = new SampleTransform(spark, new SampleOrganization)

  override def setWriteLocation(): String = configuration.hdfs.writePath

  override def write(transform: Transform, path: String): Unit = {
    writeData(transform.country.toDF, path, "Country")
    writeData(transform.region.toDF, path, "Region")
    writeData(transform.department.toDF, path, "Department")
    writeData(transform.employee.toDF, path, "Employee")
  }
}
