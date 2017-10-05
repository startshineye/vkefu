package com.founder.focuss.webcc.entity;

import java.util.List;

import com.founder.focuss.webcc.domain.BlackListVO;
import com.founder.focuss.webcc.domain.ParamVO;

/**
 * 系统初始化bean
 * 
 * @author yxm
 * @date 2016-11-24
 */
public class SystemInitBean {
	private List<ParamVO> paramList;
	private List<BlackListVO> blackList;

	public List<ParamVO> getParamList() {
		return paramList;
	}

	public void setParamList(List<ParamVO> paramList) {
		this.paramList = paramList;
	}

	public List<BlackListVO> getBlackList() {
		return blackList;
	}

	public void setBlackList(List<BlackListVO> blackList) {
		this.blackList = blackList;
	}

}
