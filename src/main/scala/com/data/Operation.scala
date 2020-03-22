package com.data

import com.model.Transaction

trait Operation {
  def transaction: List[Transaction]
}
