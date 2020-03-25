package com.job.database

import com.configuration.SparkConfiguration
import com.data.{Operation, SampleOperation}
import org.apache.spark.sql.SparkSession

class HiveInsertJob(configuration: SparkConfiguration) extends TableInsertJob(configuration: SparkConfiguration) {
  override def init: SparkSession = SparkSession.builder.master(configuration.master)
    .config("spark.hadoop.fs.defaultFS", configuration.hdfs.uri)
    .config("hive.metastore.uris", configuration.hive.metastore)
    .config("spark.sql.warehouse.dir", "/user/hive/warehouse")
    .config("hive.exec.dynamic.partition.mode", "nonstrict").enableHiveSupport.getOrCreate

  override def getOperations: Operation = new SampleOperation

  override def insert(operation: Operation): Unit = {
    spark.createDataFrame(operation.transaction).createOrReplaceTempView(configuration.hive.internal.tempTable)
    spark.sql(configuration.hive.internal.insertQuery)
  }

  override protected def initSchema(): Unit = {
    //Create Databases
    spark.sql(configuration.hive.external.createDatabaseQuery)
    spark.sql(configuration.hive.internal.createDatabaseQuery)
    //Create Tables
    configuration.hive.external.table.foreach(s => spark.sql(s.query))
    spark.sql(configuration.hive.internal.table.head.query)
  }
}
