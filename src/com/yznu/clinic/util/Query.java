package com.yznu.clinic.util;
import java.util.HashMap;
import java.util.Map;


public class Query {

	/*
	 * 以下是用于分页查询的字段
	 */
	private Map param = new HashMap();

	private int pageNumber=1;//从哪条数据开始
	private int pageSize=10;//每页多少条数据
	


	public int getPageNumber() {
		return pageNumber;
	}
	
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getEndNumber(){
		return (pageNumber+pageSize);
	}
	public Map getParam() {
		return param;
	}
	public void setParam(Map param) {
		this.param = param;
	}
	
//	
//	public void setQueryParam(String queryName) {
//		Subject currentUser = SecurityUtils.getSubject();
//		try {
//			currentUser.getSession().setAttribute(queryName, param);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}

	
	
}
