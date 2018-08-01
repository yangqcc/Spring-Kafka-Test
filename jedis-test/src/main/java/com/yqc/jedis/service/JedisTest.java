package com.yqc.jedis.service;

import redis.clients.jedis.Jedis;

/**
 * <p>title:</p>
 * <p>description:</p>
 *
 * @author yangqc
 * @date Created in 2018-07-30
 * @modified By yangqc
 */
public class JedisTest {

  public static void main(String[] args) {
    Jedis jedis = new Jedis("192.168.1.24", 6379);
    //redis执行set操作
    jedis.set("hello", "world");
    String value = jedis.get("hello");
    System.out.println(value);

    //string
    jedis.set("hello", "world");
    System.out.println(jedis.get("hello"));

    //hash
    jedis.hset("myhash", "f1", "v1");
    jedis.hset("myhash", "f2", "v2");

    System.out.println(jedis.hgetAll("myhash"));

    //list
    jedis.rpush("mylist", "1");
    jedis.rpush("mylist", "2");
    jedis.rpush("mylist", "3");
    System.out.println(jedis.lrange("mylist", 0, -1));

    //set
    jedis.sadd("myset", "a");
    jedis.sadd("myset", "b");
    jedis.sadd("myset", "b");
    System.out.println(jedis.smembers("myset"));

    //zset
    jedis.zadd("zset", 99, "tom");
    jedis.zadd("zset", 66, "peter");
    jedis.zadd("zset", 44, "james");
    System.out.println(jedis.zrangeWithScores("zset", 0, -1));
  }
}
