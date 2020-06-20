package com.transformations

import com.data.SampleOperationData
import com.model.{Stock, Transaction}
import org.apache.spark.sql.{Dataset, Encoders, SparkSession}
import org.apache.spark.storage.StorageLevel

class SampleOperationTransform(val spark: SparkSession, val operation: SampleOperationData) extends OperationTransform {
  override def transaction: Dataset[Transaction] = spark.createDataset(operation.transaction)(Encoders.product[Transaction]).persist(StorageLevel.MEMORY_AND_DISK)

  override def stock: Dataset[Stock] = spark.createDataset(operation.stock)(Encoders.product[Stock]).persist(StorageLevel.MEMORY_AND_DISK)
}
