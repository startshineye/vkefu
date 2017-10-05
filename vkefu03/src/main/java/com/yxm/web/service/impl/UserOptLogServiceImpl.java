package com.founder.focuss.webcc.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.focuss.webcc.dao.UserOptLogDao;
import com.founder.focuss.webcc.domain.UserOptLogVO;
import com.founder.focuss.webcc.service.UserOptLogService;

@Service("userOptLogService")
public class UserOptLogServiceImpl implements UserOptLogService{
	@Autowired
    private UserOptLogDao userOptLogDao;
	
	@Override
	public boolean save(UserOptLogVO userOptLogVO) {
		return userOptLogDao.save(userOptLogVO);
	}
}
