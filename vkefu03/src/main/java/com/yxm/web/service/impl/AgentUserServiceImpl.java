package com.founder.focuss.webcc.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.focuss.webcc.dao.AgentUserDao;
import com.founder.focuss.webcc.domain.AgentUserVO;
import com.founder.focuss.webcc.domain.PaginationVO;
import com.founder.focuss.webcc.domain.ThreadVO;
import com.founder.focuss.webcc.service.AgentUserService;
@Service("agentUserService")
public class AgentUserServiceImpl implements AgentUserService{
	
	@Autowired
    private AgentUserDao agentUserDao;
	@Override
	public AgentUserVO getAgentUserByUserIdAndAgentId(String userId,
			String agentId) {
		return agentUserDao.getAgentUserByUserIdAndAgentId(userId, agentId);
	}
	@Override
	public void saveAutoSessionId(AgentUserVO agentUserVO) {
		agentUserDao.saveAutoSessionId(agentUserVO);
	}
	@Override
	public void saveUnAutoSessionId(AgentUserVO agentUserVO) {
		agentUserDao.saveUnAutoSessionId(agentUserVO);
	}
	/*@Override
	public List<AgentUserVO> getAgentUserListByEndStatusAndSessionId(
			Integer sessionId) {
		return agentUserDao.getAgentUserListByEndStatusAndSessionId(sessionId);
	}*/
	@Override
	public List<AgentUserVO> getAgentUserListByEndStatusAndAgentId(
			String agentId) {
		return agentUserDao.getAgentUserListByEndStatusAndAgentId(agentId);
	}
	@Override
	public List<AgentUserVO> getAgentUserListByEndStatusAndUserId(String userId) {
		return agentUserDao.getAgentUserListByEndStatusAndUserId(userId);
	}
	@Override
	public AgentUserVO getAgentUserBySessionIdAndAgentId(Integer sessionId,
			String agentId) {
		return agentUserDao.getAgentUserBySessionIdAndAgentId(sessionId, agentId);
	}
	@Override
	public PaginationVO<AgentUserVO> getByPage(String userId, Integer pageNo,
			Integer pageSize) {
		Long total = agentUserDao.getTotalByUserId(userId);
		List<AgentUserVO> dataList = agentUserDao.getAgentUserListByPage(pageNo, pageSize,userId);
		PaginationVO<AgentUserVO> paginationVO = new PaginationVO<AgentUserVO>();
		paginationVO.setTotal(total);
		paginationVO.setDataList(dataList);
		return paginationVO;
	}
	@Override
	public Integer getSernumByEndStatusAndAgentId(String agentId) {
		return agentUserDao.getSernumByEndStatusAndAgentId(agentId);
	}
	@Override
	public void updateStatusAndChatTypeByAgentId(Integer status,Integer chatType,String agentId) {
		agentUserDao.updateStatusAndChatTypeByAgentId(status,chatType,agentId);
	}
	@Override
	public void updateStatusAndChatTypeByUserIdAndAgentId(Integer status,
			Integer chatType, String userId, String toagentId) {
		agentUserDao.updateStatusAndChatTypeByUserIdAndAgentId(status,chatType,userId,toagentId);
	}
	@Override
	public List<AgentUserVO> getAgentUserListByEndStatusAndChatTypeAndUserId(
			Integer chatStatusEnd, Integer chattype, String userId) {
		return agentUserDao.getAgentUserListByEndStatusAndChatTypeAndUserId(chatStatusEnd, chattype, userId);
	}
	@Override
	public List<AgentUserVO> getAgentUserListByEndStatusAndChatTypeAndAgentId(
			Integer chatStatusEnd, Integer chattypeChat, String agentId) {
		return agentUserDao.getAgentUserListByEndStatusAndChatTypeAndAgentId(chatStatusEnd, chattypeChat, agentId);
	}

}
