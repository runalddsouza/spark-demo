package com.data

import com.model.{Stock, Transaction}

trait Operation {
  def transaction: List[Transaction]

  def stock: List[Stock]
}
