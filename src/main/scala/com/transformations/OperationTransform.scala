package com.transformations

import com.model.{Stock, Transaction}
import org.apache.spark.sql.Dataset

trait OperationTransform {
  def transaction: Dataset[Transaction]

  def stock: Dataset[Stock]
}
