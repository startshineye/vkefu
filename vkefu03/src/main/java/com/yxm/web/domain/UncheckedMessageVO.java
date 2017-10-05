package com.yxm.web.domain;
import java.util.List;
/**
 * 最新消息实现类
 * @author yxm
 * @param <T>
 *
 */
public class UncheckedMessageVO<T> {
    private Long lastMsgId;
    private List<T> dataList;

	public Long getLastMsgId() {
		return lastMsgId;
	}

	public void setLastMsgId(Long lastMsgId2) {
		this.lastMsgId = lastMsgId2;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return "LastMessageVO [lastMsgId=" + lastMsgId + ", dataList="
				+ dataList + "]";
	}
}
