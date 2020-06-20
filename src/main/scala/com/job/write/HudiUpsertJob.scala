package com.job.write

import com.configuration.SparkConfiguration
import com.data.SampleOperationData
import com.transformations.{OperationTransform, SampleOperationTransform}
import org.apache.hudi.DataSourceWriteOptions
import org.apache.hudi.config.{HoodieCompactionConfig, HoodieStorageConfig, HoodieWriteConfig}
import org.apache.spark.sql.SaveMode

class HudiUpsertJob(configuration: SparkConfiguration) extends WriteJob[OperationTransform](configuration: SparkConfiguration) {

  override protected def setWriteLocation(): String = configuration.hdfs.writePath

  override protected def transform: OperationTransform = new SampleOperationTransform(spark, new SampleOperationData)

  override protected def write(operation: OperationTransform, path: String): Unit = {
    val tableName = "Stock"
    operation.stock.toDF.write.format("org.apache.hudi")
      .option(DataSourceWriteOptions.TABLE_TYPE_OPT_KEY, DataSourceWriteOptions.COW_TABLE_TYPE_OPT_VAL)
      .option(DataSourceWriteOptions.OPERATION_OPT_KEY, DataSourceWriteOptions.UPSERT_OPERATION_OPT_VAL)
      .option(DataSourceWriteOptions.HIVE_STYLE_PARTITIONING_OPT_KEY, String.valueOf(true))
      .option(DataSourceWriteOptions.RECORDKEY_FIELD_OPT_KEY, "id")
      .option(DataSourceWriteOptions.PARTITIONPATH_FIELD_OPT_KEY, "sType")
      .option(DataSourceWriteOptions.PRECOMBINE_FIELD_OPT_KEY, "updateDate")
      .option(HoodieStorageConfig.PARQUET_COMPRESSION_CODEC, "snappy")
      .option("hoodie.insert.shuffle.parallelism", "12")
      .option("hoodie.upsert.shuffle.parallelism", "12")
      .option(HoodieCompactionConfig.CLEANER_COMMITS_RETAINED_PROP, "1")
      .option(HoodieWriteConfig.TABLE_NAME, tableName)
      .mode(SaveMode.Append)
      .save(s"${path}/parquet/${tableName}")
  }
}
