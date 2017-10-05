package com.yxm.web.domain;
import java.util.List;

import com.google.gson.Gson;
/**
 * 实现分页的VO
 * @author yxm
 *
 */
public class PaginationVO<T> {
    private Long total;
    private List<T> dataList;
    private Gson gson = new Gson();

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	/**
	 * 将本对象转换成json
	 * @return
	 */
	public String toJson(){
		return gson.toJson(this);
	}
	@Override
	public String toString() {
		return "PaginationVO [total=" + total + ", dataList=" + dataList + "]";
	}
	
}
