package com.jt.test;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yuanzhibx
 * @Date 2020-07-20
 */
public class TestCluster {

    @Test
    public void test01() {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("172.18.55.141", 7000));
        nodes.add(new HostAndPort("172.18.55.141", 7001));
        nodes.add(new HostAndPort("172.18.55.141", 7002));
        nodes.add(new HostAndPort("172.18.55.141", 7003));
        nodes.add(new HostAndPort("172.18.55.141", 7004));
        nodes.add(new HostAndPort("172.18.55.141", 7005));

        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("AAA", "Yuanzhibx DEMO");
        System.out.println(jedisCluster.get("AAA"));
    }

}
