package com.example.demo.config;

import com.example.demo.entity.Book;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisTemplateConfiguration {
    @Bean
    public RedisTemplate<String,Object>redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object>redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance ,
                ObjectMapper.DefaultTyping.NON_FINAL);
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(om);
        //设置key和value的序列化规则
        redisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer<String>(String.class));
        redisTemplate.setValueSerializer(RedisSerializer.json());
        //设置hashKey和hashValue的序列化规则
        redisTemplate.setHashKeySerializer(new Jackson2JsonRedisSerializer<String>(String.class));
        redisTemplate.setHashValueSerializer(RedisSerializer.json());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
