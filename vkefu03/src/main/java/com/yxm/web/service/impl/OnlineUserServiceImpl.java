package com.yxm.web.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxm.web.dao.OnlineUserDao;
import com.yxm.web.entity.user.User;
import com.yxm.web.service.OnlineUserService;
@Service("onlineUserService")
public class OnlineUserServiceImpl implements OnlineUserService{
    @Autowired
	private OnlineUserDao dao;
    
	public void save(User user) {
		dao.insert(user);
	}

	public void updateUserByUserId(User user) {
		dao.update(user);
	}

	public User getByUserId(String userId) {
		return dao.getByUserId(userId);
	}

}
