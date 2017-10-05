package com.founder.focuss.webcc.entity.agent;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.WebSocketSession;

public class SocketAgent{
	private String agentId;
	private String agentName;
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	//private WebSocketSession session;

	/*public WebSocketSession getSession() {
		return session;
	}

	public void setSession(WebSocketSession session) {
		this.session = session;
	}*/
	
	public void addWebsocketSessionList(WebSocketSession session){
		sessionList.add(session);
	}

	public List<WebSocketSession> getWebSocketSessionList(){
		return sessionList;
	}
	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
}
