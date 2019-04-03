package com.yznu.clinic.util;

import java.util.ArrayList;
import java.util.List;

public class PageList {

	//页面的总记录条数
	private int total=0;
		
	//返回数据的结果集
	private List rows=new ArrayList();
	
	public PageList() {
		super();
	}
	
	public PageList(int total, List rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public List getRows() {
		return rows;
	}
	
	public void setRows(List rows) {
		this.rows = rows;
	}
}
