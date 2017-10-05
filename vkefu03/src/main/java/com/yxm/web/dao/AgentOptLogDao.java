package com.yxm.web.dao;
import java.util.List;

import com.yxm.web.domain.AgentOptLogVO;
/**
 * 坐席操作dao
 * @author yxm
 * @date 2017-6-8
 */
public interface AgentOptLogDao {
	void save(AgentOptLogVO agentOptLogVO);
	/**
	 * 根据上一次坐席操作id获取AgentOptLogList
	 * @param lastAgentOptId
	 * @return
	 */
	List<AgentOptLogVO> getAgentOptLogListByLastAgentOptId(long lastAgentOptId);
}
