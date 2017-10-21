package com.yxm.util.server.handler;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.yxm.util.DateUtil;
import com.yxm.util.cache.ClientCache;
import com.yxm.util.server.message.ChatMessage;
public class AgentEventHandler{
	private final SocketIOServer server;
	private final List<String> agentList = new ArrayList<String>();
	
	 @Autowired
	public AgentEventHandler(SocketIOServer server){
		this.server = server;
		
		
	}
	
	@OnConnect
	public void onConnect(SocketIOClient  client){
		System.out.println("***onConnect***"+client);
		//获取参数
		String agentId = client.getHandshakeData().getSingleUrlParam("agentId");
		
		//加入缓存
		ClientCache.getInstance().putAgentEventClient(agentId, client);
		agentList.add(agentId);
		System.out.println("agent OnConnect [agentId:"+agentId+" sessionId:"+client.getSessionId().toString()+"]");
	}
	
	@OnEvent(value="message")
	public void onEvent(SocketIOClient client,ChatMessage message){
		System.out.println("***message***"+client);
		String time = DateUtil.datetimeFormat.format(new Date());
		
		//构造消息
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setAgentid(message.getAgentid());
		chatMessage.setCalltype("out");
		chatMessage.setCreatetime(time);
		chatMessage.setMessage(message.getMessage());
		
		//发送
		ClientCache.getInstance().setAgentEventMessage(message.getAgentid(), "message", chatMessage);
		System.out.println("agent Onmessage [message:"+message+" sessionId:"+client.getSessionId().toString()+"]");
	}
	
	@OnDisconnect
	public void onDisconnect(SocketIOClient client){
		String agentId = client.getHandshakeData().getSingleUrlParam("agentId");
		ClientCache.getInstance().removeIMEventClient(agentId, client.getSessionId().toString());
		System.out.println("agent OnDisconnect [agentId:"+agentId+" sessionId:"+client.getSessionId().toString()+"]");
		agentList.remove(agentId);
	}
}












