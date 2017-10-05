package com.yxm.web.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxm.web.dao.AgentUserDao;
import com.yxm.web.domain.AgentUserVO;
import com.yxm.web.domain.PaginationVO;
import com.yxm.web.service.AgentUserService;
@Service("agentUserService")
public class AgentUserServiceImpl implements AgentUserService{
	
	@Autowired
    private AgentUserDao agentUserDao;
	public AgentUserVO getAgentUserByUserIdAndAgentId(String userId,
			String agentId) {
		return agentUserDao.getAgentUserByUserIdAndAgentId(userId, agentId);
	}
	public void saveAutoSessionId(AgentUserVO agentUserVO) {
		agentUserDao.saveAutoSessionId(agentUserVO);
	}
	public void saveUnAutoSessionId(AgentUserVO agentUserVO) {
		agentUserDao.saveUnAutoSessionId(agentUserVO);
	}
	/*@Override
	public List<AgentUserVO> getAgentUserListByEndStatusAndSessionId(
			Integer sessionId) {
		return agentUserDao.getAgentUserListByEndStatusAndSessionId(sessionId);
	}*/
	public List<AgentUserVO> getAgentUserListByEndStatusAndAgentId(
			String agentId) {
		return agentUserDao.getAgentUserListByEndStatusAndAgentId(agentId);
	}
	public List<AgentUserVO> getAgentUserListByEndStatusAndUserId(String userId) {
		return agentUserDao.getAgentUserListByEndStatusAndUserId(userId);
	}
	public AgentUserVO getAgentUserBySessionIdAndAgentId(Integer sessionId,
			String agentId) {
		return agentUserDao.getAgentUserBySessionIdAndAgentId(sessionId, agentId);
	}
	public PaginationVO<AgentUserVO> getByPage(String userId, Integer pageNo,
			Integer pageSize) {
		Long total = agentUserDao.getTotalByUserId(userId);
		List<AgentUserVO> dataList = agentUserDao.getAgentUserListByPage(pageNo, pageSize,userId);
		PaginationVO<AgentUserVO> paginationVO = new PaginationVO<AgentUserVO>();
		paginationVO.setTotal(total);
		paginationVO.setDataList(dataList);
		return paginationVO;
	}
	public Integer getSernumByEndStatusAndAgentId(String agentId) {
		return agentUserDao.getSernumByEndStatusAndAgentId(agentId);
	}
	public void updateStatusAndChatTypeByAgentId(Integer status,Integer chatType,String agentId) {
		agentUserDao.updateStatusAndChatTypeByAgentId(status,chatType,agentId);
	}
	public void updateStatusAndChatTypeByUserIdAndAgentId(Integer status,
			Integer chatType, String userId, String toagentId) {
		agentUserDao.updateStatusAndChatTypeByUserIdAndAgentId(status,chatType,userId,toagentId);
	}
	public List<AgentUserVO> getAgentUserListByEndStatusAndChatTypeAndUserId(
			Integer chatStatusEnd, Integer chattype, String userId) {
		return agentUserDao.getAgentUserListByEndStatusAndChatTypeAndUserId(chatStatusEnd, chattype, userId);
	}
	public List<AgentUserVO> getAgentUserListByEndStatusAndChatTypeAndAgentId(
			Integer chatStatusEnd, Integer chattypeChat, String agentId) {
		return agentUserDao.getAgentUserListByEndStatusAndChatTypeAndAgentId(chatStatusEnd, chattypeChat, agentId);
	}

}
