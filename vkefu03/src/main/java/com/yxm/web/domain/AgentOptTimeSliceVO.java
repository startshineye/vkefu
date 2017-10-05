package com.yxm.web.domain;
public class AgentOptTimeSliceVO {
	private Integer id;
	private String agentId;
	private String agentName;
	private Long onlineTimeSlice;
	private Long offlineTimeSlice;
	private String endTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Long getOnlineTimeSlice() {
		return onlineTimeSlice;
	}

	public void setOnlineTimeSlice(Long onlineTimeSlice) {
		this.onlineTimeSlice = onlineTimeSlice;
	}

	public Long getOfflineTimeSlice() {
		return offlineTimeSlice;
	}

	public void setOfflineTimeSlice(Long offlineTimeSlice) {
		this.offlineTimeSlice = offlineTimeSlice;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
