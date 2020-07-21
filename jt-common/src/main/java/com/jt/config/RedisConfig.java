package com.jt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yuanzhibx
 * @Date 2020-07-17
 */
@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {

    @Value("${redis.nodes}")
    private String redisNodes;

    @Bean
    public JedisCluster jedisCluster() {
        Set<HostAndPort> nodeSet = new HashSet<>();
        // 动态获取 Redis 节点信息
        String[] clusters = redisNodes.split(",");
        // 循环遍历 nodes
        for (String cluster : clusters) {
            //将 nodes 通过 : 拆分为 host 和 port
            String host = cluster.split(":")[0];
            int port = Integer.parseInt(cluster.split(":")[1]);
            nodeSet.add(new HostAndPort(host, port));
        }
        return new JedisCluster(nodeSet);
    }

}
