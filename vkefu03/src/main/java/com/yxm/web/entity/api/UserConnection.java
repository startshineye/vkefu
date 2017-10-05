package com.founder.focuss.webcc.entity.api;

import com.google.gson.Gson;
/**
 * 用户登录判断实体类
 * @author yxm
 * @date 2016-11-15
 */

public class UserConnection extends BaseAPI{
	private String userId;
	private String userName;
	private String agentId;
	private String agentName;
	private Integer sessionId;
    
	private static Gson gson = new Gson();// 为了让
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 将本对象转换成json
	 * 
	 * @return
	 */
	public String toJson() {
		return gson.toJson(this);
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
}
