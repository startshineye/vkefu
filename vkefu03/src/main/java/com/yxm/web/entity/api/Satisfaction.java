package com.yxm.web.entity.api;
import java.util.List;

import com.google.gson.Gson;

/**
 * 满意度评价接口类
 * 
 * @author yxm
 * @date 2016-11-30
 */
public class Satisfaction extends BaseAPI{
	private String userId;
	private String userName;
	private String agentId;
	private List message;
	private Integer sessionId;
	private Gson gson = new Gson();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public String toJson() {
		return gson.toJson(this);
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public List getMessage() {
		return message;
	}
    
	public void setMessage(List message) {
		this.message = message;
	}
}
