package com.yxm.web.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxm.web.dao.SatisfactionDao;
import com.yxm.web.domain.SatisfactionVO;
import com.yxm.web.service.SatisfactionService;
@Service("satisfactionService")
public class SatisfactionServiceImpl implements SatisfactionService{
	
	@Autowired
	private SatisfactionDao  satisfactionDao;
	
	public void save(SatisfactionVO satisfaction) {
		satisfactionDao.save(satisfaction);
	}

	public SatisfactionVO getSatisfactionByThreadIdAndAgentId(Integer threadId,
			String agentId) {
		return satisfactionDao.getSatisfactionByThreadIdAndAgentId(threadId,agentId);
	}
}
