package com.job

import java.text.SimpleDateFormat
import java.util.{Date, UUID}

import com.configuration.SparkConfiguration
import com.model.Transaction
import org.apache.spark.sql.SparkSession

class HiveInsertJob extends SparkJob {
  override def init(configuration: SparkConfiguration): SparkSession = SparkSession.builder
    .master(configuration.master).config("hive.metastore.uris", configuration.hive.metastore)
    .config("hive.exec.dynamic.partition.mode", "nonstrict").enableHiveSupport.getOrCreate

  override def execute(configuration: SparkConfiguration): Unit = {
    spark.sql(configuration.hive.createQuery)
    spark.createDataFrame(getTransactions).createOrReplaceTempView(configuration.hive.tempTable)
    spark.sql(configuration.hive.insertQuery)
  }

  private def getTransactions: List[Transaction] = {
    val date = new SimpleDateFormat("yyyy-MM-dd'T'HH.mm.ssZ").format(new Date())
    (1 to 100).map(_ => Transaction(UUID.randomUUID.toString, date)).toList
  }
}
