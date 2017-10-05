package com.yxm.web.entity.api;


import com.google.gson.Gson;

/**
 * 坐席转移发送消息接口类
 * 
 * @author yxm
 * @date 2016-11-29
 */
public class AgentTransferServiceConfirm extends BaseAPI{
	private String agentTransferServiceAgentId;
	private String agentTransferServiceContent;
	private String agentTransferServiceFlag;
	private String agentTransferServiceSessionId;
	private Gson gson = new Gson();

	public String getAgentTransferServiceSessionId() {
		return agentTransferServiceSessionId;
	}

	public String getAgentTransferServiceAgentId() {
		return agentTransferServiceAgentId;
	}

	public void setAgentTransferServiceAgentId(
			String agentTransferServiceAgentId) {
		this.agentTransferServiceAgentId = agentTransferServiceAgentId;
	}

	public void setAgentTransferServiceSessionId(
			String agentTransferServiceSessionId) {
		this.agentTransferServiceSessionId = agentTransferServiceSessionId;
	}

	public String getAgentTransferServiceContent() {
		return agentTransferServiceContent;
	}

	public void setAgentTransferServiceContent(
			String agentTransferServiceContent) {
		this.agentTransferServiceContent = agentTransferServiceContent;
	}

	public String getAgentTransferServiceFlag() {
		return agentTransferServiceFlag;
	}

	public void setAgentTransferServiceFlag(String agentTransferServiceFlag) {
		this.agentTransferServiceFlag = agentTransferServiceFlag;
	}

	public String toJson() {
		return gson.toJson(this);
	}
}
