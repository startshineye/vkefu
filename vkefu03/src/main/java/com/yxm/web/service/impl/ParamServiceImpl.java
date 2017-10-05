package com.founder.focuss.webcc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.focuss.core.DataContext;
import com.founder.focuss.webcc.dao.ParamDao;
import com.founder.focuss.webcc.domain.ParamVO;
import com.founder.focuss.webcc.service.ParamService;

@Service("paramService")
public class ParamServiceImpl implements ParamService{
	@Autowired
	private ParamDao paramDao;

	@Override
	public ParamVO getUserQueueRemind(String description, String agentId) {
		ParamVO paramVO = new ParamVO();
		paramVO = paramDao.getUserQueueRemindByAgentId(description,DataContext.ROLE_AGENT, agentId);
		if(paramVO==null){
			paramVO = paramDao.getUserQueueRemindByAdmin(description, DataContext.ROLE_ADMIN);
		}
		return paramVO;
	}

	@Override
	public ParamVO getBalcklistRemind(String description) {
		return paramDao.getBalcklistRemindByAdmin(description,DataContext.ROLE_ADMIN);
	}

	@Override
	public ParamVO getUserQueueTimeoutRemind(String description) {
		return paramDao.getUserQueueTimeoutRemindByAdmin(description, DataContext.ROLE_ADMIN);
	}

	@Override
	public ParamVO getMaxUser(String description, String agentId) {
		ParamVO paramVO = new ParamVO();
		 paramVO = paramDao.getMaxUserByAgentId(description, DataContext.ROLE_AGENT, agentId);
		 if(paramVO==null){
			 paramDao.getMaxUserByAdmin(description, DataContext.ROLE_ADMIN);
		 }
		return paramVO;
	}

	@Override
	public ParamVO getWelcome(String description, String agentId) {
		ParamVO paramVO = new ParamVO();
		 paramVO = paramDao.getWelcomeByAgentId(description, DataContext.ROLE_AGENT, agentId);
		 if(paramVO==null){
			 paramDao.getWelcomeByAdmin(description, DataContext.ROLE_ADMIN);
		 }
		return paramVO;
	}
	@Override
	public ParamVO getWelcome(String description){
		return paramDao.getWelcomeByAdmin(description, DataContext.ROLE_ADMIN);
	}

	@Override
	public ParamVO getUserWaitingTime(String description) {
		return paramDao.getUserWaitingTimeByAdmin(description, DataContext.ROLE_ADMIN);
	}

	@Override
	public ParamVO getLink(String description) {
		return paramDao.getLinkByAdmin(description,DataContext.ROLE_ADMIN);
	}

	@Override
	public List<ParamVO> getSatisfaction(String description, String agentId) {
		List<ParamVO> paramList = new ArrayList<ParamVO>();
		paramList = paramDao.getSatisfactionByAgentId(description, DataContext.ROLE_AGENT, agentId);
		if(paramList.isEmpty()){
			paramList = paramDao.getSatisfactionByAdmin(description, DataContext.ROLE_ADMIN);
		}
		return paramList;
	}
}
