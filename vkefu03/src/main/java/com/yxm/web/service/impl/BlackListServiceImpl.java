package com.yxm.web.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxm.web.dao.BlackListDao;
import com.yxm.web.domain.BlackListVO;
import com.yxm.web.service.BlackListService;
@Service("blackListService")
public class BlackListServiceImpl implements BlackListService{
	@Autowired
	private BlackListDao dao;
	
	public void add(BlackListVO blackListVO) {
		dao.insert(blackListVO);
	}

	public BlackListVO getUserInfo(String userInfo) {
		return dao.getBlackList(userInfo);
	}

	public BlackListVO selectBlackListByUserInfo(String userInfo) {
		return dao.selectBlackListByUserInfo(userInfo);
	}

	 
}
