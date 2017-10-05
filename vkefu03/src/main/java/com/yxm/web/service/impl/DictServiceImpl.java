package com.founder.focuss.webcc.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.focuss.webcc.dao.DictDao;
import com.founder.focuss.webcc.domain.DictVO;
import com.founder.focuss.webcc.service.DictService;

@Service("dictService")
public class DictServiceImpl implements DictService{
	@Autowired
	private DictDao dictDao;
	@Override
	public void save(DictVO dictVO) {
		dictDao.save(dictVO);
	}
	@Override
	public Integer getIdByDictType(String type) {
		return dictDao.getIdByDictType(type);
	}

}
