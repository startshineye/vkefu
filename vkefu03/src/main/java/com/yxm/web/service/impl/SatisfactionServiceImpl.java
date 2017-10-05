package com.founder.focuss.webcc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.focuss.webcc.dao.SatisfactionDao;
import com.founder.focuss.webcc.domain.SatisfactionVO;
import com.founder.focuss.webcc.service.SatisfactionService;


@Service("satisfactionService")
public class SatisfactionServiceImpl implements SatisfactionService{
	
	@Autowired
	private SatisfactionDao  satisfactionDao;
	
	@Override
	public void save(SatisfactionVO satisfaction) {
		satisfactionDao.save(satisfaction);
	}

	@Override
	public SatisfactionVO getSatisfactionByThreadIdAndAgentId(Integer threadId,
			String agentId) {
		return satisfactionDao.getSatisfactionByThreadIdAndAgentId(threadId,agentId);
	}
}
