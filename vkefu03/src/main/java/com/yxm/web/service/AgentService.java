package com.yxm.web.service;
import com.yxm.web.domain.PaginationVO;
import com.yxm.web.entity.agent.Agent;
public interface AgentService {
	PaginationVO<Agent> getByPage(Integer pageNo,Integer pageSize,String agnetId);
}
