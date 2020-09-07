package com.job.database

import com.configuration.{Configuration, SparkConfiguration}
import com.data.SampleOperationData
import com.transformations.{OperationTransform, SampleOperationTransform}
import org.apache.spark.sql.SparkSession

class HiveInsertJob(configuration: SparkConfiguration) extends TableInsertJob(configuration: SparkConfiguration) {
  override def init: SparkSession = SparkSession.builder.master(configuration.master)
    .config("spark.sql.warehouse.dir", "/user/hive/warehouse")
    .config("hive.exec.dynamic.partition.mode", "nonstrict")
    .enableHiveSupport
    .getOrCreate

  override def getOperations: OperationTransform = new SampleOperationTransform(spark, new SampleOperationData)

  override def insert(operation: OperationTransform): Unit = {
    operation.transaction.toDF.createOrReplaceTempView(configuration.hive.internal.tempTable)
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

object HiveInsertJob {
  def main(args: Array[String]): Unit = new HiveInsertJob(Configuration.load).run()
}