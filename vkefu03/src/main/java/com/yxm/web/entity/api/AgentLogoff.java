package com.founder.focuss.webcc.entity.api;

import com.google.gson.Gson;

public class AgentLogoff extends BaseAPI{
    private String agentId;
	private static Gson gson = new Gson();//为了让
	
	public String toJson(){
		return gson.toJson(this);
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
}
