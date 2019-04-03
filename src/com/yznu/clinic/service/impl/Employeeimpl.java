package com.yznu.clinic.service.impl;

import com.yznu.clinic.beans.Departments;
import com.yznu.clinic.beans.Employee;
import com.yznu.clinic.dao.EmployeeMapper;
import com.yznu.clinic.interfaces.EmployeeSV;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/*
员工
 */
@Service
public class Employeeimpl implements EmployeeSV {
    @Autowired
    EmployeeMapper emplyoeemapper;


    @Override
    public PageList queryEmployeePage(Query query) {
        PageList pageList = new PageList();
        List<Employee> rows = emplyoeemapper.queryEmployeePage(query);
        int total = emplyoeemapper.getAllEmployeeCount(query);
        pageList.setRows(rows);
        pageList.setTotal(total);
        return pageList;
    }
    public int  updateEmployee(Employee employee){
        return emplyoeemapper.updateEmployee(employee);
    }

    @Override
    public int insertEmployee(Employee employee) {
        return emplyoeemapper.insertEmployee(employee);
    }

    @Override
    public Employee login(Map employee) {
        return emplyoeemapper.login(employee);
    }

    @Override
    public Employee queryemployeeById(String id) {
        return emplyoeemapper.queryemployeeById(id);
    }

    @Override
    public void deleteemployee(String id) {
        if(null!=id&&!"".equals(id)){
            String[] ids = id.split(",");
            for (String strid : ids) {
                emplyoeemapper.deleteemployee(strid);
            }
        }

    }
}
