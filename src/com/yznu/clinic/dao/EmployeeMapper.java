package com.yznu.clinic.dao;

import com.yznu.clinic.beans.*;
import com.yznu.clinic.util.Query;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    public List<Employee> queryEmployeePage(Query query);  //根据条件查询员工信息

    public int getAllEmployeeCount(Query query);    //员工条数

    public void deleteemployee(String id);      //删除员工信息

    public Employee queryemployeeById(String id);   //根据ID查询员工信息

    int updateEmployee(Employee employee);  //更新员工信息

    int insertEmployee(Employee employee); //添加员工

    public  Employee login(Map employee);
}