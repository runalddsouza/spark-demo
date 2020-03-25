package com

import com.configuration.Configuration
import com.job.database._
import com.job.write._

object App {
  def main(args: Array[String]): Unit = {
    val configuration = Configuration.load(args(0))
    val hdfsWrite = new HdfsWriteJob(configuration)
    val hiveInsert = new HiveInsertJob(configuration)
    val hudiUpsert = new HudiUpsertJob(configuration)

    //Run Jobs
    hdfsWrite.run()
    hudiUpsert.run()
    hiveInsert.run()
  }
}
