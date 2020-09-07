package com.model

case class Country(id: Int, name: String, code: String)

case class Region(id: Int, name: String, country: Int)

case class Employee(id: Int, name: String, age: Int, sex: String, salary: Long, department: Int)

case class Department(id: Int, name: String, region: Int)

case class Transaction(id: String, date: String)

case class Stock(id: String, sType: String, item: String, remaining: Int, updateDate: Long)
