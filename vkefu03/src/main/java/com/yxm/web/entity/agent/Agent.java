package com.yxm.web.entity.agent;
/**
 * 坐席
 * @author yxm
 * @date 2017-6-12
 */
public class Agent{
	private String agentId;//坐席工号
	private String agentName;//姓名
	private String loginTime;// 登陆时间
	private String status;// 当前状态
	private String login;//登录与否(on:登录 off:注销)
	private String groups;// 技能组
	private Integer serUsernum;//坐席当前服务数量
	private Integer maxUsers;//坐席最大服务数量
	private Integer entId;//企业Id
	public String getAgentId() {
		return agentId;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
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
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	 
	public Integer getSerUsernum() {
		return serUsernum;
	}
	public void setSerUsernum(Integer serUsernum) {
		this.serUsernum = serUsernum;
	}
	public Integer getMaxUsers() {
		return maxUsers;
	}
	public void setMaxUsers(Integer maxUsers) {
		this.maxUsers = maxUsers;
	}
	public Integer getEntId() {
		return entId;
	}
	public void setEntId(Integer entId) {
		this.entId = entId;
	}
}
