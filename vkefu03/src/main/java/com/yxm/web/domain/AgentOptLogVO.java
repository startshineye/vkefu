package com.yxm.web.domain;
public class AgentOptLogVO {
	private Integer id;// 主键
	private Integer threadId;//聊天Id
	private String agentId;// 坐席id
	private String agentName;// 坐席姓名
	private String userId;// 用户id
	private String userName;// 用户名
	private Integer entId;// 企业id
	private String queueId;// 坐席队列id
	private Integer optCode;// 操作码
	private Integer optResult;// 操作结果
	private String toUserId;// 坐席转移用户
	private String toAgentId;// 坐席转移坐席
	private String startTime;// 操作时间
	private String logTime;// 记录日记时间
	private String remark;// 说明

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getThreadId() {
		return threadId;
	}

	public void setThreadId(Integer threadId) {
		this.threadId = threadId;
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

	public Integer getEntId() {
		return entId;
	}

	public void setEntId(Integer entId) {
		this.entId = entId;
	}

	public String getQueueId() {
		return queueId;
	}

	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}

	public Integer getOptCode() {
		return optCode;
	}

	public void setOptCode(Integer optCode) {
		this.optCode = optCode;
	}

	public Integer getOptResult() {
		return optResult;
	}

	public void setOptResult(Integer optResult) {
		this.optResult = optResult;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getToAgentId() {
		return toAgentId;
	}

	public void setToAgentId(String toAgentId) {
		this.toAgentId = toAgentId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
