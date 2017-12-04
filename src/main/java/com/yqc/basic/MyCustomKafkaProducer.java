package com.yqc.basic;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author yangqc
 * @date 2017/6/7
 */
public class MyCustomKafkaProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
            producer.send(new ProducerRecord<>("cityos_235910425693165568_235910425961601024", "{\"Humidity\":\"90\", \"Temperature\":\"89\"}"));
        }
        producer.close();
    }

    /**
     * 创建topic,可以指定分区
     */
    public static void createTopics() {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        AdminClient adminClient = AdminClient.create(props);
        short a = 1;
        NewTopic newTopic = new NewTopic("yq", 3, a);
        List<NewTopic> topicList = new ArrayList<>();
        topicList.add(newTopic);
        adminClient.createTopics(topicList);
    }
}