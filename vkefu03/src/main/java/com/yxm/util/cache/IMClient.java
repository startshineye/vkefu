package com.yxm.util.cache;

import java.util.List;
import com.corundumstudio.socketio.SocketIOClient;
import com.google.common.collect.ArrayListMultimap;

public class IMClient implements BaseClient{
  //  private Map<String, SocketIOClient> imClientsMap = new ConcurrentHashMap<String,SocketIOClient>();
	private ArrayListMultimap<String, SocketIOClient> imClientsMap = ArrayListMultimap.create();
	
    public List<SocketIOClient> getClients(String id) {
    	return imClientsMap.get(id);
	}

	public void putClient(String id, SocketIOClient client) {
		imClientsMap.put(id, client);
	}

	public void removeClient(String id, String sessionId) {
		 List<SocketIOClient> clients = this.getClients(id);
		 for (SocketIOClient client : clients) {
			 if(client.getSessionId().toString().equals(sessionId)){
				 clients.remove(client);
				 break;
			 }
		}
		if(clients.size()==0){
			imClientsMap.removeAll(id);
		} 
		 
		
	}

}
