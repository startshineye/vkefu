package com.yxm.web.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxm.web.dao.AgentOptTimeSliceDao;
import com.yxm.web.dao.UserOptQueueDao;
import com.yxm.web.service.SystemInitService;
@Service("systemInitService")
public class SystemInitServiceImpl implements SystemInitService{
	
	@Autowired
	private UserOptQueueDao userOptQueueDao;
	
	@Autowired
	private AgentOptTimeSliceDao agentOptTimeSliceDao;
	public void run() {
	System.err.println("****SystemInitService execute！*****");
	}

	public void clearAgentOptTimeSlice() {
		agentOptTimeSliceDao.delAll();
	}

	public void clearUserOptQueue() {
		userOptQueueDao.delAll();
	}
}
