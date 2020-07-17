package com.jt.test;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

/**
 * @author Yuanzhibx
 * @Date 2020-07-17
 */
public class TestRedis {

    /**
     * Spring 整合 Redis
     */
    @Test
    public void testString01() {
        //1. 创建 jedis 对象
        Jedis jedis = new Jedis("172.18.55.141", 6379);
        //2. 操作 redis
        jedis.set("id", "Hello Redis");
        String value = jedis.get("id");
        System.out.println(value);
    }

    @Test
    public void testString02() {
        //1. 创建 jedis 对象
        Jedis jedis = new Jedis("172.18.55.141", 6379);
        //2. 判断当前数据是否存在
        if (jedis.exists("id")) {
            System.out.println(jedis.get("id"));
        } else {
            jedis.set("id", "Hello Yuanzhibx");
        }
    }

    /**
     * Long setnx(final String key, final String value)
     *      如果 redis 中没有对应的值时修改数据, 有则不修改数据
     * 1. 简化是否存在的判断
     * 2. 没数据时修改数据, 否则不修改
     */
    @Test
    public void testString03() {
        //1. 创建 jedis 对象
        Jedis jedis = new Jedis("172.18.55.141", 6379);
        // 清空所有的 redis 缓存
        jedis.flushAll();
        jedis.set("name", "Yuanzhibx01");
        jedis.set("name", "Yuanzhibx02");
        System.out.println(jedis.get("name"));
    }


    /**
     * String setex(final String key, final int seconds, final String value)
     *      原子性操作, 要么同时成功, 要么同时失败
     */
    @Test
    public void testString04() throws InterruptedException {
        Jedis jedis = new Jedis("172.18.55.141", 6379);
        jedis.flushAll();

        /*
            非原子性操作
            程序报错后可能导致后面代码无法执行
         */
        jedis.set("name", "Yuanzhibx");
        //超时时间
        jedis.expire("name", 20);
        Thread.sleep(2000);
        System.out.println("剩余存活时间:  " + jedis.ttl("name"));

        /*
            原子性操作
         */
        jedis.setex("username", 20, "原子性操作");
        System.out.println(jedis.get("username"));
    }

    /**
     * String set(String key, String value, SetParams params)
     *
     * SetParams 参数说明:
     *      1. NX       只有 key 不存在时才能修改
     *      2. XX       只有 key 存在时才能修改
     *      3. PX       添加时间单位是毫秒
     *      4. EX       添加时间单位是秒
     */
    @Test
    public void testString05() {
        Jedis jedis = new Jedis("172.18.55.141", 6379);
        jedis.flushAll();
        SetParams params = new SetParams();
        params.nx().ex(20);
        jedis.set("addr", "demo01", params);
        jedis.set("addr", "demo02", params);
        System.out.println(jedis.get("addr"));
        System.out.println("剩余存活时间:  " + jedis.ttl("addr"));
    }

}
