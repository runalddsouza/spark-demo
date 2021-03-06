package com.configuration

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.scala.DefaultScalaModule

object Configuration {
  def load: SparkConfiguration = {
    new ObjectMapper(new YAMLFactory)
      .registerModule(DefaultScalaModule).readValue(getClass.getClassLoader.getResourceAsStream("config.yml"), classOf[SparkConfiguration])
  }
}

case class SparkConfiguration(@JsonProperty("master") master: String, @JsonProperty("hive") hive: Hive,
                              @JsonProperty("hdfs") hdfs: Hdfs)

case class Hdfs(@JsonProperty("readLocation") readPath: String, @JsonProperty("writeLocation") writePath: String)

case class Hive(@JsonProperty("external") external: External, @JsonProperty("internal") internal: Internal)

case class External(@JsonProperty("createDatabaseQuery") createDatabaseQuery: String, @JsonProperty("table") table: List[Table])

case class Internal(@JsonProperty("createDatabaseQuery") createDatabaseQuery: String, @JsonProperty("table") table: List[Table],
                    @JsonProperty("insertQuery") insertQuery: String, @JsonProperty("tempTable") tempTable: String)

case class Table(@JsonProperty("query") query: String)
