package com.founder.focuss.webcc.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.founder.focuss.webcc.dao.ThreadDao;
import com.founder.focuss.webcc.domain.ThreadVO;
import com.founder.focuss.webcc.service.ThreadService;


@Service("threadService")
public class ThreadServiceImpl implements ThreadService{
	
	@Resource(name="threadDao")
	private ThreadDao threadDao;
	
	@Override
	public void save(ThreadVO threadVO) {
		threadDao.save(threadVO);
	}
	@Override
	public ThreadVO getLastById(String userId) {
		return threadDao.getLastById(userId);
	}

	@Override
	public void updateById(String param, String userId, String agentId) {
		threadDao.updateById(param, userId, agentId);
		
	}

	@Override
	public List<ThreadVO> getThreadListByUserId(String userId) {
		return threadDao.getThreadListByUserId(userId);
	}
	
    @Override
	public void updateStatisfactionBySessionIdAndAgentId(String stisfaction,Integer sessionId,
			String agentId) {
		threadDao.updateStatisfactionBySessionIdAndAgentId(stisfaction,sessionId,agentId);
	}
	@Override
	public Integer[] getSessionIdByEndStatusAndUserId(String userId) {
		return threadDao.getSessionIdByEndStatusAndUserId(userId);
	}
	@Override
	public List<ThreadVO> getThreadListByEndStatusAndAgentId(String agentId) {
		return  threadDao.getThreadListByEndStatusAndAgentId(agentId);
	}
	@Override
	public void updateStatusBySessionIdAndAgentId(int i, Integer sessionId,
			String agentId) {
		threadDao.updateStatusBySessionIdAndAgentId(2,sessionId, agentId);
	}
	@Override
	public ThreadVO getThreadByEndStatusAndAgentUserId(Integer agentUserId) {
		return threadDao.getThreadByEndStatusAndAgentUserId(agentUserId);
	}
	@Override
	public ThreadVO getThreadByEndStatusAndUserIdAndAgentId(String userId,
			String agentId) {
		return threadDao.getThreadByEndStatusAndUserIdAndAgentId(userId, agentId);
	}
	@Override
	public void agentEndByEndStatusAndUserIdAndAgentId(Integer status,
			String responseTime,Integer endCause, String endTime, String userId,
			String agentId) {
		threadDao.agentEndByEndStatusAndUserIdAndAgentId(status,responseTime,endCause,endTime,userId,agentId);
	}
}
