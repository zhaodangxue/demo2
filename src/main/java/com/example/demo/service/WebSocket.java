package com.example.demo.service;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;

import org.springframework.stereotype.Component;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.WebSocketConfig;
@Component
@ServerEndpoint(value = "/websocket/{username}")
public class WebSocket {
//    private static AtomicInteger onlineCount=new AtomicInteger();
//    private static ConcurrentHashMap<String,Session>sessionPools=new ConcurrentHashMap<>();
//    public void sendMessage(Session session,String message)throws IOException{
//        if(session!=null){
//            synchronized(session){
//                System.out.println("send message:"+message);
//                session.getBasicRemote().sendText(message);
//            }
//        }
//    }
//    public void sendInfo(String userName,String message){
//        Session session=sessionPools.get(userName);
//        try {
//            sendMessage(session,message);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @OnOpen
//    public void onOpen(Session session,@PathParam("id")String username){
//        sessionPools.put(username,session);
//        addOnlineCount();
//        System.out.println(username+"加入webSocket！当前人数为"+getOnlineCount());
//        try {
//            sendMessage(session,"连接成功");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @OnClose
//    public void onClose(@PathParam("id")String username){
//        sessionPools.remove(username);
//        subOnlineCount();
//        System.out.println(username+"断开webSocket连接！当前人数为"+getOnlineCount());
//    }
//    @OnMessage
//    public void onMessage(String message,Session session2) throws IOException{
//        message="客户端："+message+"，已收到";
//        System.out.println(message);
//        for (Session session:sessionPools.values()){
//            try {
//                sendMessage(session,message);
//            }catch (Exception e){
//                e.printStackTrace();
//                continue;
//            }
//        }
//    }
//    @OnError
//    public void onError(Session session,Throwable throwable){
//        System.out.println("发生错误");
//        throwable.printStackTrace();
//    }
//    public static void addOnlineCount(){
//        onlineCount.incrementAndGet();
//    }
//    public static void subOnlineCount(){
//        onlineCount.decrementAndGet();
//    }
//    public static int getOnlineCount(){
//        return onlineCount.get();
//    }

    /**
     * 存储对象 map
     */
    public static final Map<String, javax.websocket.Session> sessionMap = new ConcurrentHashMap<>();

    /***
     * WebSocket 建立连接事件
     * 1.把登录的用户存到 sessionMap 中
     * 2.发送给所有人当前登录人员信息
     */
    @javax.websocket.OnOpen
    public void onOpen(javax.websocket.Session session, @javax.websocket.server.PathParam("username") String username) {

        // 搜索名称是否存在
        boolean isExist = sessionMap.containsKey(username);
        if (!isExist) {
            System.out.println(username + "加入了聊天室");
            sessionMap.put(username, session);
            showUserList();
        }
    }

    /**
     * WebSocket 关闭连接事件
     * 1.把登出的用户从 sessionMap 中剃除
     * 2.发送给所有人当前登录人员信息
     */
    @OnClose
    public void onClose(@javax.websocket.server.PathParam("username") String username) {
        if (username != null) {
            System.out.println(username + "退出了聊天室");
            sessionMap.remove(username);
            showUserList();
        }
    }

    /**
     * WebSocket 接受信息事件
     * 接收处理客户端发来的数据
     * @param message 信息
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println("接收到消息：" + message);
    }

    /**
     * WebSocket 错误事件
     * @param session 用户 Session
     * @param error 错误信息
     */
    @OnError
    public void onError(javax.websocket.Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 显示在线用户
     */
    private void showUserList() {
        System.out.println("------------------------------------------");
        System.out.println("当前在线用户");
        System.out.println("------------------------------------------");
        for (String username : sessionMap.keySet()) {
            System.out.println(username);
        }
        System.out.println("------------------------------------------");
        System.out.println();
    }

    /**
     * 发送消息到所有用户种
     * @param message 消息
     */
    private void sendAllMessage(String message) {
        try {
            for (javax.websocket.Session session : sessionMap.values()) {
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
