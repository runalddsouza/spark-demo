package com.job.write

import com.configuration.SparkConfiguration
import com.job.SparkJob
import com.transformations.Transform

abstract class WriteJob(configuration: SparkConfiguration) extends SparkJob {
  protected val writeLocation: String = setWriteLocation()

  final protected def execute(): Unit = write(transform, writeLocation)

  protected def setWriteLocation(): String

  protected def transform: Transform

  protected def write(transform: Transform, path: String)
}
