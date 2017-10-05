package com.founder.focuss.webcc.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.focuss.webcc.dao.AgentOptTimeSliceDao;
import com.founder.focuss.webcc.dao.UserOptQueueDao;
import com.founder.focuss.webcc.service.SystemInitService;

@Service("systemInitService")
public class SystemInitServiceImpl implements SystemInitService{
	
	@Autowired
	private UserOptQueueDao userOptQueueDao;
	
	@Autowired
	private AgentOptTimeSliceDao agentOptTimeSliceDao;
	
	@Override
	public void run() {
	System.err.println("****SystemInitService execute！*****");
	}

	@Override
	public void clearAgentOptTimeSlice() {
		agentOptTimeSliceDao.delAll();
	}

	@Override
	public void clearUserOptQueue() {
		userOptQueueDao.delAll();
	}
}
