package com.founder.focuss.webcc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.focuss.webcc.dao.CommonWordDao;
import com.founder.focuss.webcc.domain.CommonWordVO;
import com.founder.focuss.webcc.service.CommonWordService;

@Service("commonWordService")
public class CommonWordServiceImpl implements CommonWordService{
    @Autowired
	private CommonWordDao commonWordDao;
	
   @Override
   public List<CommonWordVO> getCommonWordList(){
	   return commonWordDao.getCommonWordList();
   }
}
