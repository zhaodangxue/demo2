package com.example.demo.utils;

import jakarta.annotation.Resource;
import org.apache.ibatis.cache.Cache;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.web.servlet.filter.ApplicationContextHeaderFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MybatisRedisCache implements Cache {
    private static final Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String id;
    private  String data;
    private RedisTemplate redisTemplate;
    private static final long EXPIRE_TIME_IN_MINUTES = 10; // redis过期时间
    private RedisTemplate getRedisTemplate(){
        if(redisTemplate==null){
            redisTemplate=ApplicationContextHolder.getBean("redisTemplate");
        }
        return redisTemplate;
    }
    public MybatisRedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>MybatisRedisCache:id="+id);
        this.id = id;
        this.data="";
    }
    @Override
    public String getId() {
        return id;
    }
    @Override
    public void putObject(Object key, Object value)  {
        try{
            redisTemplate=getRedisTemplate();
            this.data=key.toString().substring(key.toString().indexOf("select"),key.toString().indexOf("Mybatis"));
            if(value!=null){
                redisTemplate.opsForValue().set(this.data,value,EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
            }
            logger.debug("Put query result to redis");
        }catch (Throwable t){
            logger.error("Redis put failed",t);
        }
    }

    @Override
    public Object getObject(Object key) {
        try{
            redisTemplate=getRedisTemplate();
            logger.debug("Get cached query result from redis");
            return redisTemplate.opsForValue().get(key.toString());
        }catch (Throwable t){
            logger.error("Redis get failed, fail over to db",t);
            return null;
        }
    }

    @Override
    public Object removeObject(Object key) {
        try {
            redisTemplate=getRedisTemplate();
            redisTemplate.delete(key.toString());
            logger.info("Remove cached query result from redis");
        }catch (Throwable t) {
            logger.error("Redis remove failed", t);
        }
        return null;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    @Override
    public void clear() {
        redisTemplate=getRedisTemplate();
        Set<String>keys=redisTemplate.keys("*"+this.id+"*");
        if(!CollectionUtils.isEmpty(keys)){
            redisTemplate.delete(keys);
        }
        logger.debug("Clear all the cached query result from redis");
    }

    @Override
    public int getSize() {
        return 0;
    }
}
