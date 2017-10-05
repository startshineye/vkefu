package com.yxm.web.domain;
import java.util.List;
/**
 * 聊天记录VO
 * @author ye_xinming
 */
public class ChatRecordVO<T> {
	private int total;// 总共聊天记录几次
	private List<T> dataList;//记录列表
	private int pageTotal;//分页总页数
	private int perTotal;//每页记录数

	public int getPerTotal() {
		return perTotal;
	}

	public void setPerTotal(int perTotal) {
		this.perTotal = perTotal;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return "ChatRecordVO [total=" + total + ", dataList=" + dataList
				+ ", pageTotal=" + pageTotal + ", perTotal=" + perTotal + "]";
	}
}
