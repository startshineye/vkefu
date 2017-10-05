package com.yxm.web.domain;
import java.util.List;
import com.google.gson.Gson;
/**
 * 用于记录满意度评价的消息发送
 * 
 * @author yxm
 * @date 2016-10-11
 */
public class SatisfactionMessageVO<T> {
	private String  sfmessage;// 满意度信息
	private String sfmessageTime;// 满意度消息时间
	private String sffrom;// 消息来源
	private String sfto;// 消息去向
	private String type;//type=1 表示是自动回复消息
	private static Gson gson = new Gson();// 为了让
	private List<T> sfdataList;
	/**
	 * 将对象转换成json
	 * 
	 * @return
	 */
	public String toJson() {
		return gson.toJson(this);
	}
	public String getSfmessageTime() {
		return sfmessageTime;
	}

	public void setSfmessageTime(String sfmessageTime) {
		this.sfmessageTime = sfmessageTime;
	}
	public String getSfmessage() {
		return sfmessage;
	}
	public void setSfmessage(String sfmessage) {
		this.sfmessage = sfmessage;
	}
	public String getSffrom() {
		return sffrom;
	}

	public void setSffrom(String sffrom) {
		this.sffrom = sffrom;
	}

	public String getSfto() {
		return sfto;
	}

	public void setSfto(String sfto) {
		this.sfto = sfto;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public List<T> getSfdataList() {
		return sfdataList;
	}
	public void setSfdataList(List<T> sfdataList) {
		this.sfdataList = sfdataList;
	}
	@Override
	public String toString() {
		return "SatisfactionMessageVO [sfmessage=" + sfmessage
				+ ", sfmessageTime=" + sfmessageTime + ", sffrom=" + sffrom
				+ ", sfto=" + sfto + ", type=" + type + ", sfdataList="
				+ sfdataList + "]";
	}
}
