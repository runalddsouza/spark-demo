package com.job.database

import com.configuration.SparkConfiguration
import com.data.{Operation, SampleOperation}
import org.apache.spark.sql.SparkSession

class HiveInsertJob(configuration: SparkConfiguration) extends TableInsertJob(configuration: SparkConfiguration) {
  override def init: SparkSession = SparkSession.builder.master(configuration.master)
    .config("hive.metastore.uris", configuration.hive.metastore)
    .config("hive.exec.dynamic.partition.mode", "nonstrict").enableHiveSupport.getOrCreate

  override def getOperations: Operation = new SampleOperation

  override def insert(operation: Operation): Unit = {
    spark.sql(configuration.hive.createQuery)
    spark.createDataFrame(operation.transaction).createOrReplaceTempView(configuration.hive.tempTable)
    spark.sql(configuration.hive.insertQuery)
  }
}
