package com.yxm.web.domain;
import com.google.gson.Gson;
/**
 * 聊天消息实体类
 * @author yxm
 * @date 2016-10-31
 */
public class MessageVO{
	private Integer messageId;//主键
	private Integer threadId;//聊天id
	private Integer sessionId;//会话id
	private Integer source;//消息来源
	private Integer status;//读取状态
	private String fromId;//发消息用户id
	private String fromName;//发消息用户姓名
	private String  ownerId;//服务客户
	private String ownerName;//服务姓名
	private String show;//聊天显示
	private Integer messageFrom;//消息来源
	private Integer messageType;//消息类型
	private String messageTime;//消息时间
	private String message;//消息内容
	private static Gson gson = new Gson();//为了让
	/**
	 * 将本对象转换成json
	 * @return
	 */
	public String toJson(){
		return gson.toJson(this);
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
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Integer getThreadId() {
		return threadId;
	}
	public void setThreadId(Integer threadId) {
		this.threadId = threadId;
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
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public Integer getMessageFrom() {
		return messageFrom;
	}
	public void setMessageFrom(Integer messageFrom) {
		this.messageFrom = messageFrom;
	}
	public Integer getMessageType() {
		return messageType;
	}
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	public String getMessageTime() {
		return messageTime;
	}
	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "MessageVO [messageId=" + messageId + ", threadId=" + threadId
				+ ", sessionId=" + sessionId + ", source=" + source
				+ ", status=" + status + ", fromId=" + fromId + ", fromName="
				+ fromName + ", ownerid=" + ownerId + ", ownername="
				+ ownerName + ", show=" + show + ", messageFrom=" + messageFrom
				+ ", messageType=" + messageType + ", messageTime="
				+ messageTime + ", message=" + message + "]";
	}
}
