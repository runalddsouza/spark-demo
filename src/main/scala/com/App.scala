package com

import com.configuration.Configuration
import com.job.{HdfsWriteJob, HiveInsertJob}

object App {
  def main(args: Array[String]): Unit = {
    val configuration = Configuration.load(args(0))
    new HdfsWriteJob().run(configuration)
    new HiveInsertJob().run(configuration)
  }
}
