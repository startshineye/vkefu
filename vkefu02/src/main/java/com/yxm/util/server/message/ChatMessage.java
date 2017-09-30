package com.yxm.util.server.message;

/**
 * 消息实体类
 * 
 * @author yxm
 * 
 */
public class ChatMessage {
	private String username;
	private String message;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ChatMessage [username=" + username + ", message=" + message
				+ "]";
	}
}
