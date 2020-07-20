package com.jt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.List;

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
    public ShardedJedis shardedJedis() {
        String[] nodes = redisNodes.split(",");
        // 动态获取 Redis 节点信息
        List<JedisShardInfo> list = new ArrayList<>();
        // 循环遍历 nodes
        for (String node : nodes) {
            //将 nodes 通过 : 拆分为 host 和 port
            String host = node.split(":")[0];
            int port = Integer.parseInt(node.split(":")[1]);
            list.add(new JedisShardInfo(host, port));
        }
        return new ShardedJedis(list);
    }

}
