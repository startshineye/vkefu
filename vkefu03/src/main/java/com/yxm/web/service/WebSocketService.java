package com.yxm.web.service;
public interface WebSocketService {/*
	*//**
	 * 坐席登录
	 * @param map
	 * @param message
	 *//*
	  void webccstart(Map<String,String> map,WebSocketSession session);
	  void webchatclientmsg(Map<String,String> map,WebSocketSession session);
      *//**
       * 坐席发送web消息
       * @param map
       * @param userId
       * @param userName
       * @param session
       *//*
	  void webchatagentmsg(Map<String,String> map,WebSocketSession session);
	  void wechatagentmsg(Map<String,String> map,WebSocketSession session);
	  void weiboagentmsg(Map<String,String> map,WebSocketSession session);
	  void agentstatus(Map<String,String> map,WebSocketSession session);
	  void chat();
	  *//**
	   * 坐席关闭结束聊天后改变状态
	   * @param agentId
	   * @param status
	   *//*
	  void changeStatusByAgentIdAfterClosed(String agentId,Integer status);
	  *//**
	   * 用户关闭聊天后改变状态
	   * @param userId
	   * @param status
	   *//*
	  void changeStatusByUserIdAfterClosed(String userId,Integer status);
	  *//**
	   * 根据状态判断判断是否已经结束聊天
	   * @param map
	   * @param session
	   * @return
	   *//*
      boolean isChatEnd(String userId,String agentId,Integer sessionId,List<WebSocketSession> session);
	  *//****
	   * 坐席开始报告状态
	   * @param map
	   * @param session
	   *//*
	 void focusagentlogin(Map<String,String> map,WebSocketSession session);
         *//***
       * 获取未读消息
       * @param map
       * @param session
       *//*
	  void webchatagentstart(Map<String, String> map,
			WebSocketSession session);
       *//**
        * 获取用户的未读信息
        * @param map
        * @param session
        *//*
	  void webchatclientlastmsgs(Map<String, String> map,
			WebSocketSession session);
     *//***
      * 坐席建立连接
      * @param map
      * @param session
      *//*
     public void agentconnection(Map<String, String> map, WebSocketSession session);
    *//**
     * 会话延时结束聊天
     * @param map
     * @param session
     *//*
	void webchatclientend(Map<String, String> map,WebSocketSession session);
    *//**
     * 坐席操作
     * @param agentId
     * @param agentName
     * @param entId
     * @param optCode
     * @param optResult
     * @param queueId
     * @param remark
     * @param threadId
     * @param toAgentId
     * @param toUserId
     * @param userId
     * @param userName
     *//*
	void agentOptLog(String agentId, String agentName,int entId, int optCode,
			int optResult, String queueId, String remark, int threadId,
			String toAgentId, String toUserId, String userId, String userName);
	void getUnradMessage(String agentId, Integer lastmsgId);
	*//**
	 * 用户日志
	 * @param userId
	 * @param userName
	 * @param optCode
	 * @param queueId
	 * @param startTime
	 * @param remark
	 * @param optResult
	 *//*
	void userOptLog(String userId, String userName, Integer optCode,
			String queueId, String startTime, String remark, Integer optResult);
*/}
















