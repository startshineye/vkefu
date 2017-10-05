package com.yxm.web.service.impl;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.yxm.web.dao.ThreadDao;
import com.yxm.web.domain.ThreadVO;
import com.yxm.web.service.ThreadService;
@Service("threadService")
public class ThreadServiceImpl implements ThreadService{
	
	@Resource(name="threadDao")
	private ThreadDao threadDao;
	
	public void save(ThreadVO threadVO) {
		threadDao.save(threadVO);
	}
	public ThreadVO getLastById(String userId) {
		return threadDao.getLastById(userId);
	}

	public void updateById(String param, String userId, String agentId) {
		threadDao.updateById(param, userId, agentId);
		
	}

	public List<ThreadVO> getThreadListByUserId(String userId) {
		return threadDao.getThreadListByUserId(userId);
	}
	
	public void updateStatisfactionBySessionIdAndAgentId(String stisfaction,Integer sessionId,
			String agentId) {
		threadDao.updateStatisfactionBySessionIdAndAgentId(stisfaction,sessionId,agentId);
	}
	public Integer[] getSessionIdByEndStatusAndUserId(String userId) {
		return threadDao.getSessionIdByEndStatusAndUserId(userId);
	}
	public List<ThreadVO> getThreadListByEndStatusAndAgentId(String agentId) {
		return  threadDao.getThreadListByEndStatusAndAgentId(agentId);
	}
	public void updateStatusBySessionIdAndAgentId(int i, Integer sessionId,
			String agentId) {
		threadDao.updateStatusBySessionIdAndAgentId(2,sessionId, agentId);
	}
	public ThreadVO getThreadByEndStatusAndAgentUserId(Integer agentUserId) {
		return threadDao.getThreadByEndStatusAndAgentUserId(agentUserId);
	}
	public ThreadVO getThreadByEndStatusAndUserIdAndAgentId(String userId,
			String agentId) {
		return threadDao.getThreadByEndStatusAndUserIdAndAgentId(userId, agentId);
	}
	public void agentEndByEndStatusAndUserIdAndAgentId(Integer status,
			String responseTime,Integer endCause, String endTime, String userId,
			String agentId) {
		threadDao.agentEndByEndStatusAndUserIdAndAgentId(status,responseTime,endCause,endTime,userId,agentId);
	}
}
