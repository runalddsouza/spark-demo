package com.job

import com.configuration.SparkConfiguration
import com.transformations.{SampleTransform, Transform}
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

class HdfsWriteJob extends SparkJob {

  override def init(configuration: SparkConfiguration): SparkSession = SparkSession.builder
    .master(configuration.master).config("fs.defaultFS", configuration.hdfs.uri).getOrCreate

  override def execute(configuration: SparkConfiguration): Unit = write(new SampleTransform(spark), configuration.hdfs.writePath)

  private def write(transform: Transform, path: String): Unit = {
    val orcPath = s"${path}/orc"
    val parquetPath = s"${path}/parquet"

    writeOrc(transform.country, s"${orcPath}/Country")
    writeOrc(transform.region, s"${orcPath}/Region")
    writeOrc(transform.department, s"${orcPath}/Department")
    writeOrc(transform.employee, s"${orcPath}/Employee")

    writeParquet(transform.country, s"${parquetPath}/Country")
    writeParquet(transform.region, s"${parquetPath}/Region")
    writeParquet(transform.department, s"${parquetPath}/Department")
    writeParquet(transform.employee, s"${parquetPath}/Employee")
  }

  def writeParquet(df: DataFrame, path: String): Unit = df.write.mode(SaveMode.Overwrite).option("compression", "snappy").parquet(path)

  def writeOrc(df: DataFrame, path: String): Unit = df.write.mode(SaveMode.Overwrite).option("compression", "zlib").orc(path)
}
