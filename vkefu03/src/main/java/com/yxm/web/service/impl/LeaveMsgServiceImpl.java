package com.founder.focuss.webcc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.focuss.webcc.dao.LeaveMsgDao;
import com.founder.focuss.webcc.domain.LeaveMsgVO;
import com.founder.focuss.webcc.service.LeaveMsgService;

@Service("leaveMsgService")
public class LeaveMsgServiceImpl implements LeaveMsgService {
    @Autowired
	private LeaveMsgDao leaveMsgDao;
	
    @Override
	public void save(LeaveMsgVO leave) {
    	leaveMsgDao.save(leave);
	}
  
}
