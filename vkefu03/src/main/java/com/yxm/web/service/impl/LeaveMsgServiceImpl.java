package com.yxm.web.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yxm.web.dao.LeaveMsgDao;
import com.yxm.web.domain.LeaveMsgVO;
import com.yxm.web.service.LeaveMsgService;
@Service("leaveMsgService")
public class LeaveMsgServiceImpl implements LeaveMsgService {
    @Autowired
	private LeaveMsgDao leaveMsgDao;
	
	public void save(LeaveMsgVO leave) {
    	leaveMsgDao.save(leave);
	}
  
}
