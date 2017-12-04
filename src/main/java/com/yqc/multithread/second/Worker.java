package com.yqc.multithread.second;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * Created by yangqc on 2017/7/25
 * @author yangqc
 */
public class Worker implements Runnable {

    private ConsumerRecord<String, String> consumerRecord;

    public Worker(ConsumerRecord record) {
        this.consumerRecord = record;
    }

    @Override
    public void run() {
        // 这里写你的消息处理逻辑，本例中只是简单地打印消息
        System.out.println(Thread.currentThread().getName() + " consumed " + consumerRecord.partition() + "the message with offset: " + consumerRecord.offset());
    }
}