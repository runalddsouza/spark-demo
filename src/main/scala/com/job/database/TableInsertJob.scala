package com.job.database

import com.configuration.SparkConfiguration
import com.job.SparkJob
import com.transformations.OperationTransform

abstract class TableInsertJob(configuration: SparkConfiguration) extends SparkJob {

  final protected def execute(): Unit = {
    initSchema()
    insert(getOperations)
  }

  protected def initSchema()

  protected def getOperations: OperationTransform

  protected def insert(operation: OperationTransform)
}
