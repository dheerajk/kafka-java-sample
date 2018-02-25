package com.example.kafka;

import com.example.kafka.config.ConsumerProperties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.util.Arrays;

public class MessageConsumer extends Thread {

    private static final String TOPIC_NAME = "test";
    private static final long POLLING_TIMEOUT = 100;
    private final KafkaConsumer<String, String> kafkaConsumer;

    public MessageConsumer() {
        this.kafkaConsumer = new KafkaConsumer<String, String>(ConsumerProperties.get());
    }

    public void run() {
        kafkaConsumer.subscribe(Arrays.asList(TOPIC_NAME));
        try {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(POLLING_TIMEOUT);
                for (ConsumerRecord<String, String> record : records)
                    System.out.println(record.value());
            }
        } catch (WakeupException ex) {
            System.out.println("Exception caught " + ex.getMessage());
        } finally {
            kafkaConsumer.close();
            System.out.println("After closing KafkaConsumer");
        }
    }

    public KafkaConsumer<String, String> getKafkaConsumer() {
        return this.kafkaConsumer;
    }
}

