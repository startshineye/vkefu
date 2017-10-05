package com.yxm.web.entity.api;
import java.util.List;

import com.google.gson.Gson;

/**
 * 初始化数据传输类
 * 
 * @author YXM
 * @param <T>
 * @date 2016-11-21
 */
public class InitData<T> extends BaseAPI{
	private String desc;
	private String agentId;
	private List<T> dataList;
	private Integer threadId;
	private static Gson gson = new Gson();// 为了让

	/**
	 * 将本对象转换成json
	 * 
	 * @return
	 */
	public String toJson() {
		return gson.toJson(this);
	}
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	 

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public Integer getThreadId() {
		return threadId;
	}

	public void setThreadId(Integer threadId) {
		this.threadId = threadId;
	}
}
