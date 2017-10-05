package com.yxm.web.domain;
/**
 * 留言实体类
 * 
 * @author yxm
 * @date 2016-11-21
 */
public class LeaveMsgVO {
	private Integer Id;//主键
	private String userId;//用户Id
	private String userName;//用户名称
	private String userInfo;//用户昵称
	private String email;//邮箱
	private String mobile;//电话
	private String qq;//qq
	private String message;//留言信息
	private String agentId;//坐席ID
	private String startTime;//留言时间
	private String handleTime;//处理时间
	private Integer status;//处理状态
	private String handleDescription;//处理描述
	
	public String getHandleDescription() {
		return handleDescription;
	}
	public void setHandleDescription(String handleDescription) {
		this.handleDescription = handleDescription;
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
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
}
