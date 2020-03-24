package com.job.database

import com.configuration.SparkConfiguration
import com.data.Operation
import com.job.SparkJob

abstract class TableInsertJob(configuration: SparkConfiguration) extends SparkJob {

  final protected def execute(): Unit = {
    initSchema()
    insert(getOperations)
  }

  protected def initSchema()

  protected def getOperations: Operation

  protected def insert(operation: Operation)
}
