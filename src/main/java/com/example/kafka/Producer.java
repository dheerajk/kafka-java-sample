package com.example.kafka;

import com.example.kafka.config.ProducerProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Scanner;

public class Producer {

    private static Scanner inputScanner;
    private static final String TOPIC_NAME = "test";
    private static final String EXIT = "quit";

    public static void main(String[] commandLineArguments) throws Exception {

        inputScanner = new Scanner(System.in);
        System.out.println("Enter message or type quit to exit: ");

        org.apache.kafka.clients.producer.Producer producer = new KafkaProducer(ProducerProperties.get());
        String line = inputScanner.nextLine();
        while (!line.equals(EXIT)) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC_NAME, line);
            producer.send(record);
            line = inputScanner.nextLine();
        }
        inputScanner.close();
        producer.close();
    }

}
