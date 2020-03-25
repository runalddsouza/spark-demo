package com.job.write

import com.configuration.SparkConfiguration
import com.data.{Operation, SampleOperation}
import org.apache.hudi.DataSourceWriteOptions
import org.apache.hudi.config.HoodieWriteConfig
import org.apache.spark.sql.{SaveMode, SparkSession}

class HudiUpsertJob(configuration: SparkConfiguration) extends WriteJob[Operation](configuration: SparkConfiguration) {

  override protected def setWriteLocation(): String = configuration.hdfs.writePath

  override protected def transform: Operation = new SampleOperation

  override protected def write(operation: Operation, path: String): Unit = {
    val tableName = "Stock"
    spark.createDataFrame(operation.stock).write.format("org.apache.hudi")
      .option(DataSourceWriteOptions.RECORDKEY_FIELD_OPT_KEY, "id")
      .option(DataSourceWriteOptions.PARTITIONPATH_FIELD_OPT_KEY, "sType")
      .option(DataSourceWriteOptions.PRECOMBINE_FIELD_OPT_KEY, "updateDate")
      .option(DataSourceWriteOptions.OPERATION_OPT_KEY, DataSourceWriteOptions.UPSERT_OPERATION_OPT_VAL)
      .option(HoodieWriteConfig.TABLE_NAME, tableName)
      .mode(SaveMode.Append)
      .save(s"${path}/parquet/${tableName}")
  }

  override protected def init: SparkSession = SparkSession.builder.master(configuration.master)
    .config("spark.hadoop.fs.defaultFS", configuration.hdfs.uri)
    .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    .getOrCreate()
}
