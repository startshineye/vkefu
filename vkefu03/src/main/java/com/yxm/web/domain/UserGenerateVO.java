package com.yxm.web.domain;
/**
 * 用户
 * 
 * @author yxm
 * @date 2016-11-1
 */
public class UserGenerateVO {
	private Integer userId;//主鍵
	private String userName;//用戶姓名
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
