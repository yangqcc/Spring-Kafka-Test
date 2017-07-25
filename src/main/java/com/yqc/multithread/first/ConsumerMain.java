package com.yqc.multithread.first;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangqc on 2017/7/25
 */
public class ConsumerMain {
    public static void main(String[] args) {
        String brokerList = "localhost:9092";
        String groupId = "testGroup1";
        List<String> topics = new ArrayList<>();
        topics.add("foo");
        topics.add("db_topic");
        int consumerNum = 3;
        ConsumerGroup consumerGroup = new ConsumerGroup(consumerNum, groupId, topics, brokerList);
        consumerGroup.execute();
    }
}
