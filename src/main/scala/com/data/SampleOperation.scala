package com.data

import java.text.SimpleDateFormat
import java.util.{Date, UUID}

import com.model.Transaction

class SampleOperation extends Operation {
  def transaction: List[Transaction] = {
    val date = new SimpleDateFormat("yyyy-MM-dd'T'HH.mm.ssZ").format(new Date())
    (1 to 100).map(_ => Transaction(UUID.randomUUID.toString, date)).toList
  }
}
