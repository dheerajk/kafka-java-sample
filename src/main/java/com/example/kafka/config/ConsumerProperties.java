package com.example.kafka.config;

import java.util.Properties;

import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.CLIENT_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

final public class ConsumerProperties {

    private static final String HOST = "localhost:9092";
    private static final String KEY_DESERIALIZER = "org.apache.kafka.common.serialization.ByteArrayDeserializer";
    private static final String VALUE_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
    private static final String GROUP_NAME = "testgroup";
    private static final String CLIENT_ID = "simple";

    public static Properties get() {
        Properties configProperties = new Properties();
        configProperties.put(BOOTSTRAP_SERVERS_CONFIG, HOST);
        configProperties.put(KEY_DESERIALIZER_CLASS_CONFIG, KEY_DESERIALIZER);
        configProperties.put(VALUE_DESERIALIZER_CLASS_CONFIG, VALUE_DESERIALIZER);
        configProperties.put(GROUP_ID_CONFIG, GROUP_NAME);
        configProperties.put(CLIENT_ID_CONFIG, CLIENT_ID);
        return configProperties;
    }

}
