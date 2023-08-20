package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.mapper.BookMapper;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Resource
    BookMapper bookMapper;
    @RequestMapping("/book")
    public Book getBook(int id){
        return bookMapper.getBook(id);
    }
    @RequestMapping("/deleteBook")
    public String deleteBook(int id){
        bookMapper.deleteBook(id);
        return "success";
    }
    @RequestMapping("/updateBook")
    public String updateBook(int id,String name){
        bookMapper.updateBook(id,name);
        return "success";
    }
}
