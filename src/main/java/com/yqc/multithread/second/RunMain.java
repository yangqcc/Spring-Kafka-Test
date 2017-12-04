package com.yqc.multithread.second;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangqc on 2017/7/25
 * @author yangqc
 */
public class RunMain {
    public static void main(String[] args) {
        String brokerList = "localhost:9092";
        String groupId = "group2";
        List<String> topics = new ArrayList<>();
        topics.add("yq");
        int workerNum = 5;

        ConsumerHandler consumers = new ConsumerHandler(brokerList, groupId, topics);
        consumers.execute(workerNum);
        try {
            Thread.sleep(1000000);
        }
        catch (InterruptedException ignored) {
        }
        consumers.shutdown();
    }
}
