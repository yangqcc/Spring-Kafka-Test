package com.yqc.basic;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

/**
 *
 * @author yangqc
 * @date 2017/7/4
 */
public class KafkaConsumerRunner implements Runnable {

  private final AtomicBoolean closed = new AtomicBoolean(false);
  private final KafkaConsumer consumer;

  public KafkaConsumerRunner() {
    Properties props = new Properties();
    props.put("bootstrap.servers", "192.168.10.12:9092");
    props.put("group.id", "test");
    props.put("enable.auto.commit", "true");
    props.put("auto.commit.interval.ms", "1000");
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    consumer = new KafkaConsumer(props);
  }

  @Override
  public void run() {
    try {
      consumer.subscribe(Arrays.asList("cityos_235910425693165568_235910425961601024"));
      while (true) {
        ConsumerRecords records = consumer.poll(1000);
        records.forEach(s -> System.out.println(s));
      }
    } catch (WakeupException e) {
      System.out.println("Wakeup!");
      if (!closed.get()) {
        throw e;
      }
    } finally {
      System.out.println("shutdown！");
      consumer.close();
    }
  }

  // Shutdown hook which can be called from a separate thread
  public void shutdown() {
    closed.set(true);
    consumer.wakeup();
  }

  public static void main(String[] args) throws InterruptedException {
    KafkaConsumerRunner kafkaConsumerRunner = new KafkaConsumerRunner();
    new Thread(kafkaConsumerRunner).start();
    Thread.sleep(5000);
    //关闭
//        kafkaConsumerRunner.shutdown();
  }
}
