package com.yxm.web.domain;
/**
 * 黑名单
 * 
 * @author yxm
 * @date 2016-11-21
 * 
 */
public class BlackListVO {
	private Integer blackListId;//主键
	private String agentId;//坐席id
	private String userInfo;//用户信息
	private String userType;//用户类型
	private String startTime;//时间
	private String reason;//原因
	
	public Integer getBlackListId() {
		return blackListId;
	}
	public void setBlackListId(Integer blackListId) {
		this.blackListId = blackListId;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
	public String getUserType() {
		return userType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
