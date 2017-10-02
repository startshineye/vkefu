package com.yxm.util.server.handler;
import org.springframework.beans.factory.annotation.Autowired;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.yxm.util.server.message.ChatMessage;
public class AgentEventHandler{
	private final SocketIOServer server;
	
	 @Autowired
	public AgentEventHandler(SocketIOServer server){
		this.server = server;
	}
	
	@OnConnect
	public void onConnect(SocketIOClient  client){
		System.out.println("***onConnect***"+client);
	}
	
	@OnEvent(value="message")
	public void onEvent(SocketIOClient client,ChatMessage message){
		System.out.println("***message***"+client);
	}
	
	@OnDisconnect
	public void onDisconnect(SocketIOClient client){
		System.out.println("***onDisconnect***"+client);
	}
	
}












