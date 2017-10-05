package com.founder.focuss.webcc.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.focuss.webcc.dao.BlackListDao;
import com.founder.focuss.webcc.domain.BlackListVO;
import com.founder.focuss.webcc.service.BlackListService;

@Service("blackListService")
public class BlackListServiceImpl implements BlackListService{
	@Autowired
	private BlackListDao dao;
	
	@Override
	public void add(BlackListVO blackListVO) {
		dao.insert(blackListVO);
	}

	@Override
	public BlackListVO getUserInfo(String userInfo) {
		return dao.getBlackList(userInfo);
	}

	@Override
	public BlackListVO selectBlackListByUserInfo(String userInfo) {
		return dao.selectBlackListByUserInfo(userInfo);
	}

	 
}
