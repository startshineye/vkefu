package com.yxm.web.dao;
import com.yxm.web.domain.AgentOptTimeSliceVO;
/**
 * 坐席操作dao
 * @author yxm
 * @date 2017-6-8
 */
public interface AgentOptTimeSliceDao {
	
	void save(AgentOptTimeSliceVO agentOptTimeSliceVO);
	
	String getEndTime();
    /**
     * 清空表
     */
	void delAll();
}
