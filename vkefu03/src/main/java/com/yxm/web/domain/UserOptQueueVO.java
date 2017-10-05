package com.yxm.web.domain;
public class UserOptQueueVO {
	private Integer id; 
	 private String  userId;
	 private String  userName;
	 private String queueId;
	 private String queueinTime;
	 private Integer queueoutCause;
	 private String queueoutTime;
	 private Integer status;
	 
	 public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getQueueId() {
		return queueId;
	}
	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}
	public String getQueueinTime() {
		return queueinTime;
	}
	public void setQueueinTime(String queueinTime) {
		this.queueinTime = queueinTime;
	}
	public Integer getQueueoutCause() {
		return queueoutCause;
	}
	public void setQueueoutCause(Integer queueoutCause) {
		this.queueoutCause = queueoutCause;
	}
	public String getQueueoutTime() {
		return queueoutTime;
	}
	public void setQueueoutTime(String queueoutTime) {
		this.queueoutTime = queueoutTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
