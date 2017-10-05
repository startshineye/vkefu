package com.yxm.web.domain;
/**
 * 聊天实体类
 * @author yxm
 * @date 2016-10-31
 */
public class SatisfactionVO {
	private Integer satisfactionId;//主键
	private Integer threadId;//聊天id
	private Integer sessionId;//会话id
	private String userId;//用户id
	private String agentId;//坐席id
	private String startTime;//创建时间
    private String satisfaction;//滿意度值
	public Integer getSatisfactionId() {
		return satisfactionId;
	}
	public void setSatisfactionId(Integer satisfactionId) {
		this.satisfactionId = satisfactionId;
	}
	public Integer getThreadId() {
		return threadId;
	}
	public void setThreadId(Integer threadId) {
		this.threadId = threadId;
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
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
	public String getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}
}
