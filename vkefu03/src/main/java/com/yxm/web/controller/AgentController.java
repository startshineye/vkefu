package com.founder.focuss.webcc.controller;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.founder.focuss.core.DataContext;
import com.founder.focuss.utils.DateUtil;
import com.founder.focuss.utils.JwtUtil;
import com.founder.focuss.utils.WebSocketSessionUtil;
import com.founder.focuss.utils.factory.ACDFactory;
import com.founder.focuss.webcc.domain.AgentOptLogVO;
import com.founder.focuss.webcc.domain.AgentUserVO;
import com.founder.focuss.webcc.domain.ParamVO;
import com.founder.focuss.webcc.domain.ThreadVO;
import com.founder.focuss.webcc.entity.Person;
import com.founder.focuss.webcc.entity.agent.Agent;
import com.founder.focuss.webcc.entity.api.AgentEnd;
import com.founder.focuss.webcc.entity.api.AgentGroupChatConfirm;
import com.founder.focuss.webcc.entity.api.AgentLogoff;
import com.founder.focuss.webcc.entity.api.AgentTransferServiceConfirm;
import com.founder.focuss.webcc.entity.api.MessageTip;
import com.founder.focuss.webcc.entity.user.User;
import com.founder.focuss.webcc.service.AgentOptLogService;
import com.founder.focuss.webcc.service.AgentService;
import com.founder.focuss.webcc.service.AgentUserService;
import com.founder.focuss.webcc.service.DictService;
import com.founder.focuss.webcc.service.MessageService;
import com.founder.focuss.webcc.service.OnlineAgentService;
import com.founder.focuss.webcc.service.OnlineUserService;
import com.founder.focuss.webcc.service.ParamService;
import com.founder.focuss.webcc.service.ThreadService;
import com.founder.focuss.webcc.service.WebSocketService;
import com.founder.focuss.webcc.websocket.SystemWebSocketHandler;

@Controller
@RequestMapping("agent")
public class AgentController{
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private AgentUserService agentUserService;
	
	@Autowired
	private WebSocketService webSocketService;
	
	@Autowired
	private ThreadService threadService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private SystemWebSocketHandler webSocketHandler;
	
	@Autowired
	private DictService dictService;
	
	@Autowired
	private ParamService paramService;
	
	@Autowired
	private AgentOptLogService agentOptLogService;
	
	@Autowired
	private OnlineAgentService onlineAgentService;

	@Autowired
	private OnlineUserService onlineUserService;
	
   @RequestMapping("/test")
	public String test(){
		return "agent/test";
	}
   
   @RequestMapping("/islogin")
   @ResponseBody
   public void islogin(HttpServletRequest request,HttpServletResponse response){
	   response.setContentType("text/plain");
	   response.addHeader("Access-Control-Allow-Origin","*");
	   String callback = request.getParameter("callback");//得到js函数名称
	   String agentId = request.getParameter("agentId");
	   String access_token = request.getParameter("access_token");
	   JSONObject returnMap = new JSONObject();
      //输入json数据到前端页面
	   PrintWriter out=null;
	   try {
		   out=response.getWriter();
		   try {
			   boolean isLogin =  onlineAgentService.agentlogin(agentId);
			   if(!isLogin){//未登录
				   //生成坐席token
				   String token = JwtUtil.sign(agentId, 60L* 1000L* 30L);
				   //返回给客户端
				   returnMap.put("token",token);
				   returnMap.put("state","success");
			   }else{//已经登录情况下判断token
				   if(access_token!=null){
					   String unsign = JwtUtil.unsign(access_token, String.class);
					   if(agentId.equals(unsign)){
						   //生成坐席token
						   String token = JwtUtil.sign(agentId, 60L* 1000L* 30L);
						   //返回给客户端
						   returnMap.put("token",token);
						   returnMap.put("state","success");
					   }else{
						   returnMap.put("state","error");
					   }
				   }else{
					   returnMap.put("state","error");
				   }
				  
			   }
			} catch (Exception e) {
				 returnMap.put("state","error");
				 e.printStackTrace();
			}
		   out.write(""+callback+"('" + returnMap.toString() + "')");
	   } catch (Exception e) {
	  }finally{
		  if(out!=null){
			  out.flush();
			  out.close();
		  }
	  }
   }
   
   @RequestMapping("/login")
   public String login(HttpServletRequest request){
	       //处理坐席自己信息
		   try {
			  // request.setCharacterEncoding("GB2312");
			   HttpSession session = request.getSession();
			   String agentId = request.getParameter("agentid");
			   String agentName = request.getParameter("agentname");
			   String token = request.getParameter("token");
			   String time = DateUtil.datetimeFormat.format(new Date());
			   String group = request.getParameter("group");
			   String host = request.getParameter("host");
			   /*if(agentName==null){
				   agentName=agentId;
			   }*/
		       System.out.println("group"+group);
			   String type = "2";
			   Person person = new Person();
			   person.setPersonId(agentId);
			   person.setPersonName(agentName);
			   person.setType(type);
			   person.setGroup(group);
			   
			   if(!"undefined".equals(token)){
			       //此时是刷新或者登陆
				   String unsign = JwtUtil.unsign(token, String.class);
				   if(agentId.equals(unsign)){
					   //获取坐席
					   Agent agent = ACDFactory.createAgentManager().getAgent(agentId);
					   if(agent==null){
						   agent=onlineAgentService.getByAgentId(agentId);  
					   }
					   //保存到sesssion中
					   session.setAttribute("person",person);
					   session.setAttribute("host", host);
					   return "agent/chat";
					   
				   }else{
					   //保存到sesssion中
					   session.setAttribute("person",person);
					   session.setAttribute("host", host);
					   
					   //保存到坐席信息工厂中
					   Agent agent2 = new Agent();
					   agent2.setAgentId(agentId);
					   agent2.setAgentName(agentName);
					   agent2.setGroups(group);
					   agent2.setEntId(DataContext.AGENT_ENTID_DEFAULT);
					   agent2.setLoginTime(time);
					   agent2.setSerUsernum(0);
					   agent2.setStatus(DataContext.AGENT_STATUS_OFFLINE);
					   //坐席登录
					   agent2.setLogin(DataContext.AGENT_LOGIN_STATUS_ON);
					   ACDFactory.createAgentManager().addAgent(agent2);
					   /**
					    * 信息保存到数据库
					    */
					   Agent agent_tbl = onlineAgentService.getByAgentId(agentId);
					   if(agent_tbl==null){
						   onlineAgentService.save(agent2);
					   }else{
						   onlineAgentService.updateAgentByAgentId(agent2);
					   }
					  // onlineAgentService
					   System.out.println("坐席:"+person);
					   return "agent/chat";
				   }
			   }else{
				   //保存到sesssion中
				   session.setAttribute("person",person);
				   session.setAttribute("host", host);
				   
				   //保存到坐席信息工厂中
				   Agent agent2 = new Agent();
				   agent2.setAgentId(agentId);
				   agent2.setAgentName(agentName);
				   agent2.setGroups(group);
				   agent2.setEntId(DataContext.AGENT_ENTID_DEFAULT);
				   agent2.setLoginTime(time);
				   agent2.setSerUsernum(0);
				   agent2.setStatus(DataContext.AGENT_STATUS_OFFLINE);
				   //坐席登录
				   agent2.setLogin(DataContext.AGENT_LOGIN_STATUS_ON);
				   ACDFactory.createAgentManager().addAgent(agent2);
				   /**
				    * 信息保存到数据库
				    */
				   Agent agent_tbl = onlineAgentService.getByAgentId(agentId);
				   if(agent_tbl==null){
					   onlineAgentService.save(agent2);
				   }else{
					   onlineAgentService.updateAgentByAgentId(agent2);
				   }
				  // onlineAgentService
				   System.out.println("坐席:"+person);
				   return "agent/chat";
			   }
		} catch (Exception e) {
			e.printStackTrace();
			return "common/errorLogin";
		} 
    }
   /**
    * 坐席转移时显示所有坐席
    * @return
    */
   @RequestMapping("getFreeAgent")
   @ResponseBody
   public Object getFreeAgent(Integer pageNo,Integer pageSize,String agentId){
	try {
			   System.out.println("pageNo=["+pageNo+"] "+"pageSize=["+pageSize+"] "+"agentId=["+agentId+"] ");
			   return agentService.getByPage(pageNo, pageSize,agentId);
		} catch (Exception e) {
		      return null;
		}
   }
   /***
    * 检查坐席连接状态
    * @param userId
    * @return
    */
   @RequestMapping("checkWebSocketConn")
   @ResponseBody
   public Object checkWebSocketConn(String agentId){
	   Map<String,Object> jsonMap = new HashMap<String,Object>();
	   //  {"success",true}  {"success",false} 
	   List<WebSocketSession> socketSessionList = ACDFactory.createSocketAgentManager().getSocketSessionList(agentId);
	   //WebSocketSession webSocketSession = WebSocketSessionUtil.agentSocketSessionMap.get(agentId);
	   if(!socketSessionList.isEmpty()){
		  /* if(webSocketSession.isOpen()){
			   jsonMap.put("success", true);
		   }else{*/
		  jsonMap.put("success", false);
		   /*}*/
		   return jsonMap;
	   }else{
		   jsonMap.put("success", false);
		   return jsonMap;
	   }
   }
   
   /**
    * focus页面刷新时候初始化
    * @return
    */
   @RequestMapping("initfocus")
   @ResponseBody
   public void initfocus(HttpServletRequest request,HttpServletResponse response){
	   response.setContentType("text/plain");
	   response.addHeader("Access-Control-Allow-Origin","*");
	   String cb = request.getParameter("cb");//得到js函数名称
	   String agentId = request.getParameter("agentId");
	  //  Map<String,Object> jsonMap = new HashMap<String,Object>();
	    JSONObject jsonMap = new JSONObject();
	    PrintWriter out = null;
	    try {
	    	 try {
				 out = response.getWriter();
				 Agent agent = ACDFactory.createAgentManager().getAgent(agentId);
				 if(agent==null){
					 agent = onlineAgentService.getByAgentId(agentId);
					 ACDFactory.createAgentManager().addAgent(agent);
				 }
				 if(agent!=null){
					 String status = agent.getStatus();
					 System.out.println("agent status"+status+"staus"+status);
					// Agent agent = ACDUtil.agentInfoMap.get(agentId);
					 if(DataContext.AGENT_STATUS_OFFLINE.equals(status) || status==null){//离线
						 jsonMap.put("status","offline");
					 }else{
						 jsonMap.put("status", status);
					 }
				 }else{
					 jsonMap.put("status","offline");
				 }
			} catch (Exception e) {
				 //如果默认条件下为离线
				 jsonMap.put("status", "offline");
			}
	    	 out.write(""+cb+"('" + jsonMap.toString() + "')");
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
   }
   /**
    * 页面刷新时候初始化
    * @return
    */
   @RequestMapping("initpage")
   @ResponseBody
   public Object initPage(HttpServletRequest request){
	 Map<String,Object> jsonMap = new HashMap<String,Object>();
	 try {
		 String agentId = request.getParameter("agentId");
		 Agent agent = ACDFactory.createAgentManager().getAgent(agentId);
		 if(agent==null){
			 agent = onlineAgentService.getByAgentId(agentId);
			 ACDFactory.createAgentManager().addAgent(agent);
		 }
		 if(agent!=null){
			 String status = agent.getStatus();
			 System.out.println("agent status"+status+"staus"+status);
			// Agent agent = ACDUtil.agentInfoMap.get(agentId);
			 if(DataContext.AGENT_STATUS_OFFLINE.equals(status) || status==null){//离线
				 jsonMap.put("status","offline");
				 return jsonMap;
			 }else{
				 jsonMap.put("status", status);
				 return jsonMap;
			 }
		 }else{
			 jsonMap.put("status","offline");
			 return jsonMap;
		 }
	} catch (Exception e) {
		 //如果默认条件下为离线
		 jsonMap.put("status", "offline");
		 return jsonMap;
	}
   }
   /**
    * 判断用户是否存活
    * @param agentId
    * @param time
    * @return
    */
   @RequestMapping("survive")
   @ResponseBody
   public Object relogin(String agentId,Long time){
	   Map<String,Object> jsonMap = new HashMap<String,Object>();
	   try {
			  long serverTime = (new Date()).getTime();
			  System.err.println("agentId"+agentId+" time"+time+" serverTime"+serverTime);
			  //redis里面也
			  WebSocketSessionUtil.agentSurvive.put(agentId, serverTime);
			  //输出空闲坐席队列
			  System.err.println("空闲坐席 ["+ ACDFactory.freeAgentQueue.toString()+"]");
			  jsonMap.put("success",time);//将用户发送的事件返回去,用户自己判断
		} catch (Exception e) {
			 //如果默认条件下为离线
			 e.printStackTrace();
			 jsonMap.put("success","failure");
		}
	   return jsonMap;
   }
   
   /**
    * 在线
    * @param agentId
    * @param groups
    * @param status
    * @param agentName
    * @return
    */
   @RequestMapping("online")
   @ResponseBody
   public Object online(String agentId,String groups,String  agentName){
	   HashMap<String, Object> jsonMap = new HashMap<String,Object>();
	   /**
	     * 主变量
	     */
		Integer optCode = DataContext.AGENT_OPTCODE_ONLINE;
		
		/**
		 * default变量
		 */
		Integer entId = DataContext.AGENT_ENTID_DEFAULT;
		String queueId = DataContext.AGENT_QUEUEID_DEFAULT;
		String remark = DataContext.AGENT_REMARK_DEFAULT;
		Integer defaultThreadId = DataContext.AGENT_THREADID_DEFAULT;
		String toAgentId = DataContext.AGENT_TOAGENTID_DEFAULT;
		String toUserId = DataContext.AGENT_TOUSERID_DEFAULT;
		String userId = DataContext.AGENT_USERID_DEFAULT;
		String userName = DataContext.AGENT_USERNAME_DEFAULT;
	   
	   try {
			/***
			 * 坐席在线：
			 * 1.设置本坐席的状态为 online
			 * 2.坐席进入坐席信息map中(相当于更新free状态)
			 * 3.坐席进入空闲队列中(如果坐席已在队列中,不必进入)
			 * 4.通过S->A告知软电话状态为  online
			 */
			Agent agent = ACDFactory.createAgentManager().getAgent(agentId);
			if(agent==null){//如果为空就从数据库获取
				agent = onlineAgentService.getByAgentId(agentId);
				ACDFactory.createAgentManager().addAgent(agent);
			}
			if(agent!=null){
				if(!DataContext.AGENT_STATUS_ONLINE.equals(agent.getStatus())){
					/**
					 * 1.信息更新
					 */
					ACDFactory.createAgentManager().updateAgentStatus(agentId, DataContext.AGENT_STATUS_ONLINE);
					onlineAgentService.updateAgentStatusByAgentId(DataContext.AGENT_STATUS_ONLINE, agentId);
					
					/**
					 * 2.进入空闲队列
					 */
					ACDFactory.buildFreeAgentQueue(agent);
					
					/**
					 * 3.通知cti离线
					 */
					MessageTip ctitip = new MessageTip();
					ctitip.setCmd("webcconline");
					List<WebSocketSession> socketSessionList = ACDFactory.createStateSocketAgentManager().getSocketSessionList(agentId);
					webSocketHandler.sendTipMessage(socketSessionList,new TextMessage(ctitip.toJson()));
					webSocketService.agentOptLog(agentId, agentName, entId, optCode, DataContext.OPTRESULT_SUCCESS, queueId, remark, defaultThreadId, toAgentId, toUserId, userId, userName);
					jsonMap.put("success",true);
					return jsonMap;
				}else{
					ACDFactory.buildFreeAgentQueue(agent);
					jsonMap.put("success",true);
					return jsonMap;
				}
			}else{
				jsonMap.put("success",false);
				return jsonMap;
			}
		} catch (Exception e){
			e.printStackTrace();
			webSocketService.agentOptLog(agentId, agentName, entId, optCode, DataContext.OPTRESULT_ERROR, queueId, remark, defaultThreadId, toAgentId, toUserId, userId, userName);
			jsonMap.put("success",false);
			return jsonMap;
		}
   }
   
   /**
    * 忙碌
    * @return
    */
   @RequestMapping("busyline")
   @ResponseBody
   public Object busyline(String agentId,String  agentName){
	    Map<String, Object> jsonMap = new HashMap<String,Object>();
	    /**
	     * 主变量
	     */
		Integer optCode = DataContext.AGENT_OPTCODE_BUSYLINE;
		
		/**
		 * default变量
		 */
		Integer entId = DataContext.AGENT_ENTID_DEFAULT;
		String queueId = DataContext.AGENT_QUEUEID_DEFAULT;
		String remark = DataContext.AGENT_REMARK_DEFAULT;
		Integer defaultThreadId = DataContext.AGENT_THREADID_DEFAULT;
		String toAgentId = DataContext.AGENT_TOAGENTID_DEFAULT;
		String toUserId = DataContext.AGENT_TOUSERID_DEFAULT;
		String userId = DataContext.AGENT_USERID_DEFAULT;
		String userName = DataContext.AGENT_USERNAME_DEFAULT;
	   try {
		   /**
		    * 坐席忙碌:
		    * 坐席忙碌情况下,不能够进入新的会话,但是聊天记录执行,所以忙碌时候将坐席从空闲队列移除
		    * 
		    */
		   Integer optResult = DataContext.OPTRESULT_SUCCESS;
		    /**
		     * 1.坐席更新
		     */
			ACDFactory.createAgentManager().updateAgentStatus(agentId,DataContext.AGENT_STATUS_BUSYLINE);
			onlineAgentService.updateAgentStatusByAgentId(DataContext.AGENT_STATUS_BUSYLINE, agentId);
			
			/**
			 * 2.坐席从空闲队列中移除
			 */
			ACDFactory.removeAgent(agentId);
			
			/**
			 * 3.坐席操作记录
			 */
			webSocketService.agentOptLog(agentId,agentName,entId,optCode,optResult,queueId,remark,defaultThreadId,toAgentId,toUserId,userId,userName);
			jsonMap.put("success",true);
			return jsonMap;
		} catch (Exception e) {
			e.printStackTrace();
			 Integer optResult = DataContext.OPTRESULT_ERROR;
			webSocketService.agentOptLog(agentId,agentName,entId,optCode,optResult,queueId,remark,defaultThreadId,toAgentId,toUserId,userId,userName);
			jsonMap.put("success",false);
			return jsonMap;
		}
	   
   }
   
   /**
    * 离线
    * @param agentId
    * @param agentName
    * @param status
    * @return
    */
   @RequestMapping("offline")
   @ResponseBody
   public Object offline(String agentId,String  agentName){
	    HashMap<String, Object> jsonMap = new HashMap<String,Object>();
	    /**
	     * 主变量
	     */
	    Integer optCode = DataContext.AGENT_OPTCODE_OFFLINE;
	    
	    /**
	     * default 变量
	     */
	    Integer entId = DataContext.AGENT_ENTID_DEFAULT;
		String queueId = DataContext.AGENT_QUEUEID_DEFAULT;
		String remark = DataContext.AGENT_REMARK_DEFAULT;
		Integer defaultThreadId = DataContext.AGENT_THREADID_DEFAULT;
		String toAgentId = DataContext.AGENT_TOAGENTID_DEFAULT;
		String toUserId = DataContext.AGENT_TOUSERID_DEFAULT;
		String userId = DataContext.AGENT_USERID_DEFAULT;
		String userName = DataContext.AGENT_USERNAME_DEFAULT;
	   try {
			/***
			 * 坐席离线不比坐席忙碌:
			 * 1.坐席从坐席信息map中不能移除,为了判断在页面刷新时候状态,获取坐席，修改状态再放入队列
			 * 2.坐席离线之后坐席提出空闲队列
			 * 3.通过S->A接口告知软电话此时状态
			 * 4.通知其所服务的所有用户,此坐席已经离开了会话
			 * 5.结束fouss_im_thread表中聊天
			 */
			//1.坐席从坐席信息map中不能移除,为了判断在页面刷新时候状态,获取坐席，修改状态再放入队列
		    Agent agent = ACDFactory.createAgentManager().getAgent(agentId);
		    if(agent==null){
		    	agent = onlineAgentService.getByAgentId(agentId);
		    	ACDFactory.createAgentManager().addAgent(agent);
		    }
		    if(agent!=null){
		    	/**
		    	 * 1.坐席从空间队列中移除,告知前台离线
		    	 */
				ACDFactory.removeAgent(agentId);
				webSocketService.agentOptLog(agentId, agentName, entId, optCode, DataContext.OPTRESULT_SUCCESS, queueId, remark, defaultThreadId, toAgentId, toUserId, userId, userName);
				/**
				 * 2.通知其所服务的所有用户,此坐席已经离开了会话
				 */
				AgentLogoff agentLogoff = new AgentLogoff();
				agentLogoff.setCmd("agentlogoff");
				agentLogoff.setAgentId(agentId);
				  //通过agentId和status获取所有在线用户
				List<ThreadVO> threadList = threadService.getThreadListByEndStatusAndAgentId(agentId);
				
				/**
		    	 * 3.信息更新(缓存中,thread表中,agentuser表中,onlineagent表中)
		    	 */
				for (ThreadVO threadVO : threadList) {
	 				//@WebSocketSession userSocket = WebSocketSessionUtil.userSocketSessionMap.get(threadVO.getUserId());
	               // WebSocketSession userSession = ACDFactory.createSocketUserManager().getSocketSession(threadVO.getUserId());
					List<WebSocketSession> socketSessionList = ACDFactory.createSocketUserManager().getSocketSessionList(threadVO.getUserId());
					webSocketHandler.sendTipMessage(socketSessionList, new TextMessage(agentLogoff.toJson()));//发送结束消息
					//5.结束fouss_im_thread表中聊天
					threadService.updateStatusBySessionIdAndAgentId(2, threadVO.getSessionId(), agentId);
					//threadDao.updateStatusBySessionIdAndAgentId(2, threadVO.getSessionId(), agentId);//fouss_im_thread聊天结束
				}
				 
				//agentUserService.updateStatusByAgentId(DataContext.CHAT_STATUS_END,agentId);
				Integer chatType = DataContext.CHATTYPE_CHAT;
				agentUserService.updateStatusAndChatTypeByAgentId(DataContext.CHAT_STATUS_END, chatType, agentId);
				
				ACDFactory.createAgentManager().updateAgentStatus(agentId,DataContext.AGENT_STATUS_OFFLINE);
		    	onlineAgentService.updateAgentStatusByAgentId(DataContext.AGENT_STATUS_OFFLINE,agentId);
				
				/**
				 * 5.发送给CTI通知坐席状态
				 */
				MessageTip ctitip = new MessageTip();
				ctitip.setCmd("webcclogout");
				//@WebSocketSession stateSession = WebSocketSessionUtil.agentStateSessionMap.get(agentId);
				//WebSocketSession stateSession = ACDFactory.createStateSocketAgentManager().getSocketSession(agentId);
				List<WebSocketSession> socketSessionList = ACDFactory.createStateSocketAgentManager().getSocketSessionList(agentId);
				webSocketHandler.sendTipMessage(socketSessionList,new TextMessage(ctitip.toJson()));
				jsonMap.put("success",true);
				return jsonMap;
		    }else{
		    	jsonMap.put("success",false);
				return jsonMap;
		    }
		} catch (Exception e){
			webSocketService.agentOptLog(agentId, agentName, entId, optCode, DataContext.OPTRESULT_ERROR, queueId, remark, defaultThreadId, toAgentId, toUserId, userId, userName);
			e.printStackTrace();
			jsonMap.put("success",false);
			 return jsonMap;
		}
   }
   /*private void agentloginInit(String agentId,String agentName,String groups) {
		try {
			 //2.防止点击离线的时候出现空指针异常,就应该在建立连接时候往信息队列中加入值,但是需要判断是否存在存在不添加
			//构造坐席信息entity
			String time = DateUtil.datetimeFormat.format(new Date());
			Agent agent = new Agent();
			agent.setAgentName(agentName);
			agent.setAgentId(agentId);
			agent.setGroups(groups);
			agent.setLoginTime(time);
			agent.setStatus("offline");//开始都是离线的
			agent.setLogin(true);
			agent.setUserId("");// 此处登陆时候并没有服务对象，设置为空
			agent.setServiceNum(0);// 刚开始坐席服务人数是0；
			
			boolean contains = ACDFactory.createAgentManager().contains(agentId);
			//ACDFactory.freeAgentQueue
			if(!contains){//不存在
				try {
					// 2.将信息保存到坐席信息队列中
					webSocketService.agentOptLog(agentId, agent.getAgentName(),-1, 9, 1, "", "", -1, "", "", "", "");
					ACDFactory.createAgentManager().addAgent(agent);
				} catch (Exception e) {
					webSocketService.agentOptLog(agentId, agent.getAgentName(),-1, 9, 0, "", "", -1, "", "", "", "");
					e.printStackTrace();
				}
			}else{//坐席存在时候设置其已经登录
				//@Agent agent = ACDUtil.agentInfoMap.get(agentId);
				Agent agent1 = ACDFactory.createAgentManager().getAgent(agentId);
				agent1.setLogin(true);
				ACDFactory.createAgentManager().addAgent(agent1);
			}
			//坐席进入空闲队列 坐席在登录时候不一定进入空闲队列
			//ACDFactory.buildFreeAgentQueue(agent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
   
   /**
    * 坐席注销
    * @param agentId
    * @return
    */
   @RequestMapping("logout")
   @ResponseBody
   public Object logout(String agentId,String agentName){
	    Map<String, Object> jsonMap = new HashMap<String,Object>();
	    /**
	     * 主变量
	     */
		Integer optCode = DataContext.AGENT_OPTCODE_LOGOUT;
		
		/**
		 * default变量
		 */
		Integer entId = DataContext.AGENT_ENTID_DEFAULT;
		String queueId = DataContext.AGENT_QUEUEID_DEFAULT;
		String remark = DataContext.AGENT_REMARK_DEFAULT;
		Integer defaultThreadId = DataContext.AGENT_THREADID_DEFAULT;
		String toAgentId = DataContext.AGENT_TOAGENTID_DEFAULT;
		String toUserId = DataContext.AGENT_TOUSERID_DEFAULT;
		String userId = DataContext.AGENT_USERID_DEFAULT;
		String userName = DataContext.AGENT_USERNAME_DEFAULT;
	   try {
		   /**
		   * 1.坐席更新
		   */
		   ACDFactory.createAgentManager().updateAgentStatus(agentId,DataContext.AGENT_STATUS_OFFLINE);
		   Agent agent = ACDFactory.createAgentManager().getAgent(agentId);
		   if(agent==null){
			   agent = onlineAgentService.getByAgentId(agentId);
			   ACDFactory.createAgentManager().addAgent(agent);
		   }
		   if(agent!=null){
			   agent.setStatus(DataContext.AGENT_STATUS_OFFLINE);
			   agent.setLogin(DataContext.AGENT_LOGIN_STATUS_OFF);
			   onlineAgentService.updateAgentByAgentId(agent);
			   webSocketService.agentOptLog(agentId, agentName, entId, optCode, DataContext.OPTRESULT_SUCCESS, queueId, remark,defaultThreadId, toAgentId, toUserId, userId, userName);
			   /**
			    * 2.坐席从空闲队列中移除
			    */
			   ACDFactory.removeAgent(agentId);
			   jsonMap.put("success", true);
		   }else{
			   jsonMap.put("success",false);
		   }
		   return jsonMap;
		} catch (Exception e) {
			e.printStackTrace();
			webSocketService.agentOptLog(agentId, agentName, entId, optCode, DataContext.OPTRESULT_ERROR, queueId, remark, defaultThreadId, toAgentId, toUserId, userId, userName);
			jsonMap.put("success", false);
		    return jsonMap;
		}
   }
   
   
   @RequestMapping("transfer")
   @ResponseBody
   public Object transfer(HttpServletRequest request,Integer sessionId){
	  Map<String, Object> jsonMap = new HashMap<String,Object>();
      try {
		/****
		 * 转接客户其实就是给用户重新分配除了该坐席以外的其他坐席 一个id就是一个通道
		 */
		/**
		 * 获取参数
		 */
		String userId = request.getParameter("userId");
		String userName =request.getParameter("userName"); 
		Integer	source=Integer.parseInt(request.getParameter("source"));
		String agentId = request.getParameter("agentId");
		String toagentId = request.getParameter("toagentId");
		String time = DateUtil.datetimeFormat.format(new Date());
		Integer threadId = threadService.getThreadByEndStatusAndUserIdAndAgentId(userId, agentId).getThreadId();
		 
		/**
		 * 获取websocketSession
		 */
		//WebSocketSession userSession = ACDFactory.createSocketUserManager().getSocketSession(userId);
		List<WebSocketSession> userSession = ACDFactory.createSocketUserManager().getSocketSessionList(userId);
		//WebSocketSession toagentSession = ACDFactory.createSocketAgentManager().getSocketSession(toagentId);
		List<WebSocketSession> toagentSession = ACDFactory.createSocketAgentManager().getSocketSessionList(toagentId);
		AgentTransferServiceConfirm agentTransferServiceConfirm = new AgentTransferServiceConfirm();
		agentTransferServiceConfirm.setCmd("agenttransferservice");
		
		/**
		 * 获取实体类
		 */
		Agent toAgent = ACDFactory.createAgentManager().getAgent(toagentId);
		if(toAgent==null){
			toAgent=onlineAgentService.getByAgentId(toagentId);
			ACDFactory.createAgentManager().addAgent(toAgent);
		}
		Agent agent = ACDFactory.createAgentManager().getAgent(agentId);
		if(agent==null){
			agent=onlineAgentService.getByAgentId(agentId);
			ACDFactory.createAgentManager().addAgent(agent);
		}
		if(agent != null && toAgent != null){
			String agentName = agent.getAgentName();
			String toAgentName = toAgent.getAgentName();
			
			/**
			 * 判断服务质量是否达标
			 */
			if(serveNumCalculate(toAgent) && ACDFactory.containsFreeAgent(toagentId)){	//判断目标坐席是否已经离线
				/**
				 * 查看本次sessionId和agentId所对应的聊天记录是否存在,如果存在,则不创建,如果不存在就创建
				 */
				AgentUserVO agentUser = agentUserService.getAgentUserByUserIdAndAgentId(userId, toagentId);
				ThreadVO threadVo = new ThreadVO();//在if-else之外都需要访问
				AgentUserVO agentUserVO = new AgentUserVO();
				if(agentUser==null){
					
					//保存agentuser
					agentUserVO.setAgentId(toagentId);
					agentUserVO.setAgentName(toAgentName);
					//agentUserVO.setEntId(entId);
					agentUserVO.setSessionId(sessionId);
					agentUserVO.setSource(source);
					agentUserVO.setStatus(0);
					agentUserVO.setUserId(userId);
					agentUserVO.setUserName(userName);	
					agentUserVO.setChatType(DataContext.CHATTYPE_TRANSFER);//坐席转移
					agentUserService.saveUnAutoSessionId(agentUserVO);
					
					//保存thread
					threadVo.setAgentId(toagentId);
					threadVo.setAgentName(toAgentName);
					threadVo.setSource(source);
					threadVo.setStartTime(time);
					threadVo.setUserId(userId);
					threadVo.setUserName(userName);
					threadVo.setStatus(0);// 0.代表等待聊天
					threadVo.setResponseTime("");
					threadVo.setEndTime("");
					threadVo.setStartCause(1);
					threadVo.setSessionId(sessionId);
					threadVo.setAgentUserId(agentUserVO.getAgentUserId());
					try {
						threadService.save(threadVo);
						//agentId转出
						agentOptLog(agentId,agentName,-1, 51, 1, "", "", threadId, toagentId, "", userId, userName);
					   //toAgentId转入
						agentOptLog(toagentId,toAgentName,-1, 50, 1, "", "", threadVo.getThreadId(), "", "", userId, userName);
					} catch (Exception e) {
						e.printStackTrace();
						//agentId转出
						agentOptLog(agentId,agentName,-1, 51, 0, "", "", threadId, toagentId, "", userId, userName);
					   //toAgentId转入
						agentOptLog(toagentId,toAgentName,-1, 50, 0, "", "", threadVo.getThreadId(), "", "", userId, userName);
					}
					
					/**
					 * 原来agentuser,thread状态设置为2结束
					 */
					//agentUserService.updateStatusByUserIdAndAgentId(2, userId, agentId);
					 Integer chatStatus = DataContext.CHAT_STATUS_END;
					 Integer chatType = DataContext.CHATTYPE_CHAT;
					 agentUserService.updateStatusAndChatTypeByUserIdAndAgentId(chatStatus,chatType,userId,agentId);
					String responseTime= messageService.getResponseTimeByThreadIdAndAgentId(threadId, agentId);
					threadService.agentEndByEndStatusAndUserIdAndAgentId(2, responseTime, 6, time, userId,agentId);
				 }else{//存在
				    /**
				     * 原来agentuser,thread状态设置为2结束
				     */
					agentUserService.updateStatusAndChatTypeByUserIdAndAgentId(DataContext.CHAT_STATUS_END,DataContext.CHATTYPE_CHAT,userId,agentId);
					String responseTime=messageService.getResponseTimeByThreadIdAndAgentId(threadId,agentId);
					String endTime = DateUtil.datetimeFormat.format(new Date());
					threadService.agentEndByEndStatusAndUserIdAndAgentId(2, responseTime, 6, endTime, userId, toagentId);
					
					/**
					 * 如果转移的目标坐席已经建立连接,我们需要agentuser状态设置为1聊天  thread新建聊天
					 */
					Integer chatStatus = DataContext.CHAT_STATUS_WAITING;
					Integer chatType = DataContext.CHATTYPE_TRANSFER;
					agentUserService.updateStatusAndChatTypeByUserIdAndAgentId(chatStatus,chatType,userId,toagentId);
					//判断userid和toagentid之间聊天是否存在,不存在就建立
					ThreadVO thread = threadService.getThreadByEndStatusAndUserIdAndAgentId(userId, toagentId);
					if(thread==null){
						threadVo.setAgentId(agentUser.getAgentId());
						threadVo.setAgentName(agentUser.getAgentName());
						threadVo.setAgentUserId(agentUser.getAgentUserId());
						threadVo.setSource(2);
						threadVo.setStartTime(time);
						threadVo.setUserId(agentUser.getUserId());
						threadVo.setUserName(agentUser.getUserName());
						threadVo.setStatus(0);// 0.代表等待聊天
						threadVo.setStartCause(0);//0-代表用户发起
						threadVo.setResponseTime("");
						threadVo.setStartCause(0);
						threadVo.setEndTime("");
						threadVo.setSessionId(agentUser.getSessionId());
						threadService.save(threadVo);
					}
				}
				/**
				 * 先转移成功之后再判断通知本坐席和用户(通知坐席和用户)
				 */
				MessageTip messageTip = new MessageTip();
				messageTip.setCmd("userstartchat");
				messageTip.setFromId(threadVo.getUserId());
				messageTip.setFromName(threadVo.getUserName());
				messageTip.setOwnerId(threadVo.getUserId());
				messageTip.setOwnerName(threadVo.getUserName());
				messageTip.setShow("坐席"+toagentId);
				messageTip.setSessionId(threadVo.getSessionId());
				messageTip.setSource(threadVo.getSource());
				if(ACDFactory.containsFreeAgent(toagentId)){
					webSocketHandler.sendTipMessage(toagentSession,new TextMessage(messageTip.toJson()));
					// 2. 将agentId发给userId所对应的页面，更新userId所对应页面的通道
					agentTransferServiceConfirm.setAgentTransferServiceFlag("success");
					agentTransferServiceConfirm.setAgentTransferServiceSessionId(""+sessionId);
					agentTransferServiceConfirm.setAgentTransferServiceAgentId(toagentId);
					webSocketHandler.sendTipMessage(userSession, new TextMessage(
							agentTransferServiceConfirm.toJson()));
					jsonMap.put("success",true);
					return jsonMap;
				}else{
					jsonMap.put("success",false);
					return jsonMap;
				}
			}else{//转移坐席失败
				jsonMap.put("success",false);
				return jsonMap;
			  }
		}else{
			jsonMap.put("success",false);
			return jsonMap;
		}
		}catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("success",false);
			return jsonMap;
		}
	}

   
   @RequestMapping("agentend")
   @ResponseBody
   public Object agentend(String agentId,String userId,Integer sessionId){
	   Map<String, Object> jsonMap = new HashMap<String,Object>();
	   try {
		   System.out.println(agentId+" "+userId+" "+sessionId);
			/**
			 * 获取参数
			 */
		    //因为在群聊的条件下threadid是不同的但是如果此时参数传递时候用一个threadid那么将会出现问题
		    ThreadVO thread = threadService.getThreadByEndStatusAndUserIdAndAgentId(userId, agentId);
		    if(thread!=null){
		    	String endTime = DateUtil.datetimeFormat.format(new Date());
				String responseTime=messageService.getResponseTimeByThreadIdAndAgentId(thread.getThreadId(),agentId);
				/**
				 * 设置状态为2结束
				 */
				// 2.坐席状态减去1（根据坐席agentid找到坐席对应坐席，然后覆盖坐席map）
				//@Agent agent = (Agent) ACDUtil.agentInfoMap.get(agentId);
				Agent agent = ACDFactory.createAgentManager().getAgent(agentId);
				User user = ACDFactory.createUserManager().getUser(userId);
				if(agent==null){
					agent = onlineAgentService.getByAgentId(agentId);
					ACDFactory.createAgentManager().addAgent(agent);
				}
				if(user==null){
					user=onlineUserService.getByUserId(userId);
					ACDFactory.createUserManager().addUser(user);
				}
				if(agent!=null && user!=null){
					String userName = user.getUserName();
					try {
						 /**
						  * 结束聊天 改变状态
						  */
						 //agentUserService.updateStatusByUserIdAndAgentId(2, userId, agentId);
						 Integer chatStatus = DataContext.CHAT_STATUS_END;
						 Integer chatType = DataContext.CHATTYPE_CHAT;
						 agentUserService.updateStatusAndChatTypeByUserIdAndAgentId(chatStatus,chatType,userId,agentId);
						 threadService.agentEndByEndStatusAndUserIdAndAgentId(2,responseTime,0,endTime,userId,agentId);
						 String agentName = agent.getAgentName();
						 Integer threadId = thread.getThreadId();
						 agentOptLog(agentId,agentName,-1, 31, 1, "", "",threadId, "", "", userId,userName);
					} catch (Exception e) {
						 e.printStackTrace();
						 agentOptLog(agentId, agent.getAgentName(),-1, 31, 0, "", "", thread.getThreadId(), "", "", userId,userName);
					}
					//agent.setServiceNum(agent.getServiceNum() - 1);
					//@ACDUtil.agentInfoMap.put(agentId, agent);
					//ACDFactory.createAgentManager().addAgent(agent);
					/**
					 * 用户提示已经结束聊天
					 */
					AgentEnd agentEnd = new AgentEnd();
					agentEnd.setAgentId(agentId);
					agentEnd.setUserId(userId);
					agentEnd.setCmd("agentend");
					agentEnd.setSessionId(sessionId);
					agentEnd.setEndContent(agentId+" 离开了对话");
					//WebSocketSession userSession = ACDFactory.createSocketUserManager().getSocketSession(userId);
					List<WebSocketSession> userSession = ACDFactory.createSocketUserManager().getSocketSessionList(userId);
					webSocketHandler.sendTipMessage(userSession, new TextMessage(
							agentEnd.toJson()));
					/**
					 * 判断聊天服务数量,然后进入空闲队列
					 */
					enterQueue(agent);
				    jsonMap.put("success", true);
				}
		    }else{
		    	jsonMap.put("success", true);
		    }
		    /**
		     * 设置聊天状态为结束
		     */
		    User user =  ACDFactory.createUserManager().getUser(userId);
		    if(user!=null){
		    	user.setChatStatus(DataContext.USER_CHATSTATUS_END);
		    	ACDFactory.createUserManager().addUser(user);
		    }
		    return jsonMap;
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("success", false);
		    return jsonMap;
		}
   }
   
   @RequestMapping("groupchat")
   @ResponseBody
   public Object groupchat(HttpServletRequest request){
	   Map<String, Object> jsonMap = new HashMap<String,Object>();
	   try {
		     /**
			 * 获取参数
			 */
			 String agentId = request.getParameter("agentId");
			 String toAgentId = request.getParameter("toAgentId");
			 String userId = request.getParameter("userId"); 
			 String userName = request.getParameter("userName");
			 Integer source = Integer.parseInt(request.getParameter("source"));
			 Integer sessionId = Integer.parseInt(request.getParameter("sessionId"));
			 Agent toAgent = ACDFactory.createAgentManager().getAgent(toAgentId);
			 if(toAgent==null){
				 toAgent=onlineAgentService.getByAgentId(toAgentId);
				 ACDFactory.createAgentManager().addAgent(toAgent);
			 }
			 Agent agent = ACDFactory.createAgentManager().getAgent(agentId);
			 if(agent==null){
				 agent=onlineAgentService.getByAgentId(agentId);
				 ACDFactory.createAgentManager().addAgent(agent);
			 }
			 if(toAgent!=null && agent!=null){
				 String toAgentName = toAgent.getAgentName();
			     ThreadVO threadTemp=  threadService.getThreadByEndStatusAndUserIdAndAgentId(userId, agentId);
			     Integer threadId =0;
				 if(threadTemp!=null){
					 threadId = threadTemp.getThreadId();
				 }
				 /**
				  * 获取websocketsession
				  */
				 List<WebSocketSession> toAgentSession = ACDFactory.createSocketAgentManager().getSocketSessionList(toAgentId);  
				 List<WebSocketSession> userSession = ACDFactory.createSocketUserManager().getSocketSessionList(userId);
				 /**
				 * 执行流程
				 */
			     AgentGroupChatConfirm groupChat = new AgentGroupChatConfirm();
				 groupChat.setCmd("groupchat");
				 //判断是否在服务数量之内
				 if(serveNumCalculate(toAgent) && ACDFactory.containsFreeAgent(toAgentId)){
		        	 /**
		        	  * 查找群聊目标坐席和本用户的这次sessionId之间是否建立过连接
		        	  */
		        	AgentUserVO agentUser = agentUserService.getAgentUserByUserIdAndAgentId(userId, toAgentId);
		        	AgentUserVO agentUserVO = new AgentUserVO();
		        	ThreadVO threadVo = new ThreadVO();
					 if(agentUser==null){//没有就建立
						//保存agentuser
						agentUserVO.setAgentId(toAgentId);
						agentUserVO.setAgentName(toAgentName);
						//agentUserVO.setEntId(entId);
						agentUserVO.setSessionId(sessionId);
						agentUserVO.setSource(source);
						agentUserVO.setStatus(0);
						agentUserVO.setUserId(userId);
						agentUserVO.setUserName(userName);	
						agentUserVO.setChatType(DataContext.CHATTYPE_GROUPCHAT);
						agentUserService.saveUnAutoSessionId(agentUserVO);
							
						//保存thread
						threadVo.setAgentId(toAgentId);
						threadVo.setAgentName(toAgentName);
						threadVo.setAgentUserId(agentUserVO.getAgentUserId());
						threadVo.setSource(source);
						String time = DateUtil.datetimeFormat.format(new Date());
						threadVo.setStartTime(time);
						threadVo.setUserId(userId);
						threadVo.setUserName(userName);
						threadVo.setStatus(0);// 0.代表等待聊天
						threadVo.setResponseTime("");
						threadVo.setEndTime("");
						threadVo.setStartCause(2);//2。群聊开始
						threadVo.setSessionId(sessionId);
						try {
							threadService.save(threadVo);
							//群聊发起
							agentOptLog(agentId,agent.getAgentName(),-1, 60, 1, "", "", threadId, toAgentId, "", userId, userName);
							//群聊接受
							agentOptLog(toAgentId,toAgent.getAgentName(),-1, 61, 1, "", "", threadVo.getThreadId(), "", "", userId, userName);
						} catch (Exception e) {
							agentOptLog(agentId,agent.getAgentName(),-1,60, 0, "", "", threadId, toAgentId, "", userId, userName);
							//群聊接受
							agentOptLog(toAgentId,toAgent.getAgentName(),-1, 61,0, "", "", threadVo.getThreadId(), "", "", userId, userName);
							e.printStackTrace();
						}
					 }else{
						 /**
						  * 如果存在我们查询在thread表中是否也存在正在聊天的,并在thread就建立新的子链接
						  */
							//agentUserService.updateStatusByUserIdAndAgentId(0, userId, toAgentId);
							Integer chatStatus = DataContext.CHAT_STATUS_WAITING;
							Integer chatType = DataContext.CHATTYPE_GROUPCHAT;
							agentUserService.updateStatusAndChatTypeByUserIdAndAgentId(chatStatus,chatType,userId,toAgentId);
							
							//判断userid和toagentid之间聊天是否存在,不存在就建立
							ThreadVO thread = threadService.getThreadByEndStatusAndUserIdAndAgentId(userId, toAgentId);
							String time = DateUtil.datetimeFormat.format(new Date());
							if(thread==null){
								threadVo.setAgentId(agentUser.getAgentId());
								threadVo.setAgentName(agentUser.getAgentName());
								threadVo.setAgentUserId(agentUser.getAgentUserId());
								threadVo.setSource(2);
								threadVo.setStartTime(time);
								threadVo.setUserId(agentUser.getUserId());
								threadVo.setUserName(agentUser.getUserName());
								threadVo.setStatus(0);// 0.代表等待聊天
								threadVo.setResponseTime("");
								threadVo.setStartCause(2);
								threadVo.setEndTime("");
								threadVo.setSessionId(agentUser.getSessionId());
								try {
									threadService.save(threadVo);
									//群聊发起
									agentOptLog(agentId,agent.getAgentName(),-1, 60, 1, "", "", threadId, toAgentId, "", userId, userName);
									//群聊接受
									agentOptLog(toAgentId,toAgent.getAgentName(),-1, 61, 1, "", "", threadVo.getThreadId(), "", "", userId, userName);
								} catch (Exception e) {
									agentOptLog(agentId,agent.getAgentName(),-1, 60, 0, "", "", threadId, toAgentId, "", userId, userName);
									//群聊接受
									agentOptLog(toAgentId,toAgent.getAgentName(),-1, 61,0, "", "", threadVo.getThreadId(), "", "", userId, userName);
									e.printStackTrace();
								}
							}
					 }
					 groupChat.setAgentGroupChatFlag("success");
					 groupChat.setGroupChatAgentId(toAgentId);
					 groupChat.setAgentGroupChatSessionId(sessionId);
					 
					 //给toAgentId坐席提示
					 MessageTip messageTip = new MessageTip();
					 messageTip.setCmd("userstartchat");
					 messageTip.setFromId(userId);
					 messageTip.setFromName(userName);
					 
					 messageTip.setOwnerId(userId);
					 messageTip.setOwnerName(userName);
					 messageTip.setShow("坐席"+toAgentId);
					 
					 messageTip.setSessionId(agentUser.getSessionId());
					 messageTip.setSource(source);
					 if(ACDFactory.containsFreeAgent(toAgentId)){
						 webSocketHandler.sendTipMessage(userSession, new TextMessage(groupChat.toJson()));
						 messageTip.setCmd("groupchatuserstartchat");
						 messageTip.setAgentId(agentId);
						 webSocketHandler.sendTipMessage(toAgentSession, new TextMessage(messageTip.toJson()));
						 jsonMap.put("success",true);
					 }else{
						 jsonMap.put("success",false);
					 }
		        }else{
		        	jsonMap.put("success",false);
		        }
			 }else{
				 jsonMap.put("success",false); 
			 }
		  return jsonMap;
	  } catch (Exception e) {
		  e.printStackTrace();
		  jsonMap.put("success",false);
		  return jsonMap;
	 }
   }
   
	/*************************
	 * 坐席进入队列
	 * *******************/
	private void enterQueue(Agent agent){
		if(agent!=null){
			 String status = agent.getStatus();
				//坐席进入队列条件是服务数量和坐席没有离线
				if(serveNumCalculate(agent) && !DataContext.AGENT_STATUS_OFFLINE.equals(status)){
					ACDFactory.buildFreeAgentQueue(agent);
			}
		}
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
	
	/***
	 * 计算坐席服务数量
	 * @param agent
	 * @return
	 */
	private boolean serveNumCalculate(Agent agent){
		 try {
		    ParamVO maxUserVO =  new ParamVO();
		    maxUserVO = paramService.getMaxUser(DataContext.PARAM_MAXUSER, agent.getAgentId());
		    Integer maxAgentServerNum = 1;
			//获取本坐席服务数量（从thread表中）
		    Integer agentServerNum = agentUserService.getSernumByEndStatusAndAgentId(agent.getAgentId());
		    if(maxUserVO!=null){
				maxAgentServerNum =  Integer.parseInt(maxUserVO.getParamValue());
			}
			if(agentServerNum<maxAgentServerNum){
				return true;
			}else{
				//如果坐席服务数量和总数量相等时候,移除空闲队列
				ACDFactory.removeAgent(agent.getAgentId());
				return false;
			}
		} catch (NumberFormatException e){
			e.printStackTrace();
			return false;
		}
	}
}







