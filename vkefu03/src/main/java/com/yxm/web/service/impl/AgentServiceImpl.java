package com.yxm.web.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import org.springframework.stereotype.Service;
import com.yxm.util.factory.ACDFactory;
import com.yxm.web.domain.PaginationVO;
import com.yxm.web.entity.agent.Agent;
import com.yxm.web.service.AgentService;
@Service("agentService")
public class AgentServiceImpl implements AgentService{
   public PaginationVO<Agent> getByPage(Integer pageNo,Integer pageSize,String agentId){
	   //0.定义变量及初始化
	   PaginationVO<Agent> paginationVO = new PaginationVO<Agent>();
	   List<Agent> dataList = new ArrayList<Agent>();
	   int num = 0;//每页抽取num条数据
	   //1.获取空闲队列中除了本坐席之外的其他坐席
	   //Queue<Object> freeAgentQueue = ACDUtil.freeAgentQueue;
	   Queue<Agent> freeAgentQueue = ACDFactory.freeAgentQueue;
	   for (Object object : freeAgentQueue) {
		Agent agent = (Agent)object;
		if(!agent.getAgentId().equals(agentId)){
			 if(num>=(pageNo-1)*pageSize && num<pageNo*pageSize){
				/*   AgentShow agentShow = new AgentShow();*/
				   /*agentShow.setAgentId(agent.getAgentId());
				   agentShow.setAgentName(agent.getAgentName());
				   agentShow.setGroups(agent.getGroups());
				   agentShow.setLoginTime(agent.getLoginTime());
				   agentShow.setServiceNum(agent.getServiceNum());
				   agentShow.setStartTime(agent.getStartTime());
				   agentShow.setStatus(agent.getStatus());
				   agentShow.setUserId(agent.getUserId());*/
				   dataList.add(agent);
			   }
			   num++;
		}
	   }
	   //2.组装dataList和total
	   paginationVO.setTotal((long)  dataList.size());
	   paginationVO.setDataList(dataList);
	   System.out.println("***********paginationVO*************"+paginationVO.toString());
	   //3.传递给后台
	   return paginationVO;
   }
}
