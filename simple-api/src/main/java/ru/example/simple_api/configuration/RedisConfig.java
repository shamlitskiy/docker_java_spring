package ru.example.simple_api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.*;

import ru.example.simple_api.model.Task;

@Configuration
public class RedisConfig {

    @Value("${REDIS_HOST}")
    private String redisHost;

    @Value("${REDIS_PORT}")
    private int redisPort;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisHost, redisPort);
    }

    @Bean
    public RedisTemplate<String, Task> redisTemplate() {
        RedisTemplate<String, Task> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());

        Jackson2JsonRedisSerializer<Task> serializer = new Jackson2JsonRedisSerializer<>(Task.class);
        template.setValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}
