package com.yxm.web.entity.api;
import com.google.gson.Gson;
/****
 * 消息提示类:该类提示坐席用户已经发起连接
 * @author yxm
 *@date: 2017/4/5
 *
 */
public class MessageTip extends BaseAPI{
	private Integer threadId;
	private Integer sessionId;
	private Integer source;
	private String fromId;
	private String agentId;
	private String fromName;
	private String ownerId;//被服务客户
	private String ownerName;
	private String show;//聊天展示
 
 
	private static Gson gson = new Gson();//为了让
	
	public String toJson(){
		return gson.toJson(this);
	}
	public String getAgentId() {
		return agentId;
	}

	public Integer getThreadId() {
		return threadId;
	}
	public void setThreadId(Integer threadId) {
		this.threadId = threadId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getFromName() {
		return fromName;
	}

	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	@Override
	public String toString() {
		return "MessageTip [threadId=" + threadId + ", sessionId=" + sessionId
				+ ", source=" + source + ", fromId=" + fromId + ", agentId="
				+ agentId + ", fromName=" + fromName + ", ownerId=" + ownerId
				+ ", ownerName=" + ownerName + ", show=" + show + "]";
	}
}
