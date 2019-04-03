package com.yznu.clinic.dao;

import java.util.List;
import java.util.Map;


import com.yznu.clinic.beans.Departments;
import com.yznu.clinic.beans.Projects;
import com.yznu.clinic.util.Query;

public interface DepartmentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Departments record);

    int insertSelective(Departments record);

    Departments selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Departments record);

    int updateByPrimaryKey(Departments record);

    List<Departments> selectAll();

    List<Projects> querysomedata(Map<String, Object> data);

    int queryTotalsize(Map<String, Object> data);

    List<Projects> queryProjectsBySql(Map<String, Object> data);


    /*部门明细*/
    public List<Departments> querydepartmentsPage(Query query);

    /*部门明细count*/
    public int getAlldepartementCount(Query query);

    /*部门ID*/
    public List<Departments> getdepartmentID(Query query);

    public List<Departments> queryAll();

    public List<Departments> query(String id);
}