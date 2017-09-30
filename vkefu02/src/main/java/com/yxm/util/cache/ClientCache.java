package com.yxm.util.cache;

import java.util.List;

import com.corundumstudio.socketio.SocketIOClient;

public class ClientCache {
   private static ClientCache clientCache = new ClientCache();
   
   private IMClient imClients = new IMClient();
   
   private AgentClient agentClients = new AgentClient();
	
   private ClientCache(){
	   
   }
   
   public static ClientCache getInstance(){
	   return clientCache;
   }
   
   //IM
   public void setImClients(IMClient imClients){
	   this.imClients=imClients;
   }
   
   public void putIMEventClient(String id , SocketIOClient imClient){
	   imClients.putClient(id, imClient);
   }
   
   public void removeIMEventClient(String id , String sessionid){
	   imClients.removeClient(id, sessionid);
   }
   
   public void sendIMEventMessage(String id,String event,Object data){
	   List<SocketIOClient> clients = imClients.getClients(id);
	   for (SocketIOClient client : clients) {
		   client.sendEvent(event, data);
	   }
   }
   //Agent
   public void setAgentClients(AgentClient agentClients){
	   this.agentClients=agentClients;
   }
   
   public void putAgentEventClient(String id ,SocketIOClient agentClient){
	   agentClients.putClient(id, agentClient);
   }
   
   public void removeAgentEventClient(String id,String sessionId){
	   agentClients.removeClient(id, sessionId);
   }
   
   public void setAgentEventMessage(String id,String event,Object data){
	   List<SocketIOClient> clients = agentClients.getClients(id);
	   for (SocketIOClient client : clients) {
		   client.sendEvent(event, data);
	}
   }
}
