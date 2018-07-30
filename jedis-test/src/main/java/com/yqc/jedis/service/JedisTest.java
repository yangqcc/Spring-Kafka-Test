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
  }
}
