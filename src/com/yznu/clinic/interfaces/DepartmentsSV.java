package com.yznu.clinic.interfaces;

import com.yznu.clinic.beans.Departments;
import com.yznu.clinic.beans.Projects;
import com.yznu.clinic.util.Page;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;

import java.util.List;
import java.util.Map;

public interface DepartmentsSV {
    public List<Departments> queryAll();

    public List<Departments> query(String id);

    /*部门明细*/
    public PageList querydepartmentsPage(Query query);

    /*部门ID*/
    public PageList getdepartmentID(Query query);

    //获取所有记录
    List<Departments> getalldepartments();
    //删除一条数据
    int deldepartments(Integer id);
    //批量删除
    int delalldepartments(Integer[] allId);
    //新增记录
    int adddepartments(Departments departments);
    //加载修改信息
    Departments loadupdatedepartments(Integer id);
    //根据id修改
    int updatedepartments(Departments departments);
    //查询当前查询记录总条数
    int queryTotalsize(int pAGESIZE,Map<String, Object> data);

    //List<Projects> queryProjectsBySql(int cURRPAGE, int pAGESIZE, String sta);
    //分页查询&条件查询
    List<Projects> querysomedata(Map<String, Object> data);


}