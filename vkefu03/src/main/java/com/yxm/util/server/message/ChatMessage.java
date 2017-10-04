package com.yxm.util.server.message;

/**
 * 消息实体类
 * 
 * @author yxm
 * 
 */
public class ChatMessage {
	private String name;
	private String message;
	 
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ChatMessage [name=" + name + ", message=" + message + "]";
	}
}
