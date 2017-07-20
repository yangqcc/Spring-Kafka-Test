package com.yqc;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by yangqc on 2017/6/7.
 */
public class CustomKafkaCustomer {
    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executor.execute(new MyRunnable(Integer.toString(i)));
        }
    }
}

class MyRunnable implements Runnable {

    private Properties props = new Properties();
    private KafkaConsumer<String, String> consumer;
    private String id;

    public MyRunnable(String id) {
        this.id = id;
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", this.id);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("foo"));
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "---启动!");
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("%s,offset = %d, key = %s, value = %s%n", Thread.currentThread().getName(), record.offset(), record.key(), record.value());
            }
        }
    }
}
