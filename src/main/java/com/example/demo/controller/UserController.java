package com.example.demo.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.utils.JwtUtils;
import com.example.demo.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @RequestMapping("/login")
    public Map<String,Object> login(int id,String username, String password)
    {
        Map<String,Object>map=new HashMap<>();
        try {
            Map<String,String>payload=new HashMap<>();
            payload.put("id",String.valueOf(id));
            payload.put("username",username);
            String token= JwtUtils.createToken(payload);
            map.put("state",true);
            map.put("msg","认证成功");
            map.put("token",token);
        }catch (Exception e){
            map.put("state",false);
            map.put("msg","认证失败");
        }
        return map;
    }
    @PostMapping("/test")
    public Map<String,Object>test(HttpServletRequest request,@RequestAttribute int userID){
        Map<String,Object>map=new HashMap<>();
        String token=request.getHeader("token");
        DecodedJWT verify = JwtUtils.verifyToken(token);
        System.out.println("用户id:"+ userID);
        System.out.println("用户名:"+verify.getClaim("username").asString());
        map.put("state",true);
        map.put("msg","请求成功");
        return map;
    }
    @PostMapping("/test2")
    public Map<String,Object>test2(HttpServletRequest request,@RequestAttribute int userID){
        Map<String,Object>map=new HashMap<>();
        String token=request.getHeader("token");
        DecodedJWT verify = JwtUtils.verifyToken(token);
        String data=request.getHeader("mmm");
        System.out.println("用户id:"+ userID);
        map.put("state",true);
        map.put("msg","请求成功");
        map.put("data",data);
        return map;
    }
}
