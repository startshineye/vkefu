package com.yxm.web.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yxm.web.dao.UserGenerateDao;
import com.yxm.web.domain.UserGenerateVO;
import com.yxm.web.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
	private UserGenerateDao userDao;
	public void save(UserGenerateVO user) {
		userDao.save(user);
		
	}
	public Integer getLastId() {
		return userDao.getLastId();
	}
	public UserGenerateVO getByLastId() {
		return userDao.getByLastId();
	}
	public Integer isEmpty() {
		return userDao.isEmpty();
	}
	public void update(UserGenerateVO user) {
		userDao.update(user);
	}

}
