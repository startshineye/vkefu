package com.founder.focuss.webcc.entity;
/**
 * 用户实体类(此用户包括坐席和用户)
 * @author yxm
 * @date 2016-11-14
 */
public class Person {
    private String personId;
    private String personName;
    private String group;
    private String type;
    private String status;
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
