package com.yxm.web.service;
import com.yxm.web.domain.AgentOptLogVO;
public interface AgentOptLogService {
	void save(AgentOptLogVO agentOptLogVO);
    /**
     * 坐席操作日志记录
     * @param agentId
     * @param agentName
     * @param optId
     * @param entId
     * @param optCode
     * @param optResult
     * @param queueId
     * @param remark
     * @param threadId
     * @param toAgentId
     * @param toUserId
     * @param userId
     * @param userName
     */
	void agentOptLog(String agentId, String agentName,Integer entId,
			Integer optCode, Integer optResult, String queueId, String remark,
			Integer threadId, String toAgentId, String toUserId, String userId,
			String userName);

}
