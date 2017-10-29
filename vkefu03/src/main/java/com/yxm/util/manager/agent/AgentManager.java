package com.yxm.util.manager.agent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;

import com.yxm.web.domain.MessageVO;
import com.yxm.web.entity.agent.Agent;
public class AgentManager implements IAgentManager{
	//Feild
	private static Map<String,Agent> agentMap;
	private static Map<String,List<MessageVO>> messageMap;
	private static AgentManager manager = new AgentManager();
	public AgentManager(){
		agentMap = new ConcurrentHashMap<String, Agent>();
		messageMap = new ConcurrentHashMap<>();
	}
	public static IAgentManager getInstance(){
		return manager;
	}
	
	@Override
	public boolean addAgent(Agent agent) {
		if(agent!=null){
			String agentId = agent.getAgentId();
			if(!StringUtils.isBlank(agentId)){
				removeAgent(agentId);
				agentMap.put(agentId, agent);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeAgent(Agent agent) {
		if(agent!=null){
			String agentId = agent.getAgentId();
			return removeAgent(agentId);
		}
		return false;
		
	}
    private boolean removeAgent(String agentId){
    	if(!StringUtils.isBlank(agentId)){
    		agentMap.remove(agentId);
        	return true;
    	}
    	return false;
    }
	@Override
	public Agent getAgent(String agentId) {
		if(agentMap.containsKey(agentId)){
			return agentMap.get(agentId);
		}
		return null;
	}
	@Override
	public boolean contains(String agentId){
		if(agentMap.containsKey(agentId)){
			return true;
		}
		return false;
	}
	@Override
	public boolean updateAgentStatus(String agentId,String status) {
		Agent agent = agentMap.get(agentId);
		if(agent!=null){
			agent.setStatus(status);
			agentMap.put(agentId, agent);
			return true;
		}
		return false;
	}
	@Override
	public String getAgentStatus(String agentId) {
		Agent agent = agentMap.get(agentId);
		if(agent!=null){
			return agent.getStatus();
		}
		return "offline";
	}
	/*@Override
	public boolean updateAgentOptId(String agentId,Integer optId) {
		 Agent agent = agentMap.get(agentId);
		 if(agent!=null){
			 agent.setOptId(optId);
			 return true;
		 }
		return false;
	}
	@Override
	public Integer getAgentOptId(String agentId) {
		Agent agent = agentMap.get(agentId);
		if(agent!=null){
			return agent.getOptId();
		}
		return -1;
	}*/
	
	@Override
	public boolean putMessage(String agentId,MessageVO message){
		if(agentMap.containsKey(agentId)){
			//向用户对应的redis中添加消息
			List<MessageVO> list = messageMap.get(agentId);
			list.add(message);
			messageMap.put(agentId, list);
			System.err.println("坐席"+agentId+"putMessage 后的 messageMap"+messageMap.toString());
			return true;
		 }
		return false;
	}
	
	@Override
	public List<MessageVO> getMessage(String agentId){
		List<MessageVO> tempList = new ArrayList<MessageVO>();
		 if(agentMap.containsKey(agentId)){
			//从redis里面取出消息
			tempList = messageMap.get(agentId);
			List<MessageVO> messageList  =  new ArrayList<MessageVO>();
			messageMap.put(agentId, messageList);
			System.err.println("坐席"+agentId+"getMessage 后的 messageMap"+messageMap.toString());
			 return tempList;
		 }
		return tempList;
	}
	
	@Override
	public String getAllAgent(){
		return agentMap.toString();
	}
	@Override
	public boolean isLogin(String agentId) {
		// TODO Auto-generated method stub
		return false;
	}
}





