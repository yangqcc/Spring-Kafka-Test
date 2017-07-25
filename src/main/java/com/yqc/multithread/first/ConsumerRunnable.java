package com.yqc.multithread.first;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.List;
import java.util.Properties;

/**
 * Created by yangqc on 2017/7/25
 */
public class ConsumerRunnable implements Runnable {

    private final KafkaConsumer<String, String> consumer;

    public ConsumerRunnable(String brokerList, String groupId, List<String> topics) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", brokerList);
        properties.put("group.id", groupId);
        properties.put("enable.auto.commit", "true");        //使用自动提交位移
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(topics);   // 本例使用分区副本自动分配策略
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread() + " ** 开始工作!");
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(200);   // 使用200ms作为获取超时时间
            for (ConsumerRecord<String, String> record : records) {
                // 这里面写处理消息的逻辑，本例中只是简单地打印消息
                System.out.println(Thread.currentThread().getName() + " consumed " + record.partition() + "the message with offset: " + record.offset());
            }
        }
    }
}
