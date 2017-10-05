package com.yxm.web.domain;
/**
 * 参数实体类
 * 
 * @author yxm
 * @date 2016-11-17
 */
public class ParamVO {
	private Integer id;//主键
	private String paramKey;//参数key
	private String paramValue;//参数value
	private String description;//描述
	private String agentId;
	private String createTime;
	private String role;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getParamKey() {
		return paramKey;
	}
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "ParamVO [id=" + id + ", paramKey=" + paramKey + ", paramValue="
				+ paramValue + ", description=" + description + ", agentId="
				+ agentId + ", createTime=" + createTime + ", role=" + role
				+ "]";
	}
}
