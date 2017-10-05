package com.founder.focuss.webcc.service;

import com.founder.focuss.webcc.domain.PaginationVO;
import com.founder.focuss.webcc.entity.agent.Agent;
public interface AgentService {
	PaginationVO<Agent> getByPage(Integer pageNo,Integer pageSize,String agnetId);
}
