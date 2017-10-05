package com.yxm.web.domain;
/**
 * 自动回复实体类
 * 
 * @author yxm
 * @date 2016-10-12
 */
public class CommonWordVO {
	private Integer commonwordId;//主鍵
	private String agentId;//坐席ID
	private String content;//內容
	private Integer orderNo;//順序

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getCommonwordId() {
		return commonwordId;
	}

	public void setCommonwordId(Integer commonwordId) {
		this.commonwordId = commonwordId;
	}
}
