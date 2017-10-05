package com.yxm.web.domain;
/**
 * 用户
 * 
 * @author yxm
 * @date 2016-11-1
 */
public class UserOptLogVO {
	private Integer id;//主鍵
	private String userId;//用戶id
	private String userName;//用戶姓名
	private String queueId;//用戶所在隊列
	private String startTime;//時間
	private Integer optCode;//用戶操作碼
	private Integer optResult;//操作結果
	private String remark;//說明
	
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
	public String getRemark() {
		return remark;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
