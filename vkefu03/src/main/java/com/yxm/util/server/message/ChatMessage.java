package com.yxm.util.server.message;

/**
 * 消息实体类
 * 
 * @author yxm
 * 
 */
public class ChatMessage {
	private String agentid;
    private String userid;
    private String sessionid;
    private String type;
    private String calltype;
	private String message;
	private String createtime;
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime){
		this.createtime = createtime;
	}
	public String getAgentid() {
		return agentid;
	}
	public String getCalltype() {
		return calltype;
	}
	public void setCalltype(String calltype) {
		this.calltype = calltype;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ChatMessage [agentid=" + agentid + ", userid=" + userid
				+ ", sessionid=" + sessionid + ", type=" + type + ", calltype="
				+ calltype + ", message=" + message + ", createtime="
				+ createtime + "]";
	}
}
