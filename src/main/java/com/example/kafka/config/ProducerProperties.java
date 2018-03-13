package com.example.kafka.config;

import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

final public class ProducerProperties {

    private static final String HOST = "localhost:9092";
    private static final String KEY_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    private static final String VALUE_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";

    public static Properties get() {
        Properties configProperties = new Properties();
        configProperties.put(BOOTSTRAP_SERVERS_CONFIG, HOST);
        configProperties.put(KEY_SERIALIZER_CLASS_CONFIG, KEY_SERIALIZER);
        configProperties.put(VALUE_SERIALIZER_CLASS_CONFIG, VALUE_SERIALIZER);
        return configProperties;
    }
}
