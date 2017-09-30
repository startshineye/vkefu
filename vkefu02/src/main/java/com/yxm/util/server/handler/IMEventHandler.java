package com.yxm.util.server.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.yxm.util.cache.ClientCache;
import com.yxm.util.server.message.ChatMessage;

/**
 * webcc事件处理
 * @author yxm
 * @date: 2017/09/29 14:44
 *
 */
@Component
public class IMEventHandler {
   private final SocketIOServer server;
   
   private final List<String> userlist = new ArrayList<String>();
   
   @Autowired
   public IMEventHandler(SocketIOServer server){
	   this.server = server;
   }
   
   
   // 建立连接
   @OnConnect
   public void onConnect(SocketIOClient client){
	   //获取参数
	   SocketIONamespace namespace = client.getNamespace();
	   String userId = client.getHandshakeData().getSingleUrlParam("userId");
	   String userName = client.getHandshakeData().getSingleUrlParam("userName");
	   String sessionId = client.getHandshakeData().getSingleUrlParam("sessionId");
	   System.out.println("*******onConnect**********");
	   System.out.println("userId:"+userId+" userName:"+userName+" sessionId:"+sessionId);
	   
	   //加入缓存
	   this.userlist.add(userId);
	   ClientCache.getInstance().putIMEventClient(userId, client);
	   
   }
   
   //消息入口
   @OnEvent(value="message")
   public void onEvent(SocketIOClient client,AckRequest request,ChatMessage message){
	   System.out.println(" 发送消息 ["+message+"]");
	   
	   //组装消息发送给用户端
	   ChatMessage chatMessage = new ChatMessage();
	   chatMessage.setUsername(message.getUsername());
	   chatMessage.setMessage(message.getMessage());
	   
	   //推送消息
	   //client.sendEvent("message", chatMessage);
	   for (String userid : userlist) {
		   System.out.println("userid"+userid);
		ClientCache.getInstance().sendIMEventMessage(userid, "message", chatMessage);
	    }
	   System.out.println("userlist"+userlist.toString());
	   System.out.println("*******message**********");
   }
   
   //断开连接
   @OnDisconnect 
   public void onDisconnect(SocketIOClient client){
	   String userId = client.getHandshakeData().getSingleUrlParam("userId");
	   System.out.println("*******OnDisconnect ********userId **"+userId);
	   ClientCache.getInstance().removeIMEventClient(userId, client.getSessionId().toString());
	   this.userlist.remove(userId);
   }
}
