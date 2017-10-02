package com.yxm.util.cache;

import java.util.List;

import com.corundumstudio.socketio.SocketIOClient;
import com.google.common.collect.ArrayListMultimap;

public class AgentClient implements BaseClient{
	private ArrayListMultimap<String,com.corundumstudio.socketio.SocketIOClient> agentClientsMap=ArrayListMultimap.create();

	public List<SocketIOClient> getClients(String id) {
		return agentClientsMap.get(id);
	}

	public void putClient(String id, SocketIOClient client) {
		agentClientsMap.put(id, client);
		
	}

	public void removeClient(String id, String sessionId) {
		List<SocketIOClient> clients = this.getClients(id);
		for (SocketIOClient client : clients) {
			if(client.getSessionId().equals(sessionId)){
				clients.remove(client);
			};
		}
		if(clients.size()==0){
			agentClientsMap.removeAll(id);
		}
	}
}
