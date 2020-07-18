package com.jt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.Jedis;

/**
 * @author Yuanzhibx
 * @Date 2020-07-17
 */
@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;

    /**
     * 将返回值交给 Spring 容器进行管理, 如果以后想要使用该对象, 则可以直接注入
     *
     * @return
     */
    @Bean
    public Jedis jedis() {
        return new Jedis(host, port);
    }

}
