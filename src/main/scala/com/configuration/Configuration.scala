package com.configuration

import java.io.File

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory

object Configuration {
  def load(configPath: String): SparkConfiguration = new ObjectMapper(new YAMLFactory)
    .readValue(new File(configPath), classOf[SparkConfiguration])
}

case class SparkConfiguration(@JsonProperty("master") val master: String, @JsonProperty("hive") val hive: Hive,
                              @JsonProperty("hdfs") val hdfs: Hdfs)

case class Hdfs(@JsonProperty("uri") val uri: String, @JsonProperty("readLocation") val readPath: String,
                @JsonProperty("writeLocation") val writePath: String)

case class Hive(@JsonProperty("metastore") val metastore: String, @JsonProperty("createDatabaseQuery") val createDatabaseQuery: String,
                @JsonProperty("createTableQuery") val createTableQuery: String, @JsonProperty("insertQuery") val insertQuery: String,
                @JsonProperty("tempTable") val tempTable: String)
