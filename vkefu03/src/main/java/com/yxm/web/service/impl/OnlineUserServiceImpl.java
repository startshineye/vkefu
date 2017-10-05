package com.founder.focuss.webcc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.focuss.webcc.dao.OnlineUserDao;
import com.founder.focuss.webcc.entity.user.User;
import com.founder.focuss.webcc.service.OnlineUserService;

@Service("onlineUserService")
public class OnlineUserServiceImpl implements OnlineUserService{
    @Autowired
	private OnlineUserDao dao;
    
	@Override
	public void save(User user) {
		dao.insert(user);
	}

	@Override
	public void updateUserByUserId(User user) {
		dao.update(user);
	}

	@Override
	public User getByUserId(String userId) {
		return dao.getByUserId(userId);
	}

}
