package com.yxm.web.entity.api;
/**
 * 坐席(model)
 * @author yxm
 * @date 2016-08-25
 */
public class AgentShow {
	// 坐席工号
	private String agentId;
	// 姓名，
	private String agentName;
	// 登陆时间
	private String loginTime;
	// 技能组
	private String groups;
	// 当前状态
	private String status;
	// 当前状态开始时间
	private String startTime;
	// 服务对象
	private String userId;
	
	public Integer getServiceNum() {
		return serviceNum;
	}
	public void setServiceNum(Integer serviceNum) {
		this.serviceNum = serviceNum;
	}
	//服务人数
	private Integer serviceNum;
	
	 
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
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	 
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@Override
	public String toString() {
		return "AgentShow [agentId=" + agentId + ", agentName=" + agentName
				+ ", loginTime=" + loginTime + ", groups=" + groups
				+ ", status=" + status + ", startTime=" + startTime
				+ ", userId=" + userId + ", serviceNum=" + serviceNum + "]";
	}
}
