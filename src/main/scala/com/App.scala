package com

import com.configuration.Configuration
import com.job.database.{HiveInsertJob, TableInsertJob}
import com.job.write.{HdfsWriteJob, WriteJob}

object App {
  def main(args: Array[String]): Unit = {
    val configuration = Configuration.load(args(0))
    val hdfsWriteJob: WriteJob = new HdfsWriteJob(configuration)
    val hiveInsertJob: TableInsertJob = new HiveInsertJob(configuration)
    //Run Jobs
    hdfsWriteJob.run()
    hiveInsertJob.run()
  }
}
