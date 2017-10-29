package com.yxm.util.manager.agent;
import java.util.List;

import com.yxm.web.domain.MessageVO;
import com.yxm.web.entity.agent.Agent;
public interface IAgentManager {
   boolean addAgent(Agent agent);
   boolean removeAgent(Agent agent);
   Agent getAgent(String agentId);
   boolean isLogin(String agentId);
   boolean contains(String agentId);
   boolean updateAgentStatus(String agentId,String status);
   String getAgentStatus(String agentId);
   boolean putMessage(String agentId, MessageVO message);
   List<MessageVO> getMessage(String agentId);
   String getAllAgent();
}
