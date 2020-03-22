package com.data

import com.model._

trait Organization {
  def country: List[Country]

  def region: List[Region]

  def department: List[Department]

  def employee: List[Employee]
}
