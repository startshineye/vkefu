package com.yxm.web.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxm.web.dao.CommonWordDao;
import com.yxm.web.domain.CommonWordVO;
import com.yxm.web.service.CommonWordService;

@Service("commonWordService")
public class CommonWordServiceImpl implements CommonWordService{
    @Autowired
	private CommonWordDao commonWordDao;
    
   public List<CommonWordVO> getCommonWordList(){
	   return commonWordDao.getCommonWordList();
   }
}
