package com.yznu.clinic.interfaces;

import com.yznu.clinic.beans.Employee;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;

import java.util.Map;

public interface EmployeeSV {
    public PageList queryEmployeePage(Query query); //分页查询员工信息

    public void deleteemployee(String id);      //删除员工信息

    public Employee queryemployeeById(String id);   //根据ID查询员工信息

    int updateEmployee(Employee employee);  //更新员工信息

    int insertEmployee(Employee employee); //添加员工

    public  Employee login(Map employee);
}
