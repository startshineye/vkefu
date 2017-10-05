package com.yxm.web.entity.api;
import com.google.gson.Gson;
/**
 * 用户登录判断实体类
 * @author yxm
 * @date 2016-11-15
 */

public class UserLogin  extends BaseAPI{
	private String userId;
	private String agentId;
	private Integer sessionId;
	private Integer threadId;
	private String reminderContent;//一般美容
	private String type;//聊天登录提示类型 【1:正常,外加排队提示语  2.没有坐席   3.超时】
	private static Gson gson = new Gson();// 为了让
	public Integer getSessionId() {
		return sessionId;
	}
	public void setSessionId(Integer sessionId){
		this.sessionId = sessionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReminderContent() {
		return reminderContent;
	}

	public void setReminderContent(String reminderContent) {
		this.reminderContent = reminderContent;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getThreadId() {
		return threadId;
	}
	public void setThreadId(Integer threadId) {
		this.threadId = threadId;
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
