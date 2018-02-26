# kafka-java-sample

Simple Kafka Integration using Java.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

```
Kafka
```
```
Java 8
```
```
Ubuntu
```
```
IntelliJ
```

### Installing Kafka

Download Kafka from following link and extract it

```
http://kafka.apache.org/downloads.html
```

## Running the application

There are few types of launchers for running this application based of cardinality. Like one producer one consumer and so on.
All launchers are part of `launchers` package.
Before you run the application you need to start zookeeper and kafka processes.

### Start ZooKeeper

Change your current directory to point to where you extracted Kafka and hit following command

```
./bin/zookeeper-server-start.sh config/zookeeper.properties
```

### Start Kafka server

Change your current directory to point to where you extracted Kafka and hit following command

```
./bin/kafka-server-start.sh config/server.properties
```

### Create a test topic

Change your current directory to point to where you extracted Kafka and hit following command

```
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
```

### Run application

In order to run application you need to choose one producer launcher and one consumer launcher.
Simply you can choose SingleConsumerLauncher and SingleProducerLauncher.
Just right click on it and run both the files in IntelliJ
In terminal both the processes will get started and then you can write a message on producer process which will get consumed and displayed on consumer process.
Type quit in both terminal to quit from both the aplications.
