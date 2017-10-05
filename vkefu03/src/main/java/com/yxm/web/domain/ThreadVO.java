package com.yxm.web.domain;
/**
 * 聊天实体类
 * 
 * @author yxm
 * @date 2016-10-31
 */
public class ThreadVO {
	private Integer threadId;// 主键
	private Integer agentUserId;// 用户坐席唯一性标识
	private Integer sessionId;// 会话id
	private Integer source;// 消息来源
	private String userId;// 用户id
	private String userName;// 用户昵称
	private String agentId;// 坐席id
	private String agentName;// 坐席昵称
	private Integer entId;// 企业id
	private String startTime;// 开始聊天时间
	private String responseTime;// 坐席回应时间
	private String endTime;// 坐席状态结束时间
	private Integer status;// 坐席状态
	private Integer startCause;// 聊天缘由
	private Integer endCause;// 聊天结束缘由

	public Integer getAgentUserId() {
		return agentUserId;
	}

	public void setAgentUserId(Integer agentUserId) {
		this.agentUserId = agentUserId;
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

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Integer getEntId() {
		return entId;
	}

	public void setEntId(Integer entId) {
		this.entId = entId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStartCause() {
		return startCause;
	}

	public void setStartCause(Integer startCause) {
		this.startCause = startCause;
	}

	public Integer getEndCause() {
		return endCause;
	}

	public void setEndCause(Integer endCause) {
		this.endCause = endCause;
	}

	@Override
	public String toString() {
		return "ThreadVO [threadId=" + threadId + ", sessionId=" + sessionId
				+ ", source=" + source + ", userId=" + userId + ", userName="
				+ userName + ", agentId=" + agentId + ", agentName="
				+ agentName + ", entId=" + entId + ", startTime=" + startTime
				+ ", responseTime=" + responseTime + ", endTime=" + endTime
				+ ", status=" + status + ", startCause=" + startCause
				+ ", endCause=" + endCause + "]";
	}
}
