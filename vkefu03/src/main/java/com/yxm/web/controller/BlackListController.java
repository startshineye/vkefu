package com.yxm.web.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxm.util.DateUtil;
import com.yxm.util.factory.ACDFactory;
import com.yxm.web.dao.AgentOptLogDao;
import com.yxm.web.entity.user.User;
import com.yxm.web.service.BlackListService;
import com.yxm.web.service.OnlineAgentService;
import com.yxm.web.service.OnlineUserService;
/**
 * 黑名单controller
 * @author yxm
 * @date 2016-12-12
 *
 */
@Controller
@RequestMapping("blacklist")
public class BlackListController {
  @Autowired
  private BlackListService  blackListService;
  @Autowired
  private AgentOptLogDao agentOptLogDao;
  
  @Autowired
  private OnlineAgentService onlineAgentService;
  
  @Autowired
  private OnlineUserService onlineUserService;
  
  @RequestMapping("/add")
  @ResponseBody
  public Object add(HttpServletRequest request,HttpServletResponse response){
	  Map<String, Object> jsonMap = new HashMap<String,Object>();
	  try{
	  //获取参数
	  String agentId = request.getParameter("agentId");
      String userId = request.getParameter("userId");
	  String userType = request.getParameter("userType");
	  String time = DateUtil.datetimeFormat.format(new Date());
	 /* User user = ACDFactory.createUserManager().getUser(userId);*/
	  User user = new User();
	  if(user==null){
		  user = onlineUserService.getByUserId(userId);
		  ACDFactory.createUserManager().addUser(user);
	  }
	  Agent agent = ACDFactory.createAgentManager().getAgent(agentId);
	  if(agent==null){
		  agent = onlineAgentService.getByAgentId(agentId);
		  ACDFactory.createAgentManager().addAgent(agent);
	  }
	  if(user != null && agent != null){
		  String userInfo = user.getIp();
		  String agentName = agent.getAgentName();
		  
		 //创建对象
		  BlackListVO blackListVO = new BlackListVO();
		  blackListVO.setAgentId(agentId);
		  blackListVO.setUserInfo(userInfo);
		  blackListVO.setUserType(userType);
		  blackListVO.setStartTime(time);
		  blackListVO.setReason("");
		  
		 //添加黑名单前需要判断是否已经添加
		  BlackListVO black = blackListService.selectBlackListByUserInfo(userInfo);
		  if(black==null){
			  //保存
			  try {
				  blackListService.add(blackListVO);
				  //坐席操作日志
				  agentOptLog(agentId,agentName, -1, 8, 1, "", "",-1, "", "", "", "");
				  jsonMap.put("state","success");
			} catch (Exception e) {
				// TODO: handle exception
				 agentOptLog(agentId, agentName, -1, 8, 0, "", "",-1, "", "", "", "");
				 e.printStackTrace();
				 jsonMap.put("state","failure");
			}
		  }else{
			  jsonMap.put("state","exist");
		  }
	    }
	  }catch(Exception e){
		  jsonMap.put("state","failure");
	  }
	  return jsonMap;
  }
  /**
   * 坐席日志记录
   * 
   */
  public void agentOptLog(String agentId,String agentName,int entId,int optCode,int optResult,String queueId,String remark,int threadId,String toAgentId,String toUserId,String userId,String userName){
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
