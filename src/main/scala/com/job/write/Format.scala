package com.job.write

import org.apache.spark.sql.{DataFrame, SaveMode}

trait Format {

  def writeData(df: DataFrame, path: String, entity: String): Unit = {
    parquet(df, path, entity)
    orc(df, path, entity)
  }

  private def parquet(df: DataFrame, path: String, entity: String): Unit = df.write.mode(SaveMode.Overwrite)
    .option("compression", "snappy").parquet(s"${path}/parquet/${entity}")

  private def orc(df: DataFrame, path: String, entity: String): Unit = df.write.mode(SaveMode.Overwrite)
    .option("compression", "zlib").orc(s"${path}/orc/${entity}")
}
