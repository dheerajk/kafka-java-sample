package com.example.kafka;

import com.example.kafka.config.ConsumerProperties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.util.Arrays;
import java.util.Scanner;

public class Consumer {

    private static Scanner inputScanner;
    private static final String EXIT = "quit";

    public static void main(String[] commandLineArguments) throws Exception {

        inputScanner = new Scanner(System.in);

        ConsumerThread consumerRunnable = new ConsumerThread();
        consumerRunnable.start();
        String line = "";
        while (!line.equals(EXIT)) {
            line = inputScanner.next();
        }
        consumerRunnable.getKafkaConsumer().wakeup();
        System.out.println("Stopping consumer .....");
        consumerRunnable.join();
    }

    private static class ConsumerThread extends Thread {
        private static final String TOPIC_NAME = "test";
        private static final long POLLING_TIMEOUT = 100;
        private KafkaConsumer<String, String> kafkaConsumer;

        public void run() {
            kafkaConsumer = new KafkaConsumer<String, String>(ConsumerProperties.get());
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
}

