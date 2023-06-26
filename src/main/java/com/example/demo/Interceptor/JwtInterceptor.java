package com.example.demo.Interceptor;
import java.util.*;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.demo.utils.JwtUtils;
public class    JwtInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        Map<String,Object>map=new HashMap<>();
        String token=request.getHeader("token");
        try {
            JwtUtils.verifyToken(token);
            request.setAttribute("userID",JwtUtils.getUserId(token));
            return true;
        }catch (SignatureVerificationException e){
            e.printStackTrace();
            map.put("message","无效签名");
        }catch(TokenExpiredException e){
            e.printStackTrace();
            map.put("message","token过期");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("message","token算法不一致");
        }catch (Exception e){
            e.printStackTrace();
            map.put("message","token无效");
        }
        //设置状态
        map.put("state",false);
        String json=new ObjectMapper().writeValueAsString(map);
        //相应json数据
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
