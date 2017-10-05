package com.founder.focuss.webcc.entity.api;

import com.google.gson.Gson;

/**
 * 结束聊天
 * @author yxm
 * @2016-12-28
 */
public class EndChat extends BaseAPI{
	private String agentId;
	private String userId;
	private Integer sessionId;
	private String endChatContent;
	private static Gson gson = new Gson();
	
	public String getEndChatContent() {
		return endChatContent;
	}

	public void setEndChatContent(String endChatContent) {
		this.endChatContent = endChatContent;
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
	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}
	/**
	 * 将本对象转换成json
	 * 
	 * @return
	 */
	public String toJson() {
		return gson.toJson(this);
	}
}
