package com.example.kafka;

import com.example.kafka.config.ProducerProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MessageBroadcaster {

    private static final String TOPIC_NAME = "test";
    private final String message;
    private final Producer producer;

    MessageBroadcaster() {
        this("default message");
    }

    MessageBroadcaster(String message) {
        this.producer = new KafkaProducer(ProducerProperties.get());
        this.message = message;
    }

    public void broadcast() {
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC_NAME, message);
        producer.send(record);
    }

    public void broadcast(String customMessage) {
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC_NAME, customMessage);
        producer.send(record);
    }

    public void shutdown() {
        producer.close();
    }

}
