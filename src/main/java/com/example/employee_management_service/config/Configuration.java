package com.example.employee_management_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Value("${spring.data.redis.host}") String host;
    @Value("${spring.data.redis.password}") String password;
    @Value("${spring.data.redis.port}") int port;

//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        // You can customize the connection factory here (e.g., pool configuration).
//        return jedisConnectionFactory;
//    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConFactory
                = new JedisConnectionFactory();
        jedisConFactory.setHostName(host);
        jedisConFactory.setPassword(password);
        jedisConFactory.setPort(port);
        return jedisConFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        // You can customize the RedisTemplate here (e.g., serializer, key serializer).
        return redisTemplate;
    }

    @Bean
    public BCryptPasswordEncoder generateEncoder(){
        return new BCryptPasswordEncoder();
    }

}