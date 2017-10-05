package com.founder.focuss.webcc.service.impl;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.focuss.core.DataContext;
import com.founder.focuss.utils.DateUtil;
import com.founder.focuss.webcc.dao.OnlineAgentDao;
import com.founder.focuss.webcc.entity.agent.Agent;
import com.founder.focuss.webcc.service.OnlineAgentService;

@Service("onlineAgentService")
public class OnlineAgentServiceImpl implements OnlineAgentService{
	@Autowired
    private OnlineAgentDao dao;
	@Override
	public void save(Agent agent) {
		dao.insert(agent);
	}
	@Override
	public void updateAgentByAgentId(Agent agent) {
		dao.update(agent);
	}
	@Override
	public Agent getByAgentId(String agentId) {
		return dao.getByAgentId(agentId);
	}
	@Override
	public boolean agentlogin(String agentId){
		Agent agent = dao.getByAgentId(agentId);
		if(agent==null){//说明坐席没有登录
			return false;
		}else{//说明不为null，可能登录
			//查询坐席登录时间和当前时间如果是为12小时说明是服务器死机,此时认为坐席不在线
			String login = dao.getAgentLoginByAgentId(agentId);
			if(DataContext.AGENT_LOGIN_STATUS_OFF.equals(login)){//说明离线
				return false;
			}else{
				String loginTime_tbl = dao.getLoginTimeByAgentId(agentId);
				try {
					long loginTime = DateUtil.datetimeFormat.parse(loginTime_tbl).getTime();
					long time = (new Date()).getTime();
					if((time-loginTime)/1000/60/60>=12){
						return false;
					}else{
						return true;
					}
				} catch (ParseException e) {
					e.printStackTrace();
					return true;
				}
				
			}
		}
	}
	@Override
	public void updateAgentStatusByAgentId(String status, String agentId) {
         dao.updateAgentStatusByAgentId(status,agentId);		
	}
}
