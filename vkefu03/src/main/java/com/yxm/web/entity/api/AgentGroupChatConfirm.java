package com.yxm.web.entity.api;
import com.google.gson.Gson;

/**
 * 坐席转移发送消息接口类
 * 
 * @author yxm
 * @date 2016-11-29
 */
public class AgentGroupChatConfirm extends BaseAPI{
	private String agentGroupChatContent;
	private String agentGroupChatFlag;
	private String groupChatAgentId;
	private Integer agentGroupChatSessionId;
	private Gson gson = new Gson();
	 
	public String toJson(){
		return gson.toJson(this);
	}

	public String getAgentGroupChatContent() {
		return agentGroupChatContent;
	}

	public void setAgentGroupChatContent(String agentGroupChatContent) {
		this.agentGroupChatContent = agentGroupChatContent;
	}

	public String getGroupChatAgentId() {
		return groupChatAgentId;
	}

	public void setGroupChatAgentId(String groupChatAgentId) {
		this.groupChatAgentId = groupChatAgentId;
	}

	public String getAgentGroupChatFlag() {
		return agentGroupChatFlag;
	}

	public void setAgentGroupChatFlag(String agentGroupChatFlag) {
		this.agentGroupChatFlag = agentGroupChatFlag;
	}

	public Integer getAgentGroupChatSessionId() {
		return agentGroupChatSessionId;
	}

	public void setAgentGroupChatSessionId(Integer agentGroupChatSessionId) {
		this.agentGroupChatSessionId = agentGroupChatSessionId;
	}
}
