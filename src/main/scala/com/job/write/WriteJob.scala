package com.job.write

import com.configuration.SparkConfiguration
import com.job.SparkJob

abstract class WriteJob[T](configuration: SparkConfiguration) extends SparkJob {
  protected val writeLocation: String = setWriteLocation()

  final protected def execute(): Unit = write(transform, writeLocation)

  protected def setWriteLocation(): String

  protected def transform: T

  protected def write(transform: T, path: String)
}
