package com.yznu.clinic.util;

import java.util.List;

import com.yznu.clinic.beans.Projects;

public class QueryPage {
	//第几页
	private int currpage;
	//一页有几条
	private int pagesize;
	//所有条数
	private long allnum;
	//页面条数
	private int totalsize;
	private Object pagedata;
	
	
	public QueryPage(int currpage,int pagesize, int totalsize,long allnum,Object pagedata) {
		super();
		this.currpage = currpage;
		this.totalsize = totalsize;
		this.pagedata = pagedata;
		this.pagesize = pagesize;
		this.allnum = allnum;
	}

	public QueryPage() {
	}
	
	
	
	public long getAllnum() {
		return allnum;
	}

	public void setAllnum(long allnum) {
		this.allnum = allnum;
	}

	public int getCurrpage() {
		return currpage;
	}
	public void setCurrpage(int currpage) {
		this.currpage = currpage;
	}

	public int getTotalsize() {
		return totalsize;
	}

	public void setTotalsize(int totalsize) {
		this.totalsize = totalsize;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public Object getPagedata() {
		return pagedata;
	}

	public void setPagedata(Object pagedata) {
		this.pagedata = pagedata;
	}
	

}
