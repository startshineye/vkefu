package com.founder.focuss.webcc.entity.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.WebSocketSession;

public class SocketUser{
	private String userId;
	private String userName;
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	public void addWebsocketSessionList(WebSocketSession session){
		sessionList.add(session);
	}

	public List<WebSocketSession> getWebSocketSessionList(){
		return sessionList;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
