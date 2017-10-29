package com.yxm.util.server.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.yxm.core.Context;
import com.yxm.util.DateUtil;
import com.yxm.util.OnlineUtil;
import com.yxm.util.cache.ClientCache;
import com.yxm.util.server.message.ChatMessage;
import com.yxm.web.domain.MessageVO;

/**
 * webcc事件处理
 * @author yxm
 * @date: 2017/09/29 14:44
 *
 */
public class IMEventHandler {
   private final SocketIOServer server;
   
   private final List<String> userlist = new ArrayList<String>();
  // private final List<SocketIOClient> iolist = new ArrayList<SocketIOClient>();
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
	   System.out.println("*******onConnect**********"+client+" userlist"+userlist);
	   System.out.println("userId:"+userId+" userName:"+userName+" sessionId:"+sessionId);
	   System.out.println("serverNameSpace:"+Context.NameSpaceEnum.IM.getNamespace());
	   /**
	    * 用户进入到对话连接
	    */
	   MessageVO message = OnlineUtil.getRequestMessage(userId,userName,client);
	   /**
	    * 用户加入缓存
	    */
	   if(!StringUtils.isBlank(userId)){
		    ClientCache.getInstance().putIMEventClient(userId, client);
	   }
	   /**
	    * 发送分配坐席后的消息
	    */
	   client.sendEvent(Context.MessageTypeEnum.STATUS.toString(), message);
   }
   //消息入口
   @OnEvent(value = "message")
   public void onEvent(SocketIOClient client,AckRequest request,ChatMessage message){
	   System.out.println(" 发送消息 ["+message+"]");
	   String time = DateUtil.datetimeFormat.format(new Date());
	   String userid = message.getUserid();
	   //组装消息
	   ChatMessage chatMessage = new ChatMessage();
	   chatMessage.setAgentid(message.getAgentid());
	   chatMessage.setCalltype("out");
	   chatMessage.setCreatetime(time);
	   chatMessage.setMessage(message.getMessage());
	   chatMessage.setSessionid(message.getSessionid());
	   chatMessage.setType(message.getType());
	   chatMessage.setUserid(userid);
	   //推送消息
	    if(!StringUtils.isBlank(userid)){
	    	chatMessage.setCalltype("in");
	    	ClientCache.getInstance().sendIMEventMessage(userid, "message", chatMessage);
	    	chatMessage.setCalltype("out");
	    	ClientCache.getInstance().sendAgentEventMessage(message.getAgentid(), "message", chatMessage);
	    }
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
