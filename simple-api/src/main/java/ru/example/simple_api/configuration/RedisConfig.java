package ru.example.simple_api.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.*;

import ru.example.simple_api.model.Task;

@Configuration
public class RedisConfig {

    @Value("${REDIS_HOST:localhost}")
    private String redisHost;

    @Value("${REDIS_PORT:6379}")
    private int redisPort;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisHost, redisPort);
    }

    @Bean
    public RedisTemplate<String, Task> redisTemplate() {
        RedisTemplate<String, Task> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        // String keys
        template.setKeySerializer(new StringRedisSerializer());

        // JSON values
        Jackson2JsonRedisSerializer<Task> serializer = new Jackson2JsonRedisSerializer<>(Task.class);
        ObjectMapper objectMapper = new ObjectMapper();
        serializer.setObjectMapper(objectMapper);

        template.setValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}
