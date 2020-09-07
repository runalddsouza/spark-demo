package com.job.write

import com.configuration.{Configuration, SparkConfiguration}
import com.data.SampleOrganizationData
import com.transformations.{OrganizationTransform, SampleOrganizationTransform}

class HdfsWriteJob(configuration: SparkConfiguration) extends WriteJob[OrganizationTransform](configuration: SparkConfiguration) with Format {

  override def transform: OrganizationTransform = new SampleOrganizationTransform(spark, new SampleOrganizationData)

  override def setWriteLocation(): String = configuration.hdfs.writePath

  override def write(transform: OrganizationTransform, path: String): Unit = {
    writeData(transform.country.toDF, path, "Country")
    writeData(transform.region.toDF, path, "Region")
    writeData(transform.department.toDF, path, "Department")
    writeData(transform.employee.toDF, path, "Employee")
  }
}

object HdfsWriteJob {
  def main(args: Array[String]): Unit = new HdfsWriteJob(Configuration.load).run()
}