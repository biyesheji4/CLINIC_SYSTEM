package com.yznu.clinic.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yznu.clinic.beans.Projects;
import com.yznu.clinic.dao.ProjectsMapper;
import com.yznu.clinic.interfaces.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	ProjectsMapper projectsMapper;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

	//获取所有记录
	public List<Projects> getallproject() {
		// TODO Auto-generated method stub
		List<Projects> data=projectsMapper.selectAll();
		return data;
	}

	//删除一条数据
	public int delproject(Integer id) {
		// TODO Auto-generated method stub
		Projects projects=new Projects();
		projects.setId(id);
		projects.setProjectsState("0");
		int data=projectsMapper.updateByPrimaryKeySelective(projects);
		return data;
	}

	//批量删除
	public int delallproject(Integer[] allId) {
		// TODO Auto-generated method stub
		int data=0;
		Projects projects=new Projects();
		for(int id:allId){
			projects.setId(id);
			projects.setProjectsState("0");
			data=projectsMapper.updateByPrimaryKeySelective(projects);
		}
		return data;
	}

	//新增记录
	public int addproject(Projects projects) {
		// TODO Auto-generated method stub
		int data=projectsMapper.insertSelective(projects);
		return data;
	}

	//根据id修改
	public int updateproject(Projects projects) {
		// TODO Auto-generated method stub
		int data=projectsMapper.updateByPrimaryKeySelective(projects);
		return data;
	}

	//加载修改信息
	public Projects loadupdateproject(Integer id) {
		// TODO Auto-generated method stub
		Projects data=projectsMapper.selectByPrimaryKey(id);
		return data;
	}

	/*public List<Projects> queryProjectsBySql(int currPage, int pageSize, String sta) {
		// TODO Auto-generated method stub
		 Map<String, Object> data = new HashedMap();
	        data.put("currIndex", (currPage-1)*pageSize);
	        data.put("pageSize", pageSize);
	        data.put("state", sta);
	        return projectsMapper.queryProjectsBySql(data);

	}*/

	//查询当前查询记录总条数
	public int queryTotalsize(int pagesize,Map<String, Object> data) {
		// TODO Auto-generated method stub
		String date1=(String) data.get("date1");
		String date2=(String) data.get("date2");
		String searchindex=(String) data.get("searchindex");
		if(date1==null||date1==""){
			date1="0000-00-00 00:00:00";
		}else{
			date1=date1+" 00:00:00";
		}
		if(date2==null||date2==""){
			Date date=new Date();
			date2=sdf.format(date);
		}else{
			date2=date2+" 00:00:00";
		}
		if(searchindex==null||searchindex==""){
			searchindex="%%";
		}else{
			searchindex="%"+searchindex+"%";
		}

		data.put("date1", date1);
		data.put("date2", date2);
		data.put("searchindex", searchindex);
		return projectsMapper.queryTotalsize(data);
	}

	//分页查询&条件查询
	public List<Projects> querysomedata(Map<String, Object> data) {
		// TODO Auto-generated method stub
		String date1=(String) data.get("date1");
		String date2=(String) data.get("date2");
		String searchindex=(String) data.get("searchindex");
		if(date1==null||date1==""){
			date1="0000-00-00 00:00:00";
		}else{
			date1=date1+" 00:00:00";
		}
		if(date2==null||date2==""){
			Date date=new Date();
			date2=sdf.format(date);
		}else{
			date2=date2+" 00:00:00";
		}
		if(searchindex==null||searchindex==""){
			searchindex="%%";
		}else{
			searchindex="%"+searchindex+"%";
		}

		data.put("date1", date1);
		data.put("date2", date2);
		data.put("searchindex", searchindex);

		List<Projects> data1=projectsMapper.querysomedata(data);

		return data1;
	}

	//查询科室列表
	public List<HashMap<String, Object>> queryusededepartment() {
		// TODO Auto-generated method stub
		return projectsMapper.queryusededepartment();
	}

	@Override
	public Projects selectByPrimaryKey(Integer id) {
		return projectsMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageList queryprojectPage(Query query) {
		PageList pageList = new PageList();
		List rows = projectsMapper.queryprojectPage(query);
		int total = projectsMapper.getAllprojectCount(query);
		pageList.setRows(rows);
		pageList.setTotal(total);
		return pageList;
	}


}
