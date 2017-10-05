package com.yxm.web.service;
import java.util.List;

import com.yxm.web.domain.AgentUserVO;
import com.yxm.web.domain.PaginationVO;
public interface AgentUserService {
	/**
	 * 保存 (自动包含sessionId)
	 * @param sessionVO
	 */
	 void saveAutoSessionId(AgentUserVO agentUserVO);
	 /**
	 * 保存 (sessionId自己构造)
	 * @param sessionVO
	 */
	 void saveUnAutoSessionId(AgentUserVO agentUserVO);
    /**
     * userId和agentId获取session
     * @param userId
     * @param agentId
     * @return
     */
	 AgentUserVO getAgentUserByUserIdAndAgentId(String userId, String agentId);
	/**
	 *new  获取未结束的session
	 * @param agentId
	 * @return
	 */
	List<AgentUserVO> getAgentUserListByEndStatusAndAgentId(String agentId);
	/**
	 * 根据userId获取未结束session
	 * @param userId
	 * @return
	 */
	List<AgentUserVO> getAgentUserListByEndStatusAndUserId(String userId);
	/**
	 * new sessionid与agentid唯一性获取agentuser
	 * @param sessionId
	 * @param agentId
	 * @return
	 */
	AgentUserVO getAgentUserBySessionIdAndAgentId(Integer sessionId,String agentId);
	/**
	 * 
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	PaginationVO<AgentUserVO> getByPage(String userId, Integer pageNo,Integer pageSize);
	/**
	 * huoqu fuwu shuliang
	 * @param agentId
	 * @return
	 */
	Integer getSernumByEndStatusAndAgentId(String agentId);
	/**
	 * 坐席单独操作时候(根据agentid更改状态)
	 * @param status
	 * @param agentId
	 */
	void updateStatusAndChatTypeByAgentId(Integer status,Integer chatType,String agentId);
	/**
	 * 修改status和聊天类型
	 * @param i
	 * @param j
	 * @param userId
	 * @param toagentId
	 */
	void updateStatusAndChatTypeByUserIdAndAgentId(Integer status,Integer chatType, String userId,
			String toagentId);
	
	/**
	 * 用户发送聊天消息
	 * @param chatStatusEnd
	 * @param chattype
	 * @param userId
	 * @return
	 */
	List<AgentUserVO> getAgentUserListByEndStatusAndChatTypeAndUserId(
			Integer chatStatusEnd, Integer chattype, String userId);
	/**
	 * 坐席发送消息
	 * @param chatStatusEnd
	 * @param chattypeChat
	 * @param agentId
	 * @return
	 */
	List<AgentUserVO> getAgentUserListByEndStatusAndChatTypeAndAgentId(
			Integer chatStatusEnd, Integer chattypeChat, String agentId);
	
}
