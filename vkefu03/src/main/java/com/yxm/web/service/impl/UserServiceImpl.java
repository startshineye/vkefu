package com.founder.focuss.webcc.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.focuss.webcc.dao.UserGenerateDao;
import com.founder.focuss.webcc.domain.UserGenerateVO;
import com.founder.focuss.webcc.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
	private UserGenerateDao userDao;
    
	@Override
	public void save(UserGenerateVO user) {
		userDao.save(user);
		
	}

	@Override
	public Integer getLastId() {
		return userDao.getLastId();
	}

	@Override
	public UserGenerateVO getByLastId() {
		return userDao.getByLastId();
	}

	@Override
	public Integer isEmpty() {
		return userDao.isEmpty();
	}

	@Override
	public void update(UserGenerateVO user) {
		userDao.update(user);
	}

}
