package com.yqc.jedis.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

/**
 * <p>title:</p>
 * <p>description:</p>
 *
 * @author yangqc
 * @date Created in 2018-07-31
 * @modified By yangqc
 */
public class JedisPoolTest {

  /**
   * 获取线程池
   */
  public JedisPool getJedisPool() {
    GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
    JedisPool jedisPool = new JedisPool(poolConfig, "192.168.1.24", 6379);
    return jedisPool;
  }

  @Test
  public void test() {
    JedisPool jedisPool = getJedisPool();
    Jedis jedis = jedisPool.getResource();
  }

  /**
   * 批量删除
   */
  public void mdel(List<String> keys) {
    Jedis jedis = getJedisPool().getResource();
    Pipeline pipeline = jedis.pipelined();
    keys.forEach(key -> pipeline.del(key));
    //执行命令
    pipeline.sync();
  }

  /**
   * jedis执行lua脚本
   */
  @Test
  public void luaTest() {
    Jedis jedis = getJedisPool().getResource();
    String key = "counter";
    String script = "return redis.call('get',KEYS[1])";
    Object result = jedis.eval(script, 1, key);
    System.out.println(result);
  }

  @Test
  public void luaTest2() {
    Jedis jedis = getJedisPool().getResource();
    String key = "counter";
    String script = "return redis.call('get',KEYS[1])";
    String scriptSha = jedis.scriptLoad(script);
    Object result = jedis.evalsha(scriptSha, 1, key);
    System.out.println(result);
  }

  @Test
  public void batchExecute() {
    Jedis jedis = getJedisPool().getResource();
    Pipeline pipeline = jedis.pipelined();
    pipeline.set("hello", "pipeline");
    pipeline.set("counter", "1");
    pipeline.incr("counter");
    List<Object> result = pipeline.syncAndReturnAll();
    result.forEach(s -> System.out.println(s));
  }

  @Test
  public void testSort() {
    List<Person> personList = new ArrayList<>();
    personList.add(new Person("qicheng", 123));
    personList.add(new Person("shijai1", 21));
    personList.add(new Person("wenhai1", 21));
    personList.add(new Person("chaochao", 2121));
    Collections.sort(personList);
    personList.forEach(person -> System.out.println(person));
  }
}

class Person implements Comparable<Person> {

  private String name;
  private int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        ", age=" + age +
        '}';
  }

  @Override
  public int compareTo(Person o) {
    if (o == null) {
      throw new NullPointerException("参数不能为空!");
    }
    return this.age - o.age;
  }
}
