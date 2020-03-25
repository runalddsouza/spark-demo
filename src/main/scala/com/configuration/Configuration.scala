package com.configuration

import java.io.File

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.scala.DefaultScalaModule

object Configuration {
  def load(configPath: String): SparkConfiguration = new ObjectMapper(new YAMLFactory)
    .registerModule(DefaultScalaModule).readValue(new File(configPath), classOf[SparkConfiguration])
}

case class SparkConfiguration(@JsonProperty("master") val master: String, @JsonProperty("hive") val hive: Hive,
                              @JsonProperty("hdfs") val hdfs: Hdfs)

case class Hdfs(@JsonProperty("uri") val uri: String, @JsonProperty("readLocation") val readPath: String,
                @JsonProperty("writeLocation") val writePath: String)

case class Hive(@JsonProperty("metastore") val metastore: String, @JsonProperty("external") val external: External,
                @JsonProperty("internal") val internal: Internal)

case class External(@JsonProperty("createDatabaseQuery") val createDatabaseQuery: String, @JsonProperty("table") val table: List[Table])

case class Internal(@JsonProperty("createDatabaseQuery") val createDatabaseQuery: String, @JsonProperty("table") val table: List[Table],
                    @JsonProperty("insertQuery") val insertQuery: String, @JsonProperty("tempTable") val tempTable: String)

case class Table(@JsonProperty("query") val query: String)
