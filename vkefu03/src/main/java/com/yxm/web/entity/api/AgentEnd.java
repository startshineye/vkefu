package com.founder.focuss.webcc.entity.api;

import com.google.gson.Gson;

/**
 * 聊天结束对应的接口类
 * 
 * @author yxm
 * @date 2016-11-30
 */
public class AgentEnd {
	private String cmd;
	private String desc;
	private String agentId;
	private String userId;
	private String threadId;
	private Integer sessionId;
	private String endContent;
	
	
	public String getEndContent() {
		return endContent;
	}

	public void setEndContent(String endContent) {
		this.endContent = endContent;
	}

	

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}



	private Gson gson = new Gson();
	
	/**
	 * 将对应转化成相应的json字符串 
	 * @return
	 */
	public String toJson(){
		return gson.toJson(this);
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}
}
