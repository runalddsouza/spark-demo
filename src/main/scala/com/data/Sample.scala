package com.data

import com.model.{Country, Department, Employee, Region}

//Sample Data
object Sample {
  final val country = List(
    Country(100, "Australia", "AU"),
    Country(101, "Belgium", "BE"),
    Country(102, "India", "IN"),
    Country(103, "Netherlands", "NL"),
    Country(104, "United States of America", "US"),
    Country(105, "United Arab Emirates", "AE"),
  )

  final val region = List(
    Region(1000, "Melbourne", 100),
    Region(1001, "Mumbai", 102),
    Region(1002, "Bangalore", 102),
    Region(1003, "Pune", 102),
    Region(1004, "Abu Dhabi", 105),
    Region(1005, "Dubai", 105),
    Region(1006, "San Francisco", 104),
    Region(1007, "Amsterdam", 103),
    Region(1008, "Rotterdam", 103),
    Region(1009, "Brussels", 101),
  )

  final val department = List(
    Department(1, "Engineering", 1003),
    Department(2, "Engineering", 1007),
    Department(3, "Engineering", 1002),
    Department(4, "Finance", 1001),
    Department(5, "Finance", 1006),
    Department(6, "PR", 1008),
    Department(7, "HR", 1000),
    Department(8, "HR", 1009),
    Department(9, "Content & Design", 1005),
    Department(10, "Sales", 1001),
  )

  final val employee = List(
    Employee(100000, "A", 25, "Female", 150000, 1),
    Employee(100001, "B", 26, "Female", 1000, 1),
    Employee(100002, "C", 27, "Female", 10000, 1),
    Employee(100003, "D", 28, "Male", 15000, 2),
    Employee(100004, "E", 29, "Male", 170000, 2),
    Employee(100005, "F", 30, "Male", 450000, 3),
    Employee(100006, "G", 31, "Female", 550000, 3),
    Employee(100007, "H", 32, "Female", 650000, 4),
    Employee(100008, "I", 33, "Female", 150000, 5),
    Employee(100009, "J", 34, "Male", 750000, 6),
    Employee(100010, "K", 35, "Male", 250000, 7),
    Employee(100011, "L", 36, "Male", 900000, 9),
    Employee(100012, "M", 37, "Male", 50000, 10),
  )
}
