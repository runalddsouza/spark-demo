package com.data

import java.text.SimpleDateFormat
import java.util.{Date, UUID}

import com.model.{Stock, Transaction}

class SampleOperation extends Operation {
  def transaction: List[Transaction] = {
    val date = new SimpleDateFormat("yyyy-MM-dd'T'HH.mm.ssZ").format(new Date())
    (1 to 100).map(_ => Transaction(UUID.randomUUID.toString, date)).toList
  }

  def stock: List[Stock] = List(
    Stock("1", "THIRD_PARTY", "Gaming Keyboard", 150, System.currentTimeMillis),
    Stock("2", "THIRD_PARTY", "LED Monitor", 98, System.currentTimeMillis),
    Stock("3", "THIRD_PARTY", "Headset", 99, System.currentTimeMillis),
    Stock("4", "IN_HOUSE", "PSU", 30, System.currentTimeMillis),
    Stock("5", "IN_HOUSE", "RAM", 490, System.currentTimeMillis),
    Stock("6", "IN_HOUSE", "SSD", 159, System.currentTimeMillis),
    Stock("7", "IN_HOUSE", "GPU", 400, System.currentTimeMillis)
  )
}
