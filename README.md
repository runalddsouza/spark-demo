# Spark Hadoop
 Experimenting Spark Connectivity with Remote Hadoop Cluster
 - Write Parquet and ORC files to HDFS.
 - Create Hive external tables over ORC data.
 - Create Hive internal tables.
 - Insert data into internal tables.
 - Write Hudi Datasets as Upsert to HDFS
 
 **Version**
 - Spark: 2.4.6
 - Scala: 2.12.8
 - Hudi: 0.6.0
 
 **Steps to Run on YARN:**
 - Build using Maven -> `mvn clean package`
 
 - Download Spark (https://spark.apache.org/downloads.html).
 
 - Set directory which contains the configuration files for the remote Hadoop cluster.<br>
   `export HADOOP_CONF_DIR=/path/to/hadoop/conf`
 
 - Submit Job using below commands:<br>
    `spark-submit --master yarn --deploy-mode cluster --class com.job.write.HdfsWriteJob 
    --conf spark.app.name=HdfsWriteJob spark-demo-1.0.jar`
 
    `spark-submit --master yarn --deploy-mode cluster --class com.job.database.HiveInsertJob 
    --conf spark.app.name=HiveInsertJob --conf spark.hadoop.hive.metastore.uris=thrift://<METASTORE_HOST>:9083
    spark-demo-1.0.jar`
 
    `spark-submit --master yarn --deploy-mode cluster --class com.job.write.HudiUpsertJob 
     --conf spark.app.name=HudiUpsertJob spark-demo-1.0.jar`
