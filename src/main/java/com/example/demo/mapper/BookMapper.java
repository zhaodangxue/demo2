package com.example.demo.mapper;

import com.example.demo.entity.Book;
import com.example.demo.utils.MybatisRedisCache;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheEvict;

@Mapper
@CacheNamespace(implementation = MybatisRedisCache.class,eviction = MybatisRedisCache.class)
public interface BookMapper {
    @Select("select * from book where id=#{id}")
    Book getBook(int id);
    @Delete("delete from book where id=#{id}")
    void deleteBook(int id);
    @Update("update book set name=#{name} where id=#{id}")
    void updateBook(int id,String name);
}
