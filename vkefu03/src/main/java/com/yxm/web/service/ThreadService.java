package com.founder.focuss.webcc.service;
import java.util.List;

import com.founder.focuss.webcc.domain.ThreadVO;
public interface ThreadService {
	/**
	 * 保存聊天信息
	 * @param threadVO
	 */
	void save(ThreadVO threadVO);
	/**
	 * 根据userId获取最新threadVO(agentid)
	 * @param userId
	 * @return
	 */
	 ThreadVO getLastById(String userId);
	 /**
	  * 满意度指标加入到数据库中
	  * @param param
	  * @param userId
	  * @param agentId
	  */
	void updateById(String param,String userId,String agentId);
	/**
	 * 
	 * @param userId
	 * @return
	 */
	List<ThreadVO> getThreadListByUserId(String userId);
	 
    /******
     * 更改满意度评价
     * @param sessionId
     * @param agentId
     */
	void updateStatisfactionBySessionIdAndAgentId(String stisfaction,
			Integer sessionId, String agentId);
    /***
     *根据userId和status获取sessionId 
     * @param userId
     * @return Integer
     */
	Integer[] getSessionIdByEndStatusAndUserId(String userId);

	List<ThreadVO> getThreadListByEndStatusAndAgentId(String agentId);

	void updateStatusBySessionIdAndAgentId(int i, Integer sessionId,
			String agentId);
	
	/**
	 * new 获取本次聊天
	 * @param agentUserId
	 * @return
	 */
	ThreadVO getThreadByEndStatusAndAgentUserId(Integer agentUserId);
	/**
	 *  new 获取本次聊天
	 * @param userId
	 * @param agentId
	 * @return
	 */
	ThreadVO getThreadByEndStatusAndUserIdAndAgentId(String userId,
			String agentId);
	/**
	 * 
	 * @param i
	 * @param responseTime
	 * @param j
	 * @param endTime
	 * @param userId
	 * @param agentId
	 */
	public void agentEndByEndStatusAndUserIdAndAgentId(Integer status,
			String responseTime,Integer endCause, String endTime, String userId,
			String agentId);
}
