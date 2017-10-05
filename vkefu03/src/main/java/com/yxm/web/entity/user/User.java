package com.yxm.web.entity.user;
public class User{
	private Integer id;//主键
	private String userId;//用户Id
	private String userName;//用户姓名
	private String source;//用户来源(微信,web,微博)
	private String userType;//用户类型(访客,用户,VIP用户)
	private String loginTime;//登录时间
	private String browser;//浏览器
	private String ip;//用户访问ip
	private String chatStatus;//聊天状态,当聊天状态结束时候,websocket重连时候不分配坐席
	private String operSystem;//操作系统
	private String status;//状态
	private String mobile;//手机
	private String country;//国家
	private String province;//省份
	private String city;//城市
	
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getChatStatus() {
		return chatStatus;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setChatStatus(String chatStatus) {
		this.chatStatus = chatStatus;
	}
	public String getOperSystem() {
		return operSystem;
	}
	public void setOperSystem(String operSystem) {
		this.operSystem = operSystem;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
