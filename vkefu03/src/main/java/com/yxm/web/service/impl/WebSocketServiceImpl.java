package com.yxm.web.service.impl;
import org.springframework.stereotype.Service;

import com.yxm.web.service.WebSocketService;

/**
 * 处理从websocket来的消息
 * 
 * @author yxm
 * @date 2016-11-16
 */
@Service("webSockService")
public class WebSocketServiceImpl implements WebSocketService {/*
	//加锁
    static final Lock instanceLock = new ReentrantLock();
	@Autowired
	private AgentOptLogDao agentOptLogDao;
	@Resource(name = "threadDao")
	private ThreadDao threadDao;

	@Resource(name = "messageDao")
	private MessageDao messageDao;
	
	@Autowired
	private ParamDao paramDao;
	
	@Autowired
	private AgentUserDao agentUserDao;
	
	@Autowired
	private OnlineAgentDao onlineAgentDao;
	
	@Autowired
	private OnlineUserDao onlineUserDao;
	
	@Autowired
	private SystemWebSocketHandler webSocketHandler;

	@Autowired
	private UserOptLogService userOptService;
	private static final Logger  logger;
	private static final SimpleDateFormat sdf;
	static {
		logger=Logger.getLogger(WebSocketServiceImpl.class);
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 日期格式化
	}
	@Override
	public void webccstart(Map<String,String> map, WebSocketSession session){
		try {
	    
		 * 用户登录完以后 1.根据json创建用户对象 2.判断用户类型,加入到相应队列中 3.根据用户的group分配坐席
		 * 4.坐席不为空且服务人数没有达到上限则建立连接 5.发送欢迎语 6.将坐席放在队尾 7.发送坐席坐席状态给前端控制
		 
    		String group = map.get("group");
			String userId = map.get("userId");
			String userName = map.get("userName");
			String lastMsgId = map.get("lastMsgId");
			//String sessionId = map.get("sessionId");
			 ACDFactory.createSocketUserManager().addWebsocketSession(userId, session);
			
			User user = new User();
			user.setUserId(userId);
			user.setUserName(map.get("userName"));
			user.setLoginTime(DateUtil.datetimeFormat.format(new Date()));
			*//**
			 * 获取未结束消息
			 *//*
			List<AgentUserVO> agentUserList = agentUserDao.getAgentUserListByEndStatusAndUserId(userId);
			//List<ThreadVO> threadList = threadDao.getThreadListByEndStatusAndUserId(userId);
			if(agentUserList !=null && agentUserList.size()>0){//说明是轮训后的立即连接,不做处理
				UserConnection userConnection = new UserConnection();
				userConnection.setCmd("userconnection");
				for (AgentUserVO agentUserVO : agentUserList) {
					userConnection.setAgentId(agentUserVO.getAgentId());
					userConnection.setAgentName(agentUserVO.getAgentName());
					userConnection.setSessionId(agentUserVO.getSessionId());
					break;
				}
				if("0".equals(lastMsgId)){//页面刷新
					    // Integer[] sessionIds = threadDao.getSessionIdByEndStatusAndUserId(userId);
					    Integer lastmsgid=0;
						//根据sessionId获取threadList（未读消息取100条）
					 //    for (Integer sessionId : sessionIds) {
					for (AgentUserVO agentUserVO : agentUserList) {
						 List<MessageVO> unreadUserMessage = messageDao.getUnreadUserMessage(agentUserVO.getSessionId(),lastmsgid);
					      for (MessageVO messageVO : unreadUserMessage) {
					    	  webSocketHandler.sendMessageToUser(userId, messageVO);
					 	 }
					}
					//     }
					  //清空未读消息
					  ACDFactory.createUserManager().getMessage(userId);
				}else{	//读取未读消息(直接从对应的缓存中读取)
					List<MessageVO> message = ACDFactory.createUserManager().getMessage(userId);
					for (MessageVO messageVO : message) {
						webSocketHandler.sendMessageToUser(userId, messageVO);
					}
				}
				//先判断此坐席是否离线,离线的话结束聊天,不然继续服务
				List<WebSocketSession> socketSessionList = ACDFactory.createSocketUserManager().getSocketSessionList(userId);
				webSocketHandler.sendTipMessage(socketSessionList,new TextMessage(userConnection.toJson()));
			}else{ 
				User outUser = ACDFactory.createUserManager().getUser(userId);
				if(outUser == null){
					outUser = onlineUserDao.getByUserId(userId);
					ACDFactory.createUserManager().addUser(outUser);
				}
				if(outUser!=null && !DataContext.USER_CHATSTATUS_END.equals(outUser.getChatStatus())){
					if ("webcc".equals(group)){
						//用户进入队列记一个时间
						outUser.setSource(DataContext.USER_SOURCE_WEBCC);
						ACDFactory.createUserManager().addUser(outUser);
						*//**
						 * 户进队列记录日志
						 *//*
						String startTime = DateUtil.datetimeFormat.format(new Date());
						Integer optCode = DataContext.USER_OPTCODE_ENQUEUE;
						String queueId = "";
						String remark = "";
						Integer optResult = DataContext.OPTRESULT_SUCCESS;
						userOptLog(userId, userName, optCode, queueId, startTime, remark, optResult);
						*//**
						 * 用户进入队列
						 *//*
						ACDFactory.addWebccUserQueue(outUser);
					}
			 	  chat();//分配坐席用户聊天
				}
			} 
		} catch (Exception e) {
			logger.error("webccstart error");
			e.printStackTrace();
		}
	}
	@Override
	public void webchatclientmsg(Map<String,String> map, WebSocketSession session){
		try {
			Integer threadId=0;
			System.out.println("webchatclientmsg中 session"+session.getId());
			int num=0;//定义一个变量控制发送信息到用户只有一次
			// 1.根据id从表里面查找聊天对象(用户给消息对象赋值)	
			*//**
			 * 获取参数
			 *//*
			String userId = map.get("userId");
			String agentId = map.get("agentId");
			String userName = map.get("userName");
			Integer sessionId = Integer.parseInt(map.get("sessionId").trim());
			//Integer threadId = Integer.parseInt(map.get("threadId").trim());
			String time = DateUtil.datetimeFormat.format(new Date());
			*//**
			 * 查找所有聊天组
			 *//*
			ACDFactory.createSocketUserManager().addWebsocketSession(userId, session);
			
			//List<AgentUserVO> agentUserList = agentUserDao.getAgentUserListByEndStatusAndSessionId(sessionId);
			Integer chatStatusEnd = DataContext.CHAT_STATUS_END;
			Integer chattype = DataContext.CHATTYPE_CHAT;
			List<AgentUserVO> agentUserList = agentUserDao.getAgentUserListByEndStatusAndChatTypeAndUserId(chatStatusEnd,chattype,userId);
			
			//List<ThreadVO> threadList = threadDao.getThreadListByEndStatusAndSessionId(sessionId);
			if(agentUserList.isEmpty()){//为空说明里面就没有建立过连接,需要重新建立连接
				EndChat endChat = new EndChat();
				endChat.setAgentId(agentId);
				endChat.setUserId(userId);
				endChat.setCmd("endchat");
				endChat.setSessionId(sessionId);
				endChat.setEndChatContent("聊天已结束");
				List<WebSocketSession> socketSessionList = ACDFactory.createSocketUserManager().getSocketSessionList(userId);
			    webSocketHandler.sendTipMessage(socketSessionList,new TextMessage(endChat.toJson()));
			    return;
			}
			*//**
			 * 遍历发送消息(此时agentuser不为空,但是thread也许为空了,说明此时聊天已经结束了,只是我们结束聊天时候,也许操作只有thread的结束了而agentuser的没有结束,所以只有两者同步才行)
			 *//*
			for (AgentUserVO agentUserVO : agentUserList) {//循环发送给其他坐席,此时循环出来的threadList也是status为:0或者1
					*//**
					 * 更改agentuser，thread表中状态 为1
					 *//*
				    Integer chatType = agentUserVO.getChatType();
			    	agentUserDao.updateStatusAndChatTypeByUserIdAndAgentId(DataContext.CHAT_STATUS_CHATTING,chatType,agentUserVO.getUserId(), agentUserVO.getAgentId());
				   // threadDao.updateStatusByEndStatusAndAgentUserId(1,agentUserVO.getAgentUserId());
					//1.获取threadid(此时的threadid有可能为多个值数组形式,)
					Integer[] threadIds = threadDao.getThreadIdByEndStatusAndAgentUserId(agentUserVO.getAgentUserId());
					*//**
					 * 异常处理
					 *//*
					if(threadIds.length==0){//此时agentuser没有结束,但是thread全部结束,此时我们更新最新一个thread为1
						threadDao.updateLastStatusByAgentUserId(DataContext.CHAT_STATUS_CHATTING, agentUserVO.getAgentUserId());
						threadId=threadDao.getThreadIdByEndStatusAndAgentUserId(agentUserVO.getAgentUserId())[0];
					}else if(threadIds.length>1){//此时出现上一次聊天还未结束情况
						threadDao.updateStatusByEndStatusAndAgentUserId(chatStatusEnd,agentUserVO.getAgentUserId());
						threadDao.updateLastStatusByAgentUserId(DataContext.CHAT_STATUS_CHATTING, agentUserVO.getAgentUserId());
						threadId=threadDao.getThreadIdByEndStatusAndAgentUserId(agentUserVO.getAgentUserId())[0];
					}else{
						threadId=threadIds[0];
					}
					// 2.组装消息对象保存
					MessageVO messageVO = new MessageVO();
					messageVO.setFromId(userId);
					messageVO.setFromName(userName);
					messageVO.setOwnerId(userId);
					messageVO.setOwnerName(userName);
					messageVO.setShow(userName);
					messageVO.setMessage(map.get("data"));
					messageVO.setMessageFrom(1);// 1:u-->a
					messageVO.setMessageTime(time);
					messageVO.setMessageType(1);//1.文本
					messageVO.setSource(agentUserVO.getSource());
					messageVO.setSessionId(agentUserVO.getSessionId());
					messageVO.setThreadId(threadId);
					messageDao.save(messageVO);
				   
					//3.发送对应坐席
					webSocketHandler.sendMessageToAgent(agentUserVO.getAgentId(),messageVO);
					
					//4.发送给对应用户
					if(num==0){
						webSocketHandler.sendMessageToUser(userId,messageVO);
						num++;
					}
					
					//5.推送消息给坐席提示按钮闪烁
					//WebSocketSession stateSession = ACDFactory.createStateSocketAgentManager().getSocketSession(agentUserVO.getAgentId());
					List<WebSocketSession> stateSession = ACDFactory.createStateSocketAgentManager().getSocketSessionList(agentUserVO.getAgentId());
					webSocketHandler.sendTipMessage(stateSession, new TextMessage(messageVO.toJson()));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void webchatagentmsg(Map<String,String> map, WebSocketSession session) {
		 	 
			  AgentMsgHande(2, map, session);// source:1-->wechat 2-->webcc
	}
	@Override
	public void wechatagentmsg(Map<String,String> map, WebSocketSession session) {
	 	 
		 AgentMsgHande(1, map, session);//推送消息给微信
		
	}
	@Override
	public void weiboagentmsg(Map<String,String> map, WebSocketSession session) {
		 
			 AgentMsgHande(3, map, session);//source:1-->wechat 2-->webcc 3-->weibo
	 
	}
	@Override
	public void agentstatus(Map<String,String> map,WebSocketSession session) {
		// 1. 根据状态对空闲队列进行操作
		
		 * 状态：空闲（free），不空闲(busy) 
		 * 原则：
		 * 1.不空闲，聊天数为0---出空闲队列(说明在处理软电话)
		 * 2.空闲，聊天数为0---空闲队列(都有可能处理)
		 * 3.不空闲，聊天数大于0---空闲队列(说明在处理聊天)
		 
		try {
			// 1.获取坐席状态和聊天数
			String status = (String) map.get("status");
			String agentId = (String) map.get("agentId");
			// Integer count = agentObj.getServiceNum();
			//@Agent agent = (Agent) ACDUtil.agentInfoMap.get(agentId);
			Agent agent = ACDFactory.createAgentManager().getAgent(agentId);
			if(agent==null){
				agent = onlineAgentDao.getByAgentId(agentId);
				ACDFactory.createAgentManager().addAgent(agent);
			}
			Integer count = agentUserDao.getSernumByEndStatusAndAgentId(agentId);
			if(agent!=null){
				// 2.空闲队列操作
				if(DataContext.AGENT_STATUS_OFFLINE.equals(agent.getStatus())){//信息队列中坐席为离线则肯定剔除坐席
					//用户离线条件下,CTI所有动作对其不受影响
					//@ACDUtil.freeAgentQueue.remove(agent);
					ACDFactory.freeAgentQueue.remove(agent);
				}else{
					if("forceagentlogoff".equals(status)){
						MessageTip messageTip = new MessageTip();
						messageTip.setCmd("ctirequestwebccoffline");
						//WebSocketSession agentSession = ACDFactory.createSocketAgentManager().getSocketSession(agentId);
						 List<WebSocketSession> agentSession = ACDFactory.createSocketAgentManager().getSocketSessionList(agentId);
						webSocketHandler.sendTipMessage(agentSession, new TextMessage(messageTip.toJson()));
					};
					if(count==0){//在0情况下,webcc等待有人聊天
						if("ctiCallOut".equals(status)){//通话
					    	//1.离线webcc
							MessageTip messageTip = new MessageTip();
							//messageTip.setCmd("ctirequestwebcclogoff");
							messageTip.setCmd("ctirequestwebccoffline");
							List<WebSocketSession> agentSession = ACDFactory.createSocketAgentManager().getSocketSessionList(agentId);;
							webSocketHandler.sendTipMessage(agentSession, new TextMessage(messageTip.toJson()));
						}else if("eRinging".equals(status)){
							//1.离线webcc
							MessageTip messageTip = new MessageTip();
							messageTip.setCmd("ctirequestwebccoffline");
							List<WebSocketSession> agentSession = ACDFactory.createSocketAgentManager().getSocketSessionList(agentId);
							webSocketHandler.sendTipMessage(agentSession, new TextMessage(messageTip.toJson()));
						}
					}else if(count>0){//在1情况下,webcc正在聊天
						if("ctiInit".equals(status)){//签入
							MessageTip messageTip = new MessageTip();
							messageTip.setCmd("webccrequestctichating");
							List<WebSocketSession> stateSession = ACDFactory.createStateSocketAgentManager().getSocketSessionList(agentId);
							webSocketHandler.sendTipMessage(stateSession, new TextMessage(messageTip.toJson()));
						}else if("ctiReady".equals(status)){//就绪
							MessageTip messageTip = new MessageTip();
							messageTip.setCmd("webccrequestctichating");
							List<WebSocketSession> stateSession = ACDFactory.createStateSocketAgentManager().getSocketSessionList(agentId);
							webSocketHandler.sendTipMessage(stateSession, new TextMessage(messageTip.toJson()));
						}else if("ctiOut".equals(status)){//签出
							return;
						}else if("ctiNotReady".equals(status)){//小修
							return;
						}else{//通话
							//提示CTI webcc在聊天
							MessageTip messageTip = new MessageTip();
							messageTip.setCmd("webccrequestctichating");
							List<WebSocketSession> stateSession = ACDFactory.createStateSocketAgentManager().getSocketSessionList(agentId);
							webSocketHandler.sendTipMessage(stateSession, new TextMessage(messageTip.toJson()));
							return;
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("agentstatus error");
			e.printStackTrace();
		}
	}
	public void chat(){
		*//**分配坐席原则,是先分配坐席,如果没有空闲坐席再去放入空闲队列,所以是两个
		 * 定时监测(定时器定时执行) 1.从用户队列中监测出是否有等待用户 2.有用户从空闲坐席队列中取队列 3.建立聊天
		 * **//*
		instanceLock.lock();
		try {
			// 1.从用户队列中监测出是否有等待用户
			if (!ACDFactory.webccUserQueue.isEmpty()) {
				User user = null;
				try {
					user = (User) ACDFactory.getWebccUserQueue();
				} catch (Exception e) {
					e.printStackTrace();
				}
				UserLogin userLogin = new UserLogin();
				userLogin.setUserId(user.getUserId());
				userLogin.setCmd("userlogin");
				if (timeCalculate(user)){
					// 2.根据用户的group分配坐席
					String source = user.getSource();
					// 坐席分配(分配的坐席是从空闲队列中获取)
					Agent assignAgent = (Agent) ACDFactory.allotAgent(source);
					if (assignAgent != null) {
						if(serveNumCalculate(assignAgent)){
						List<WebSocketSession> agentSession = ACDFactory.createSocketAgentManager().getSocketSessionList(assignAgent.getAgentId());
						String time = DateUtil.datetimeFormat.format(new Date());
						*//**
						 * 先去session表中查找,查找到了就复用,不然新建立
						 *//*
						AgentUserVO agentUser = agentUserDao.getAgentUserByUserIdAndAgentId(user.getUserId(),assignAgent.getAgentId());
						ThreadVO threadVo = new ThreadVO();
						if(agentUser==null){
							//1.保存到agentuser表
							AgentUserVO agentUserVO = new AgentUserVO();
							agentUserVO.setAgentId(assignAgent.getAgentId());
							agentUserVO.setAgentName(assignAgent.getAgentName());
							agentUserVO.setUserId(user.getUserId());
							agentUserVO.setUserName(user.getUserName());
							//agentUserVO.setEntId(entId);
							agentUserVO.setSource(2);
							agentUserVO.setStatus(0);
							agentUserVO.setChatType(DataContext.CHATTYPE_SINGLE);
							agentUserDao.saveAutoSessionId(agentUserVO);
							
							//2.保存到thread表(保存到thread表之前需要判断有没有上次聊天的会话结束)
							
							//检查聊天是否结束
							checkChatEnd(assignAgent.getAgentId(),user.getUserId());
							
							threadVo.setAgentId(assignAgent.getAgentId());
							threadVo.setAgentName(assignAgent.getAgentName());
							threadVo.setAgentUserId(agentUserVO.getAgentUserId());
							threadVo.setSource(2);
							threadVo.setStartTime(time);
							threadVo.setUserId(user.getUserId());
							threadVo.setUserName(user.getUserName());
							threadVo.setStatus(0);// 0.代表等待聊天
							threadVo.setStartCause(0);//0-代表用户发起
							threadVo.setResponseTime("");
							threadVo.setStartCause(0);
							threadVo.setEndTime("");
							threadVo.setSessionId(agentUserVO.getAgentUserId());
							threadDao.save(threadVo);
						}else{
							//更改agentuser表中状态为0
							Integer chatType =DataContext.CHATTYPE_SINGLE;
							agentUserDao.updateStatusAndChatTypeByUserIdAndAgentId(DataContext.CHAT_STATUS_WAITING, chatType, agentUser.getUserId(),agentUser.getAgentId());
							//检查上一次会话是否结束，没有结束我们设置为异常结束
							checkChatEnd(assignAgent.getAgentId(),user.getUserId());
							*//**
							 * 新建会话
							 *//*
							threadVo.setAgentId(assignAgent.getAgentId());
							threadVo.setAgentName(assignAgent.getAgentName());
							threadVo.setSource(2);
							threadVo.setStartTime(time);
							threadVo.setUserId(agentUser.getUserId());
							threadVo.setUserName(agentUser.getUserName());
							threadVo.setStatus(0);// 0.代表等待聊天
							threadVo.setStartCause(0);//0-代表用户发起
							threadVo.setResponseTime("");
							threadVo.setStartCause(0);
							threadVo.setEndTime("");
							threadVo.setAgentUserId(agentUser.getAgentUserId());
							threadVo.setSessionId(agentUser.getSessionId());
							threadDao.save(threadVo);
						}
						*//**
						 * 发送用户登录欢迎语给前端
						 *//*
						userLogin.setThreadId(threadVo.getThreadId());
						userLogin.setAgentId(assignAgent.getAgentId());
						userLogin.setSessionId(threadVo.getSessionId());
						userLogin.setType("1");//1.正常
						userLogin.setCmd("userlogin");
						List<WebSocketSession> userSession = ACDFactory.createSocketUserManager().getSocketSessionList(user.getUserId());
						webSocketHandler.sendTipMessage(userSession,
								new TextMessage(userLogin.toJson()));
					   *//**
					    * 通知坐席,有用户建立连接
					    *//*
					   MessageTip messageTip = new MessageTip();
					   messageTip.setCmd("userstartchat");
					   messageTip.setFromId(threadVo.getUserId());
					   messageTip.setFromName(threadVo.getUserName());
					   messageTip.setOwnerId(threadVo.getUserId());
					   messageTip.setOwnerName(threadVo.getUserName());
					   messageTip.setShow(threadVo.getUserName());
					   messageTip.setThreadId(threadVo.getThreadId());
					   messageTip.setSessionId(threadVo.getSessionId());
					   messageTip.setSource(threadVo.getSource());
					   webSocketHandler.sendTipMessage(agentSession, new TextMessage(messageTip.toJson()));
						
					   *//**
					    * 用户聊天日志
					    *//*
					    String userId = user.getUserId();
					    String userName = user.getUserName();
					    Integer optCode = DataContext.USER_OPTCODE_CHAT;
					    String queueId="";
					    String remark="";
					    Integer optResult = DataContext.OPTRESULT_SUCCESS;
					    userOptLog(userId, userName, optCode, queueId, time, remark, optResult);
					    
					    *//**
					     * 坐席聊天日志
					     *//*
					   agentOptLog(assignAgent.getAgentId(), assignAgent.getAgentName(),-1, 30, 1, "", "", threadVo.getThreadId(), "", "", user.getUserId(),user.getUserName());
					   
					   *//**
					    * 更新坐席
					    *//*
					   ACDFactory.createAgentManager().addAgent(assignAgent);
					   
					   *//**
					    * 将队列放置最尾部
					    *//*
						ACDFactory.buildFreeAgentQueue(assignAgent);

						*//**
						 * 通知CTI离线
						 *//*
						MessageTip ctitip = new MessageTip();
						ctitip.setCmd("webccchat");
						//@WebSocketSession stateSession = WebSocketSessionUtil.agentStateSessionMap.get(assignAgent.getAgentId());
						List<WebSocketSession> stateSession = ACDFactory.createStateSocketAgentManager().getSocketSessionList(assignAgent.getAgentId());
						webSocketHandler.sendTipMessage(stateSession,new TextMessage(ctitip.toJson()));
						}else{
							// 没有坐席的时候，让其跳转到留言页面
							// window.location.href
							userLogin.setType("2");//2.没有坐席
							userLogin.setReminderContent("没有客服在线");
						//@WebSocketSession session = WebSocketSessionUtil.userSocketSessionMap.get(user.getUserId());
							List<WebSocketSession> session = ACDFactory.createSocketUserManager().getSocketSessionList(user.getUserId());
							webSocketHandler.sendTipMessage(session,
									new TextMessage(userLogin.toJson()));
						}
					} else {
						*//**
						 * 没有坐席的时候，让其跳转到留言页面
						 *//*
						userLogin.setType("2");//2.没有坐席
						userLogin.setReminderContent("没有客服在线");
						List<WebSocketSession> session = ACDFactory.createSocketUserManager().getSocketSessionList(user.getUserId());
						webSocketHandler.sendTipMessage(session,new TextMessage(userLogin.toJson()));
					}
				} else {
					logger.info("[***webcc***] chat waiting overtime");
					ParamVO userQueueTimeoutRemind = paramDao.getUserQueueTimeoutRemindByAdmin(DataContext.PARAM_USERQUEUETIMEOUTREMIND,DataContext.ROLE_ADMIN);
					userLogin.setType("3");//2.没有坐席
					if(userQueueTimeoutRemind==null){
						userLogin.setReminderContent("等待超时");
					}else{
						userLogin.setReminderContent(userQueueTimeoutRemind.getParamValue());
					}
					List<WebSocketSession> userSession = ACDFactory.createSocketUserManager().getSocketSessionList(user.getUserId());
					webSocketHandler.sendTipMessage(userSession,
							new TextMessage(userLogin.toJson()));
					  ****************
					  *等待超时记录用户日志
					 ******************
					String userId = user.getUserId();
					String userName = user.getUserName();
					Integer optCode = DataContext.USER_OPTCODE_WAITTIMEOUT;
					String queueId = "";
					String time = DateUtil.datetimeFormat.format(new Date());
					String remark = "";
					Integer optResult = DataContext.OPTRESULT_SUCCESS;
					userOptLog(userId, userName, optCode, queueId, time, remark, optResult);
					
				}
			}else{
				logger.info("没有任务");
			}
		} catch (Exception e) {
			logger.error("chat error");
			e.printStackTrace();
		}
		instanceLock.unlock();
	}
	*//**
	 * 根据消息来源(webcc,wechat,weibo)处理agentmsg
	 * @param source
	 * @param map
	 * @param session
	 *//*
	private void AgentMsgHande(Integer source, Map<String,String> map, WebSocketSession session) {
		try {
			*//**
			 * 获取参数
			 *//*
			ThreadVO threadVO = new ThreadVO();
			String agentId = map.get("agentId");//当前坐席,对于其他坐席有可能充当用户级别
			String agentName = map.get("agentName");
			String userId = map.get("userId");
			String userName = map.get("userName");//userName不一定准
			Integer sessionId = Integer.parseInt(map.get("sessionId"));
			String msg = map.get("msg");
			
			//^^^^^^^^^^^^^^注意:此时的userId不一定是用户的userId,也许是群聊时候坐席的agentId^^^^^^^^^^^^^^^^^^^注意
			*//**
			 * 发送消息给用户
			 *//*
			ACDFactory.createSocketAgentManager().addWebsocketSession(agentId, session);
			AgentUserVO agentUserVO = agentUserDao.getAgentUserBySessionIdAndAgentId(sessionId,agentId);
			if(agentUserVO!=null){
				if(!userId.equals(agentUserVO.getUserId())){
					userId=agentUserVO.getUserId();
				}
				Integer agentUserId = agentUserVO.getAgentUserId();
				
				*//**
				 * 单聊；组装消息，发送给对应坐席和客户
				 *//*
				//根据sessionId得到会话id
				//List<AgentUserVO> agentUserList = agentUserDao.getAgentUserListByEndStatusAndSessionId(sessionId);
				Integer chatStatusEnd = DataContext.CHAT_STATUS_END;
				Integer chattypeChat = DataContext.CHATTYPE_CHAT;
				List<AgentUserVO> agentUserList = agentUserDao.getAgentUserListByEndStatusAndChatTypeAndUserId(chatStatusEnd,chattypeChat,userId);
				//List<ThreadVO> threadList = threadDao.getThreadListByEndStatusAndSessionId(sessionId);
	            if(agentUserList.isEmpty()){
					EndChat endChat = new EndChat();
					endChat.setAgentId(agentId);
					endChat.setUserId(userId);
					endChat.setCmd("endchat");
					endChat.setSessionId(sessionId);
					endChat.setEndChatContent("聊天已结束");
					List<WebSocketSession> socketSessionList = ACDFactory.createSocketAgentManager().getSocketSessionList(agentId);
				    webSocketHandler.sendTipMessage(socketSessionList,new TextMessage(endChat.toJson()));
				    return;
				}else{
					*//**
					 * 异常处理
					 *//*
					Integer[] threadIds = threadDao.getThreadIdByEndStatusAndAgentUserId(agentUserId);
					if(threadIds.length==0){//此时agentuser没有结束,但是thread全部结束,此时我们更新最新一个thread为1
						threadDao.updateLastStatusByUserIdAndAgentId(1, userId, agentId);
						threadVO =  threadDao.getThreadByEndStatusAndUserIdAndAgentId(userId, agentId);
					}else if(threadIds.length>1){//此时出现上一次聊天还未结束情况
						threadDao.updateStatusByEndStatusAndAgentUserId(chatStatusEnd,agentUserVO.getAgentUserId());
						threadDao.updateLastStatusByAgentUserId(DataContext.CHAT_STATUS_CHATTING, agentUserVO.getAgentUserId());
						threadVO =  threadDao.getThreadByEndStatusAndUserIdAndAgentId(userId, agentId);
					}else{
						threadVO = threadDao.getThreadByEndStatusAndUserIdAndAgentId(userId, agentId);
					}
					
					*//**
					 * 消息组装
					 *//*
					MessageVO messageVO = new MessageVO();
					messageVO.setFromId(agentId);
					messageVO.setFromName(agentName);
					messageVO.setOwnerId(userId);
					messageVO.setOwnerName(userName);
					messageVO.setShow("坐席"+agentId);
					messageVO.setMessage(msg);
					messageVO.setMessageFrom(2);// 2:a-->u
					String time = DateUtil.datetimeFormat.format(new Date());
					messageVO.setMessageTime(time);
					messageVO.setMessageType(1);
					messageVO.setSource(source);
					messageVO.setSessionId(sessionId);
					messageVO.setThreadId(threadVO.getThreadId());
					System.out.println(".....mesageVO:......" + messageVO);
					//同一个session用户id是相同的
					messageDao.save(messageVO);
					if (source == 2){
						// 4.发送给webcc客户
						webSocketHandler.sendMessageToUser(userId,messageVO);
					}else{
						
					}
					 
					*//**
					 * 循环发送给多坐席
					 *//*
					for (AgentUserVO agentUser : agentUserList) {//循环发送消息
					// 组装消息(此时消息为Message) 发送给本坐席
					ThreadVO thread = threadDao.getThreadByEndStatusAndUserIdAndAgentId(agentUser.getUserId(), agentUser.getAgentId());
					MessageVO toAgentMessage = new MessageVO();
					toAgentMessage.setMessage(msg);
					if (agentId.equals(agentUser.getAgentId())) {// 如果当前agentid等于thread中agentid,则消息来源为2（a-->u）
						toAgentMessage.setFromId(agentUser.getAgentId());// 发给本坐席
						toAgentMessage.setFromName(agentName);
						toAgentMessage.setOwnerId(userId);
						toAgentMessage.setOwnerName(userName);
						toAgentMessage.setShow("坐席"+agentUser.getAgentId());
						toAgentMessage.setMessageFrom(2);// 2:a-->u
					} else {//发给其他坐席
						toAgentMessage.setFromId(agentId);// 当发送给其他坐席时候,其fromId就是agentId
						toAgentMessage.setFromName(agentName);//此时设置userName为用户的userName
						toAgentMessage.setOwnerId(userId);
						toAgentMessage.setOwnerName(userName);
						toAgentMessage.setShow("坐席"+agentId);
						toAgentMessage.setMessageFrom(1);// 1:u-->a
					}
					toAgentMessage.setMessageTime(time);
					toAgentMessage.setMessageType(1);
					toAgentMessage.setSource(source);
					toAgentMessage.setSessionId(agentUser.getSessionId());
					toAgentMessage.setThreadId(thread.getThreadId());
					toAgentMessage.setMessageId(messageVO.getMessageId());
					// 3.消息保存完毕
					if (!agentId.equals(thread.getAgentId())) {// 如果是当前坐席无需再保存信息,因为客户发送就已经保存信息
						messageDao.save(toAgentMessage);
					}
					
					*//**
					 * 推送消息给坐席
					 *//*
					webSocketHandler.sendMessageToAgent(thread.getAgentId(),toAgentMessage);
					*//**
					 * 推送消息给坐席提示按钮闪烁
					 *//*
					List<WebSocketSession> stateSession = ACDFactory.createStateSocketAgentManager().getSocketSessionList(thread.getAgentId());
					webSocketHandler.sendTipMessage(stateSession, new TextMessage(
							toAgentMessage.toJson()));
					System.out.println("chat is over");
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isChatEnd(String userId,String agentId,Integer sessionId,List<WebSocketSession> session) {
		try {
		//@Agent agent = (Agent)ACDUtil.agentInfoMap.get(agentId);
		Agent agent = ACDFactory.createAgentManager().getAgent(agentId);
		if(agent==null){
			agent = onlineAgentDao.getByAgentId(agentId);
			ACDFactory.createAgentManager().addAgent(agent);
		}
		if(agent != null){  //如果坐席存在我们才执行下面
			//Integer status = threadDao.getStatusByAgentIdAndSessionId(agentId, sessionId);
			Integer status = agentUserDao.getAgentUserByUserIdAndAgentId(userId, agentId).getStatus();
			if(status==2 || "pause".equals(agent.getStatus())){ 
					EndChat endChat = new EndChat();
					endChat.setAgentId(agentId);
					endChat.setUserId(userId);
					endChat.setCmd("endchat");
					endChat.setSessionId(sessionId);
					endChat.setEndChatContent("聊天已结束");
				webSocketHandler.sendTipMessage(session,new TextMessage(endChat.toJson()));
				return true;
			}else{
				return false;
			}
		}else{//如果坐席等于空说明已经移除,我们应该结束此会话
			threadDao.updateStatusBySessionIdAndAgentId(2, sessionId, agentId);
			EndChat endChat = new EndChat();
			endChat.setAgentId(agentId);
			endChat.setUserId(userId);
			endChat.setCmd("endchat");
			endChat.setSessionId(sessionId);
			endChat.setEndChatContent("聊天已结束");
		webSocketHandler.sendTipMessage(session,new TextMessage(endChat.toJson()));
			return true;
		}
		} catch (Exception e) {
			logger.error("---------isChatEnd error--------------");
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void changeStatusByAgentIdAfterClosed(String agentId, Integer status) {
		List<Integer> threadList = threadDao.getThreadIdListByAgentId(agentId);
		for (Integer threadId : threadList) {
			threadDao.updateStatusByThreadId(threadId, status);
		}
	}
	
	@Override
	public void changeStatusByUserIdAfterClosed(String userId, Integer status) {
		
		List<Integer> threadIdList = threadDao.getThreadIdListByUserId(userId);
		for (Integer threadId: threadIdList) {
			threadDao.updateStatusByThreadId(threadId, status);
		}
	}
	 *//**
	  * 计算等待是否超时
	  * @param user
	  * @return true,false
	  *//*
	private boolean timeCalculate(User user){
		try {
			String loginTime = user.getLoginTime();
			long start_time = sdf.parse(loginTime).getTime();
			long end_time = (new Date()).getTime();
			long interval_time = (end_time - start_time) / 1000;
			long timeOut = 0;
			//系统等待超时时间比较
			ParamVO paramVO = paramDao.getUserWaitingTimeByAdmin(DataContext.PARAM_USERWAITINGTIME,DataContext.ROLE_ADMIN);
			if(paramVO!=null){
				if(!StringUtils.isBlank(paramVO.getParamValue())){
					timeOut = Long.parseLong(paramVO.getParamValue());
				}
			}
			if (interval_time <=timeOut){//等待不延时
				return true;
			}else{
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	*//*************************
	 * 坐席进入队列
	 * *******************//*
	public void enterQueue(Agent agent){
		String status = agent.getStatus();
		//坐席进入队列条件是服务数量和坐席没有离线
		if(serveNumCalculate(agent) && !"pause".equals(status)){
			ACDFactory.buildFreeAgentQueue(agent);
		}
	}
	*//***
	 * 计算坐席服务数量
	 * @param agent
	 * @return
	 *//*
	private boolean serveNumCalculate(Agent agent){
		 try {
		    ParamVO maxUserVO =  new ParamVO();
		    maxUserVO= paramDao.getMaxUserByAgentId(DataContext.PARAM_MAXUSER, DataContext.ROLE_AGENT, agent.getAgentId());
			if(maxUserVO==null){
				maxUserVO = paramDao.getMaxUserByAdmin(DataContext.PARAM_MAXUSER, DataContext.ROLE_ADMIN);
			}
		    Integer maxAgentServerNum = 1;
			//获取本坐席服务数量（从agentUser表中）
		    Integer agentServerNum = agentUserDao.getSernumByEndStatusAndAgentId(agent.getAgentId());
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
	@Override
	public void focusagentlogin(Map<String,String> map, WebSocketSession session) {
        //主要实现给坐席绑定状态报告的WebSocketSession	
		String agentId = (String)map.get("agentId");
		//@WebSocketSessionUtil.agentStateSessionMap.put(agentId, session);
		StateSocketAgent stateSocketAgent = new StateSocketAgent();
		stateSocketAgent.setAgentId(agentId);
		stateSocketAgent.setSession(session);
		ACDFactory.createStateSocketAgentManager().addStateSocketAgent(stateSocketAgent);
		ACDFactory.createStateSocketAgentManager().addWebsocketSession(agentId, session);
	}

	@Override
	public void webchatagentstart(Map<String, String> map,
			WebSocketSession session) {
		try {
		Integer lastmsgId = Integer.parseInt(map.get("lastmsgId"));
		String agentId = map.get("agentId");
		getUnradMessage(agentId,lastmsgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	@Override
	public void webchatclientend(Map<String, String> map,
			WebSocketSession session) {
		System.out.println("####################webchatclientend执行##########################");
		try {
			*//**
			 * 获取参数
			 *//*
			String userId = map.get("userId");
			Integer sessionId = Integer.parseInt(map.get("sessionId"));
			String endTime = DateUtil.datetimeFormat.format(new Date());
			// @WebSocketSession userSocket =
			// WebSocketSessionUtil.userSocketSessionMap.get(userId);
			List<WebSocketSession> userSocket = ACDFactory.createSocketUserManager().getSocketSessionList(userId);
			EndChat endChat = new EndChat();
			endChat.setCmd("webchatclientend");
			endChat.setUserId(userId);
			endChat.setSessionId(sessionId);

			*//**
			 * 通知坐席会话结束
			 *//*
			Integer chatStatusEnd = DataContext.CHAT_STATUS_END;
			Integer chattypeChat = DataContext.CHATTYPE_CHAT;
			List<AgentUserVO> agentUserList = agentUserDao.getAgentUserListByEndStatusAndChatTypeAndUserId(chatStatusEnd,chattypeChat,userId);
			for (AgentUserVO agentUserVO : agentUserList) {
				List<WebSocketSession> agentSession = ACDFactory.createSocketAgentManager().getSocketSessionList(agentUserVO.getAgentId());
				*//**
				 * 结束会话
				 *//*
				//结束agentuser
				//agentUserDao.updateStatusByUserIdAndAgentId(2, userId, agentUserVO.getAgentId());
				Integer chatType=DataContext.CHATTYPE_CHAT;
				agentUserDao.updateStatusAndChatTypeByUserIdAndAgentId(DataContext.CHAT_STATUS_END, chatType, userId,agentUserVO.getAgentId());
				//结束thread表中
				ThreadVO thread = threadDao.getThreadByEndStatusAndAgentUserId(agentUserVO.getAgentUserId());
				String responseTime=messageDao.getResponseTimeByThreadIdAndAgentId(thread.getThreadId(),agentUserVO.getAgentId());
				threadDao.agentEndByEndStatusAndUserIdAndAgentId(2, responseTime, 2, endTime, userId, agentUserVO.getAgentId());
				*//**
				 * 给出坐席提示
				 *//*
				webSocketHandler.sendTipMessage(agentSession, new TextMessage(
						endChat.toJson()));

				*//**
				 * 给出用户提示
				 *//*
				webSocketHandler.sendTipMessage(userSocket,new TextMessage(endChat.toJson()));
				*//**
				 * 坐席结束会话,判断看是否进入队列,重新进入队列 
				 *//*
				if (!ACDFactory.containsFreeAgent(agentUserVO.getAgentId())) {
					// Agent agent =
					// ACDUtil.agentInfoMap.get(threadVO.getAgentId());
					Agent agent = ACDFactory.createAgentManager().getAgent(
							agentUserVO.getAgentId());
					if(agent==null){
						agent=onlineAgentDao.getByAgentId(agentUserVO.getAgentId());
						ACDFactory.createAgentManager().addAgent(agent);
					}
					if(agent!=null){
						ACDFactory.buildFreeAgentQueue(agent);
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
  *//**
   * 坐席日志记录
   * 
   *//*
  @Override
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
  @Override
  public void userOptLog(String userId,String userName,Integer optCode,String queueId,String startTime,String remark,Integer optResult){
	  *//**
		 * 户进队列记录日志
		 *//*
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
  @Override
  public void getUnradMessage(String agentId,Integer lastmsgId){
	  try {
			//此时判断用户是否是在线状态
			Agent agent = ACDFactory.createAgentManager().getAgent(agentId);
			if(agent==null){
				agent=onlineAgentDao.getByAgentId(agentId);
				ACDFactory.createAgentManager().addAgent(agent);
			}
			if(agent!=null && !DataContext.AGENT_STATUS_OFFLINE.equals(agent.getStatus())){
				if (lastmsgId == 0) {// 此时用户刷新,恢复到以前状态,通过thread表获取消息
					*//**
					 * 通过agentId和status !=2 获取所有未结束会话
					 *//*
					List<AgentUserVO> agentUserList = agentUserDao.getAgentUserListByEndStatusAndAgentId(agentId);
					// List<SessionVO> sessionList =
					// sessionDao.getSessionListByEndStatusAndAgentId(agentId);
					for (AgentUserVO agentUserVO : agentUserList) {
						*//**
						 * 根据sessionId和lastmsgid查找message
						 *//*
						 ThreadVO thread = threadDao.getThreadByEndStatusAndAgentUserId(agentUserVO.getAgentUserId());
						if(thread!=null){
							Integer threadId = thread.getThreadId();
							List<MessageVO> messageList = messageDao.getUnreadMessage(
									threadId, agentUserVO.getSessionId(), lastmsgId);

							System.out.println("***********[  messageList    ]*******"
									+ messageList.toString() + "*********");
							for (MessageVO messageVO : messageList) {
								webSocketHandler.sendMessageToAgent(agentId, messageVO);
							}
						}
					}
					// 清空消息记录
					ACDFactory.createAgentManager().getMessage(agentId);
				} else {// 此时用户坐席断开连接,通过message表获取消息
						// 获取未读信息
					  List<MessageVO> messageList = ACDFactory.createAgentManager().getMessage(agentId);
					  if(messageList!=null){
						     for (MessageVO messageVO : messageList) {
							webSocketHandler.sendMessageToAgent(agentId, messageVO);
					     }
					  }
					
			    	}
		    	}
			} catch (Exception e) {
				e.printStackTrace();
			}
  }
  
  private void checkChatEnd(String agentId,String userId){
	  try {
		  ThreadVO thread = threadDao.getThreadByEndStatusAndUserIdAndAgentId(userId, agentId);
			if(thread!=null){
				//设置意外结束   7
				String endTime = DateUtil.datetimeFormat.format(new Date());
				String responseTime=messageDao.getResponseTimeByThreadIdAndAgentId(thread.getThreadId(),thread.getAgentId());
				threadDao.agentEndByEndStatusAndUserIdAndAgentId(2, responseTime,DataContext.CHAT_END_CAUSE_EXCEPTION, endTime,userId,agentId);
		   }
	 } catch (Exception e) {
		 
	 }
  }
*/}
