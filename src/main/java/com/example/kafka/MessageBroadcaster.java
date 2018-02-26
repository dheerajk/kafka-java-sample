package com.example.kafka;

import com.example.kafka.config.ProducerProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MessageBroadcaster {

    private static final String TOPIC_NAME = "test";
    private final String message;
    private final Producer producer;
    private final String name;
    private final int timeInterval;
    private final Timer timer;
    private final int totalNumberOfMessagesToBroadcast = 10;
    private static final DateFormat dateFormater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public MessageBroadcaster() {
        this("default-name", "default message", 0);
    }

    public MessageBroadcaster(String name, String message, int timeInterval) {
        this.producer = new KafkaProducer(ProducerProperties.get());
        this.message = message;
        this.name = name;
        this.timeInterval = timeInterval;
        this.timer = new Timer();
    }

    public void broadcast() {
        timer.scheduleAtFixedRate(new TimerTask() {
            int numberOfMessagesToBroadcasted = 0;

            @Override
            public void run() {
                numberOfMessagesToBroadcasted++;
                if (numberOfMessagesToBroadcasted >= totalNumberOfMessagesToBroadcast) {
                    cancel();
                }
                System.out.println(dateFormater.format(new Date()) + " Producer name: " + name + " Broadcasting " +
                        "message "
                        + message);
                ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC_NAME, message);
                producer.send(record);
            }
        }, 0, timeInterval);
    }

    public void broadcast(String customMessage) {
        System.out.println(dateFormater.format(new Date()) + " Producer name: " + name + " Broadcasting message "
                + customMessage);
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC_NAME, customMessage);
        producer.send(record);
    }

    public void shutdown() {
        producer.close();
    }

}
