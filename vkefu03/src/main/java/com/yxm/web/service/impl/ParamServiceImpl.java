package com.yxm.web.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxm.core.DataContext;
import com.yxm.web.dao.ParamDao;
import com.yxm.web.domain.ParamVO;
import com.yxm.web.service.ParamService;
@Service("paramService")
public class ParamServiceImpl implements ParamService{
	@Autowired
	private ParamDao paramDao;
	public ParamVO getUserQueueRemind(String description, String agentId) {
		ParamVO paramVO = new ParamVO();
		paramVO = paramDao.getUserQueueRemindByAgentId(description,DataContext.ROLE_AGENT, agentId);
		if(paramVO==null){
			paramVO = paramDao.getUserQueueRemindByAdmin(description, DataContext.ROLE_ADMIN);
		}
		return paramVO;
	}
	public ParamVO getBalcklistRemind(String description) {
		return paramDao.getBalcklistRemindByAdmin(description,DataContext.ROLE_ADMIN);
	}

	public ParamVO getUserQueueTimeoutRemind(String description) {
		return paramDao.getUserQueueTimeoutRemindByAdmin(description, DataContext.ROLE_ADMIN);
	}

	public ParamVO getMaxUser(String description, String agentId) {
		ParamVO paramVO = new ParamVO();
		 paramVO = paramDao.getMaxUserByAgentId(description, DataContext.ROLE_AGENT, agentId);
		 if(paramVO==null){
			 paramDao.getMaxUserByAdmin(description, DataContext.ROLE_ADMIN);
		 }
		return paramVO;
	}

	public ParamVO getWelcome(String description, String agentId) {
		ParamVO paramVO = new ParamVO();
		 paramVO = paramDao.getWelcomeByAgentId(description, DataContext.ROLE_AGENT, agentId);
		 if(paramVO==null){
			 paramDao.getWelcomeByAdmin(description, DataContext.ROLE_ADMIN);
		 }
		return paramVO;
	}
	public ParamVO getWelcome(String description){
		return paramDao.getWelcomeByAdmin(description, DataContext.ROLE_ADMIN);
	}

	public ParamVO getUserWaitingTime(String description) {
		return paramDao.getUserWaitingTimeByAdmin(description, DataContext.ROLE_ADMIN);
	}

	public ParamVO getLink(String description) {
		return paramDao.getLinkByAdmin(description,DataContext.ROLE_ADMIN);
	}

	public List<ParamVO> getSatisfaction(String description, String agentId) {
		List<ParamVO> paramList = new ArrayList<ParamVO>();
		paramList = paramDao.getSatisfactionByAgentId(description, DataContext.ROLE_AGENT, agentId);
		if(paramList.isEmpty()){
			paramList = paramDao.getSatisfactionByAdmin(description, DataContext.ROLE_ADMIN);
		}
		return paramList;
	}
}
