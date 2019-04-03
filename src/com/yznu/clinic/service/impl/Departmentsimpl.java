package com.yznu.clinic.service.impl;

import com.yznu.clinic.beans.Departments;
import com.yznu.clinic.beans.Projects;
import com.yznu.clinic.dao.DepartmentsMapper;
import com.yznu.clinic.interfaces.DepartmentsSV;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

//部门
@Service
public class Departmentsimpl implements DepartmentsSV {
    @Autowired
    DepartmentsMapper departmentsMapper;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    //获取所有记录
    public List<Departments> getalldepartments() {
        // TODO Auto-generated method stub
        List<Departments> data=departmentsMapper.selectAll();
        return data;
    }

    //删除一条数据
    public int deldepartments(Integer id) {
        // TODO Auto-generated method stub
        Departments departments=new Departments();
        departments.setId(id);
        departments.setDepartmentsState("0");
        int data=departmentsMapper.updateByPrimaryKeySelective(departments);
        return data;
    }

    //批量删除
    public int delalldepartments(Integer[] allId) {
        // TODO Auto-generated method stub
        int data=0;
        Departments departments=new Departments();
        for(int id:allId){
            departments.setId(id);
            departments.setDepartmentsState("0");
            data=departmentsMapper.updateByPrimaryKeySelective(departments);
        }
        return data;
    }

    //新增记录
    public int adddepartments(Departments departments) {
        // TODO Auto-generated method stub
        int data=departmentsMapper.insertSelective(departments);
        return data;
    }

    //加载修改信息
    public Departments loadupdatedepartments(Integer id) {
        // TODO Auto-generated method stub
        Departments data=departmentsMapper.selectByPrimaryKey(id);
        return data;
    }

    //根据id修改
    public int updatedepartments(Departments departments) {
        // TODO Auto-generated method stub
        int data=departmentsMapper.updateByPrimaryKeySelective(departments);
        return data;
    }

/*	public List<Projects> queryProjectsBySql(int currPage, int pageSize, String sta) {
		// TODO Auto-generated method stub
		 Map<String, Object> data = new HashedMap();
	        data.put("currIndex", (currPage-1)*pageSize);
	        data.put("pageSize", pageSize);
	        data.put("state", sta);
	        return departmentsMapper.queryProjectsBySql(data);

	}*/

    //查询当前查询记录总条数
    public int queryTotalsize(int pagesize, Map<String, Object> data) {
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
        return departmentsMapper.queryTotalsize(data);
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

        List<Projects> data1=departmentsMapper.querysomedata(data);

        return data1;
    }



    @Override
    public List<Departments> queryAll(){
       return departmentsMapper.queryAll();
    }

    @Override
    public List<Departments> query(String id) {
        return departmentsMapper.query(id);
    }

    @Override
    public PageList querydepartmentsPage(Query query) {
        PageList pageList = new PageList();
        List<Departments> rows = departmentsMapper.querydepartmentsPage(query);
        int total = departmentsMapper.getAlldepartementCount(query);
        pageList.setRows(rows);
        pageList.setTotal(total);
        return pageList;
    }

    @Override
    public PageList getdepartmentID(Query query) {
        PageList pageList = new PageList();
        List<Departments> rows = departmentsMapper.getdepartmentID(query);
        int total = departmentsMapper.getAlldepartementCount(query);
        pageList.setRows(rows);
        pageList.setTotal(total);
        return pageList;
    }

}