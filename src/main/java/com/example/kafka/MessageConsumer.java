package com.example.kafka;

import com.example.kafka.config.ConsumerProperties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MessageConsumer extends Thread {

    private static final String TOPIC_NAME = "mindgate-upi-transaction-result";
    private static final long POLLING_TIMEOUT = 500;
    private final KafkaConsumer<String, String> kafkaConsumer;
    private static final DateFormat dateFormater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private static final File outputFile = new File(classLoader.getResource("data/output/simple.txt").getFile());

    public MessageConsumer() {
        this.kafkaConsumer = new KafkaConsumer<String, String>(ConsumerProperties.get());
    }

    public void run() {
        kafkaConsumer.subscribe(Arrays.asList(TOPIC_NAME));
        BufferedWriter bw = null;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outputFile, true));

            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(POLLING_TIMEOUT);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(dateFormater.format(new Date()) + " " + record.value());
                    writer.write(record.value());
                    writer.newLine();
                    writer.flush();
                }
            }
        } catch (WakeupException ex) {
            System.out.println("Exception caught " + ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            kafkaConsumer.close();
            System.out.println("After closing KafkaConsumer");
        }
    }

    public KafkaConsumer<String, String> getKafkaConsumer() {
        return this.kafkaConsumer;
    }
}

