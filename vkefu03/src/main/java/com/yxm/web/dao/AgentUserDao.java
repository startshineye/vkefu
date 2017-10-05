package com.yxm.web.dao;
import java.util.List;

import com.yxm.web.domain.AgentUserVO;
public interface AgentUserDao {
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
	 * 根据userId获取聊天总数
	 * @param userId
	 * @return
	 */
	Long getTotalByUserId(String userId);
	  /**
	   * 分页获取thread
	   * @param pageNo
	   * @param pageSize
	   * @return
	   */
	List<AgentUserVO> getAgentUserListByPage(Integer pageNo, Integer pageSize,
			String userId);
	
	Integer getSernumByEndStatusAndAgentId(String agentId);
    /**
     * 改变状态
     * @param status
     * @param agentId
     */
	void updateStatusAndChatTypeByAgentId(Integer status,Integer chatType,String agentId);
	/**
	 * 修改状态和聊天类型
	 * @param status
	 * @param chatType
	 * @param userId
	 * @param toagentId
	 */
	void updateStatusAndChatTypeByUserIdAndAgentId(Integer status, Integer chatType,
			String userId, String toagentId);
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
