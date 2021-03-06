package com.transformations

import com.data.SampleOrganizationData
import com.model._
import org.apache.spark.sql.{Dataset, Encoders, SparkSession}
import org.apache.spark.storage.StorageLevel

class SampleOrganizationTransform(val spark: SparkSession, val organization: SampleOrganizationData) extends OrganizationTransform {

  override def country: Dataset[Country] = spark.createDataset(organization.country)(Encoders.product[Country]).persist(StorageLevel.MEMORY_AND_DISK)

  override def region: Dataset[Region] = spark.createDataset(organization.region)(Encoders.product[Region]).persist(StorageLevel.MEMORY_AND_DISK)

  override def department: Dataset[Department] = spark.createDataset(organization.department)(Encoders.product[Department]).persist(StorageLevel.MEMORY_AND_DISK)

  override def employee: Dataset[Employee] = spark.createDataset(organization.employee)(Encoders.product[Employee]).persist(StorageLevel.MEMORY_AND_DISK)
}
