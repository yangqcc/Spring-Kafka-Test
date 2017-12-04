package com.yqc.multithread.custompartition;


import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yangqc on 2017/7/25
 * @author yangqc
 */
public class MySamplePartitioner implements Partitioner {

    private final AtomicInteger counter = new AtomicInteger(new Random().nextInt());
    private Random random = new Random();

    /**
     * A cheap way to deterministically convert a number to a positive value. When the input is
     * positive, the original value is returned. When the input number is negative, the returned
     * positive value is the original value bit AND against 0x7fffffff which is not its absolutely
     * value.
     * <p>
     * Note: changing this method in the future will possibly cause partition selection not to be
     * compatible with the existing messages already placed on a partition.
     *
     * @param number a given number
     * @return a positive number.
     */
    private static int toPositive(int number) {
        return number & 0x7fffffff;
    }

    @Override
    public void configure(Map<String, ?> configs) {
    }

    //我的分区器算法实现
    //实际上是将默认分区器的代码copy过来，修改了public int partition(……){……}方法体的内容
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        int numPartitions = 2;
        int res = 1;
        if (value == null) {
            System.out.println("value is null");
            res = random.nextInt(numPartitions);
        } else {
            System.out.println("value is " + value + "\n hashcode is " + value.hashCode());
            res = Math.abs(value.hashCode()) % numPartitions;
        }
        System.out.println("data partitions is " + res);
        return res;
    }

    public void close() {
    }
}