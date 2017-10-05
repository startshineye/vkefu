package com.yxm.web.service.impl;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxm.util.DateUtil;
import com.yxm.web.dao.AgentOptLogDao;
import com.yxm.web.domain.AgentOptLogVO;
import com.yxm.web.service.AgentOptLogService;
@Service("agentOptLogService")
public class AgentOptLogServiceImpl implements AgentOptLogService {
	@Autowired
	private AgentOptLogDao agentOptLogDao;
	 
	public void save(AgentOptLogVO agentOptLogVO) {
		agentOptLogDao.save(agentOptLogVO);
	}
	  public void agentOptLog(String agentId,String agentName,Integer entId,Integer optCode,Integer optResult,String queueId,String remark,Integer threadId,String toAgentId,String toUserId,String userId,String userName){
		 try {
			 String time = DateUtil.datetimeFormat.format(new Date());
			  AgentOptLogVO agentOptLogVO = new AgentOptLogVO();
			  agentOptLogVO.setAgentId(agentId);
			  agentOptLogVO.setAgentName(agentName);
			  agentOptLogVO.setEntId(entId);
			  agentOptLogVO.setLogTime(time);
			  agentOptLogVO.setOptCode(optCode);
			  agentOptLogVO.setOptResult(optResult);
			  agentOptLogVO.setQueueId(queueId);
			  agentOptLogVO.setRemark(remark);
			  agentOptLogVO.setStartTime(time);
			  agentOptLogVO.setThreadId(threadId);
			  agentOptLogVO.setToAgentId(toAgentId);
			  agentOptLogVO.setToUserId(toUserId);
			  agentOptLogVO.setUserId(userId);
			  agentOptLogVO.setUserName(userName);
			  agentOptLogDao.save(agentOptLogVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
}
