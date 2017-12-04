package com.yqc.multithread.first;

import java.util.ArrayList;
import java.util.List;

/**
 * ConsumerGroup类
 * @author yangqc
 * @date 2017/7/25
 */
public class ConsumerGroup {

    private List<ConsumerRunnable> consumers;

    public ConsumerGroup(int consumerNum, String groupId, List<String> topics, String brokerList) {
        consumers = new ArrayList<>(consumerNum);
        for (int i = 0; i < consumerNum; ++i) {
            ConsumerRunnable consumerThread = new ConsumerRunnable(brokerList, groupId, topics);
            consumers.add(consumerThread);
        }
    }

    /**
     * 一个客户端一个线程
     */
    public void execute() {
        for (ConsumerRunnable task : consumers) {
            new Thread(task).start();
        }
    }
}
