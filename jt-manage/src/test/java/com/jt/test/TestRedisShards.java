package com.jt.test;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuanzhibx
 * @Date 2020-07-20
 */
public class TestRedisShards {

    @Test
    public void test01() {
        // 准备 list 集合, 添加节点信息
        List<JedisShardInfo> shard = new ArrayList<>();
        shard.add(new JedisShardInfo("172.18.55.141", 6379));
        shard.add(new JedisShardInfo("172.18.55.141", 6380));
        shard.add(new JedisShardInfo("172.18.55.141", 6381));

        // 创建分片对象, 存入 shard
        ShardedJedis shardedJedis = new ShardedJedis(shard);
        shardedJedis.set("shards", "Yuanzhibx");
        System.out.println(shardedJedis.get("shards"));
    }

}
