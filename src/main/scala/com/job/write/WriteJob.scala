package com.job.write

import com.configuration.SparkConfiguration
import com.job.SparkJob
import org.apache.spark.sql.SparkSession

abstract class WriteJob[T](configuration: SparkConfiguration) extends SparkJob {
  protected val writeLocation: String = setWriteLocation()

  override protected def init: SparkSession = SparkSession.builder.master(configuration.master)
    .config("spark.hadoop.fs.defaultFS", configuration.hdfs.uri)
    .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    .getOrCreate()

  final protected def execute(): Unit = write(transform, writeLocation)

  protected def setWriteLocation(): String

  protected def transform: T

  protected def write(transform: T, path: String)
}
