package com.founder.focuss.webcc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.founder.focuss.core.DataContext;
import com.founder.focuss.utils.DateUtil;
import com.founder.focuss.utils.factory.ACDFactory;
import com.founder.focuss.webcc.domain.AgentOptLogVO;
import com.founder.focuss.webcc.domain.AgentUserVO;
import com.founder.focuss.webcc.domain.MessageVO;
import com.founder.focuss.webcc.domain.PaginationVO;
import com.founder.focuss.webcc.domain.ParamVO;
import com.founder.focuss.webcc.domain.SatisfactionMessageVO;
import com.founder.focuss.webcc.domain.SatisfactionVO;
import com.founder.focuss.webcc.domain.ThreadVO;
import com.founder.focuss.webcc.domain.UncheckedMessageVO;
import com.founder.focuss.webcc.domain.UserOptLogVO;
import com.founder.focuss.webcc.entity.api.Satisfaction;
import com.founder.focuss.webcc.service.AgentOptLogService;
import com.founder.focuss.webcc.service.AgentUserService;
import com.founder.focuss.webcc.service.ChatRecordService;
import com.founder.focuss.webcc.service.MessageService;
import com.founder.focuss.webcc.service.ParamService;
import com.founder.focuss.webcc.service.SatisfactionService;
import com.founder.focuss.webcc.service.ThreadService;
import com.founder.focuss.webcc.service.UserOptLogService;
import com.founder.focuss.webcc.websocket.SystemWebSocketHandler;

/**
 * 聊天记录controller
 * 
 * @author yxm
 * @date 2016-10-08
 */
@Controller
@RequestMapping("/chatRecord")
public class ChatRecordController {
	@Autowired
	private ChatRecordService chatRecordService;
	
	@Autowired
	private ParamService paramService;
	
	@Autowired
	private AgentUserService agentUserService;
	
	@Autowired
	private  ThreadService threadService;
	 
	@Autowired
	private SystemWebSocketHandler systemWebSocketHandler;
	 
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private AgentOptLogService agentOptLogService;
	
	@Autowired
	private UserOptLogService userOptService;
	
	@Autowired
	private SatisfactionService satisfactionService;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss"); // 日期格式化

	@RequestMapping("getlastmsg")
	@ResponseBody
	public Object getLastMessage(String userId, Integer lastmsgid) {
		System.out.println("userId" + userId + "lastmsgid" + lastmsgid);
		// 1.获取为查询信息
		UncheckedMessageVO<MessageVO> uncheckedMessage = chatRecordService
				.getUncheckedMessage(userId, lastmsgid);
		return uncheckedMessage;
	}
	/**
	 * 坐席发送满意度
	 * @param request
	 * @return
	 */
	@RequestMapping("sendSatifaction")
	@ResponseBody
	public Object getSatisfaction(HttpServletRequest request) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			 /**
		     * 主变量
		     */
			Integer optCode = DataContext.AGENT_OPTCODE_SATISFACT;
			
			/**
			 * default变量
			 */
			Integer entId = DataContext.AGENT_ENTID_DEFAULT;
			String queueId = DataContext.AGENT_QUEUEID_DEFAULT;
			String remark = DataContext.AGENT_REMARK_DEFAULT;
			String toAgentId = DataContext.AGENT_TOAGENTID_DEFAULT;
			String toUserId = DataContext.AGENT_TOUSERID_DEFAULT;
		   
			// 以json形式传递数据
			String userId = request.getParameter("userId");
			String message = request.getParameter("message");
			String agentId = request.getParameter("agentId");
			//threadid表才是对应的某一次会话id
			ThreadVO thread = threadService.getThreadByEndStatusAndUserIdAndAgentId(userId, agentId);
			SatisfactionVO satiafaction = satisfactionService.getSatisfactionByThreadIdAndAgentId(thread.getThreadId(), agentId);
			if(satiafaction!=null){
				jsonMap.put("message","failure");
				return jsonMap;
			}else{
				// 获取userId所对应的session
				//WebSocketSession userSession = WebSocketSessionUtil.userSocketSessionMap.get(userId);
				//WebSocketSession userSession = ACDFactory.createSocketUserManager().getSocketSession(userId);
				List<WebSocketSession> userSession = ACDFactory.createSocketUserManager().getSocketSessionList(userId);
				
				SatisfactionMessageVO<ParamVO> saMsgVO = new SatisfactionMessageVO<ParamVO>();
				saMsgVO.setSffrom(agentId);
				saMsgVO.setSfto(userId);
				//saMsgVO.setSfmessage(message);
				String msgTime = sdf.format( new Date());
			    //List<ParamVO> datalist = SystemInitUtil.satisfactionMap.get("satisfaction");
			    List<ParamVO> datalist =paramService.getSatisfaction(DataContext.PARAM_SATISFACTION, agentId);
				saMsgVO.setSfmessageTime(msgTime);
				saMsgVO.setSfdataList(datalist);
				saMsgVO.setSfmessage(message);
				jsonMap.put("message",datalist);
				
				//组装回复给用户的信息
				Satisfaction satisfaction = new Satisfaction();
				satisfaction.setAgentId(agentId);
				satisfaction.setCmd("agentsatisfaction");
				satisfaction.setDesc("坐席满意度评价");
				satisfaction.setUserId(userId);
				satisfaction.setMessage(datalist);
				// 发送消息给客户
				systemWebSocketHandler.sendTipMessage(userSession,new TextMessage(satisfaction.toJson()));
				agentOptLog(agentId,thread.getAgentName(), entId, optCode, DataContext.OPTRESULT_SUCCESS, queueId, remark, thread.getThreadId(), toAgentId, toUserId, userId, thread.getUserName());
				return jsonMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("message","failure");
			return jsonMap;
		}
	}
	@RequestMapping("confirmSatifaction")
	@ResponseBody
	public Object confirmSatifaction(HttpServletRequest request) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String satifanum = request.getParameter("satifaction");// 满意度数字
		String userId = request.getParameter("userId");// 用户id
		String userName = request.getParameter("userName");// 用户姓名
		String agentId = request.getParameter("agentId");// 满意度数字
	    String sessionIdStr = request.getParameter("sessionId");
        Integer sessionId = Integer.parseInt(sessionIdStr.trim());
    	String startTime = DateUtil.datetimeFormat.format(new Date());
        try {
        	ThreadVO thread = threadService.getThreadByEndStatusAndUserIdAndAgentId(userId, agentId);
            SatisfactionVO satiafaction = satisfactionService.getSatisfactionByThreadIdAndAgentId(thread.getThreadId(), agentId);
            if(satiafaction!=null){
				jsonMap.put("confirMsg", "err");
				return jsonMap;
            }else{
            	 String time = DateUtil.datetimeFormat.format(new Date());
         		//1.满意度评价组装
                 SatisfactionVO satisfactionVO = new SatisfactionVO();
                 satisfactionVO.setAgentId(agentId);
                 satisfactionVO.setUserId(userId);
                 satisfactionVO.setStartTime(time);
                 satisfactionVO.setThreadId(thread.getThreadId());
                 satisfactionVO.setSessionId(sessionId);
                 satisfactionVO.setSatisfaction(satifanum);
                 satisfactionService.save(satisfactionVO);
    				// 获取userId所对应的session
    				//WebSocketSession session = WebSocketSessionUtil.agentSocketSessionMap.get(agentId);
				//WebSocketSession session = ACDFactory.createSocketAgentManager().getSocketSession(agentId);
                 List<WebSocketSession> session = ACDFactory.createSocketAgentManager().getSocketSessionList(agentId);
                 Satisfaction satisfaction = new Satisfaction();
				satisfaction.setAgentId(agentId);
				satisfaction.setCmd("agentsatisfaction");
				satisfaction.setDesc("客户满意度评价结束");
				satisfaction.setUserId(userId);
				satisfaction.setUserName(userName);
				satisfaction.setSessionId(sessionId);
				// 发送消息给客户
				systemWebSocketHandler.sendTipMessage(session,
						new TextMessage(satisfaction.toJson()));
				userOptLog(userId, userName, DataContext.USER_OPTCODE_SATISFACT, "", startTime, "", DataContext.OPTRESULT_SUCCESS);
				jsonMap.put("confirmMsg",satisfaction);
				return jsonMap;
            }
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("confirMsg", "err");
			return jsonMap;
		  }
		}
	@RequestMapping("sendAutoReply")
	@ResponseBody
	public Object sendAutoReply(HttpServletRequest request){
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 以json形式传递数据
		String userId = request.getParameter("userId");
		String message = request.getParameter("message");
		String agentId = request.getParameter("agentId");
		try{
		// 获取userId所对应的session
		//WebSocketSession agentSession = ACDFactory.createSocketAgentManager().getSocketSession(agentId);
		//WebSocketSession userSession = ACDFactory.createSocketUserManager().getSocketSession(userId);
			List<WebSocketSession> agentSession = ACDFactory.createSocketAgentManager().getSocketSessionList(agentId);
			List<WebSocketSession> userSession = ACDFactory.createSocketUserManager().getSocketSessionList(userId);
			SatisfactionMessageVO<Object> saMsgVO = new SatisfactionMessageVO<Object>();
		 saMsgVO.setSffrom(agentId);
		 saMsgVO.setSfto(userId);
		 saMsgVO.setSfmessage(message);
		 saMsgVO.setType("commonword");
		String msgTime = sdf.format( new Date());
		
		saMsgVO.setSfmessageTime(msgTime);
		//发送消息给客户
		systemWebSocketHandler.sendTipMessage(userSession,
				new TextMessage(saMsgVO.toJson()));
		//发送消息给客户
		systemWebSocketHandler.sendTipMessage(agentSession,
				new TextMessage(saMsgVO.toJson()));
		//发送消息给坐席自己显示
		System.out.println(saMsgVO);
		jsonMap.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("success",false);
		}
		return jsonMap;
	}
	/*@RequestMapping("/history")
	@ResponseBody
	public Object chatThread(HttpServletRequest request,HttpServletResponse response,Integer pageNo,Integer pageSize){
		String userId = request.getParameter("userId");
		PaginationVO<ThreadVO> paginationVO = threadService.getByPage(userId, pageNo, pageSize);
		System.out.println(paginationVO);
		return paginationVO;
	}*/
 
	@RequestMapping("/history")
	@ResponseBody
	public Object chatThread(HttpServletRequest request,HttpServletResponse response,Integer pageNo,Integer pageSize){
		/**
		 * 获取本用户所建立的所有聊天 agentuser
		 */
		PaginationVO<AgentUserVO> paginationVO = new PaginationVO<AgentUserVO>();
		try {
			String userId = request.getParameter("userId");
			paginationVO = agentUserService.getByPage(userId,pageNo,pageSize);
			System.out.println(paginationVO);
			return paginationVO;
		} catch (Exception e) {
			 e.printStackTrace();
			 return paginationVO;
		}
	}
	@RequestMapping("/userRecord")
	@ResponseBody
	public Object getUserRecord(HttpServletRequest request,Integer pageNo,Integer pageSize){
		PaginationVO<MessageVO> paginationVO = new PaginationVO<MessageVO>();
		try {
			//我们规定最多只能查询12页，600条数据
			if(pageNo<=12){
				String userId = request.getParameter("userId");
				String agentId = request.getParameter("agentId");
				if("".equals(agentId)){
					return null;
				}
				Long total = messageService.getMessageTotal(userId, agentId);
				paginationVO = messageService.getUserRecordByUserIdAndAgentId(pageNo, pageSize, userId, agentId);
				paginationVO.setTotal(total);
			}
			return paginationVO;
		} catch (Exception e) {
			 e.printStackTrace();
			 return paginationVO;
		}
	}
	
@RequestMapping("/agentRecord")
	@ResponseBody
	public Object getAgentRecord(Integer pageNo,Integer pageSize,String agentId,Integer sessionId){
		PaginationVO<MessageVO> paginationVO = new PaginationVO<MessageVO>();
		try {
			/**
			 * 获取坐席端特定聊天信息
			 */
			Long total = messageService.getMessageTotalBySessionIdAndAgentId(sessionId,agentId);
			paginationVO = messageService.getAgentRecord(pageNo,pageSize,sessionId,agentId);
			paginationVO.setTotal(total);
			return paginationVO;
		} catch (Exception e) {
			e.printStackTrace();
			return paginationVO;
		}
	}
	
	@RequestMapping("/getUserName")
	@ResponseBody
	public Object getUserName(String agentId,Integer sessionId){
		HashMap<String, String> jsonMap = new HashMap<String,String>();
		try {
		/*	ThreadVO thread = threadService.getThreadBySessionIdAndAgentId(sessionId, agentId);
			jsonMap.put("username", thread.getUserName());*/
		} catch (Exception e) {
			jsonMap.put("username", "");
		}
		return jsonMap;
	}
	
	@RequestMapping("/more")
	@ResponseBody
	public Object chatMore(HttpServletRequest request,Integer pageNo,Integer pageSize){
	   String userId = request.getParameter("userId");
	   String agentId = request.getParameter("agentId");
	   String minmessageId = request.getParameter("messageId");
	   if("".equals(agentId)){
		  return null; 
	   }
	   //int num =(Integer)request.getParameter("num");
	   PaginationVO<MessageVO> paginationVO=null;
	   if("-1".equals(minmessageId)){
		   paginationVO = messageService.getMoreHistoryByUserIdAndAgentIdNoMinMessageId(pageNo,pageSize,userId,agentId);
	   }else{
		   paginationVO = messageService.getMoreHistoryByUserIdAndAgentId(pageNo,pageSize,userId,agentId,minmessageId);
	   }
	   return paginationVO;
	}
	
	private void agentOptLog(String agentId,String agentName,int entId,int optCode,int optResult,String queueId,String remark,int threadId,String toAgentId,String toUserId,String userId,String userName){
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
			  agentOptLogService.save(agentOptLogVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
	
	private void userOptLog(String userId,String userName,Integer optCode,String queueId,String startTime,String remark,Integer optResult){
		/**
		 * 户进队列记录日志
		 */
		UserOptLogVO userOptLogVO = new UserOptLogVO();
		userOptLogVO.setUserId(userId);
		userOptLogVO.setUserName(userName);
		userOptLogVO.setOptCode(optCode);
		userOptLogVO.setQueueId(queueId);
		userOptLogVO.setStartTime(startTime);
		userOptLogVO.setRemark(remark);
		userOptLogVO.setOptResult(optResult);
		userOptService.save(userOptLogVO);
	}

}
