package com.yxm.web.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yxm.web.dao.UserOptLogDao;
import com.yxm.web.domain.UserOptLogVO;
import com.yxm.web.service.UserOptLogService;
@Service("userOptLogService")
public class UserOptLogServiceImpl implements UserOptLogService{
	@Autowired
    private UserOptLogDao userOptLogDao;
	public boolean save(UserOptLogVO userOptLogVO) {
		return userOptLogDao.save(userOptLogVO);
	}
}
