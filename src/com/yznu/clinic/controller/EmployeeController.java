package com.yznu.clinic.controller;

import com.yznu.clinic.beans.Departments;
import com.yznu.clinic.beans.Employee;
import com.yznu.clinic.interfaces.DepartmentsSV;
import com.yznu.clinic.interfaces.EmployeeSV;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeSV employeeSV;
    @Autowired
    DepartmentsSV departmentsSV;

    /*
查询所有员工
 */
    @RequestMapping(value = "getEmployeeAll.do")
    @ResponseBody
    public PageList getdepartmentAll(Query query){
        String sort = (String) query.getParam().get("sort");
        String sortOrder = (String) query.getParam().get("sortOrder");
        String textarea = (String) query.getParam().get("textarea");
        String employeeState = (String) query.getParam().get("state");
        query.getParam().put("sort", sort);
        query.getParam().put("sortOrder", sortOrder);
        query.getParam().put("textarea",textarea);
        query.getParam().put("state",employeeState);
        PageList pageList = employeeSV.queryEmployeePage(query);
        return pageList;
    }

    /*
      查看单个员工详情
       */
    @RequestMapping(value = "query-employee.do")
    @ResponseBody
    public ModelAndView queryregistered(HttpServletRequest req){
        String id = req.getParameter("id");
        Employee list = employeeSV.queryemployeeById(id);
        ModelAndView mav = new ModelAndView("employee-query");
        req.getSession().setAttribute("employee", list);
        return mav;
    }

   /*
进入更新模态框
 */
    @RequestMapping(value = "input-employeeupdate.do")
    @ResponseBody
    public Map inputUpdate(HttpServletRequest req){
        String id = req.getParameter("id");
        Employee list = employeeSV.queryemployeeById(id);
        List<Departments> departmentsAll= departmentsSV.queryAll();
        Map map = new HashMap();
        if(list.getDepartmentsId()!=null){
            List<Departments> onedepartment= departmentsSV.query(list.getDepartmentsId());
            map.put("one",onedepartment);
        }
        map.put("employee",list);
        map.put("departmentsAll",departmentsAll);

        return map;
    }

    /**
    执行更新
    */
    @RequestMapping(value = "updateEmployee.do")
    @ResponseBody
    public Map updateEmployee(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        String employeeName = req.getParameter("employeeName");
        int employeeNum = Integer.parseInt(req.getParameter("employeeNum"));
        String employeeOccupation = req.getParameter("employeeOccupation");
        String employeeBirth = req.getParameter("employeeBirth");
        String employeeTel = req.getParameter("employeeTel");
        String employeeEmail = req.getParameter("employeeEmail");
        String employeeSalary = req.getParameter("employeeSalary");
        String departmentsId = req.getParameter("departmentsName");
        String employeeSex = req.getParameter("employeeSex");

        Employee em  = new Employee();
       em.setDepartmentsId(departmentsId);em.setEmployeeBirth(employeeBirth);em.setId(id);
       em.setEmployeeEmail(employeeEmail); em.setEmployeeName(employeeName);em.setEmployeeNum(employeeNum);
       em.setEmployeeOccupation(employeeOccupation);em.setEmployeeTel(employeeTel);em.setEmployeeSalary(employeeSalary);
       em.setEmployeeSex(employeeSex);
        employeeSV.updateEmployee(em);
        Map map = new HashMap();
        return map;
    }

    /**
     * 对离职员工进行恢复
     */
    @RequestMapping(value = "recovery.do")
    @ResponseBody
    public Map recoveryEmployy(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        Employee em  = new Employee();
        em.setId(id);em.setEmployeeState("1");
        employeeSV.updateEmployee(em);
        Map map = new HashMap();
        return map;
    }


    /**
  employee.jsp页面删除按钮
  实际是对员工状态进行修改
    */
    @RequestMapping(value = "deleteemployee.do")
    @ResponseBody
    public Map delete(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        String employeeState = "2";
        Employee employee = new Employee();
        employee.setId(id);
        employee.setEmployeeState(employeeState);
        employeeSV.updateEmployee(employee);

        Map<String, String> map = new HashMap<String, String>();
        return map;
    }


    /**
    添加员工信息
     */
    @RequestMapping(value = "insertEmployee.do")
    @ResponseBody
    public Map insertEmployee(HttpServletRequest req){
        int employeeNum = Integer.parseInt(req.getParameter("employeeNum"));
        String employeeName = req.getParameter("employeeName");
        String employeeBirth = req.getParameter("employeeBirth");
        String employeeEmail = req.getParameter("employeeEmail");
        String employeeSalary = req.getParameter("employeeSalary");
        String employeeDate = req.getParameter("employeeDate");
        String employeeTel = req.getParameter("employeeTel");
        String departmentId = req.getParameter("departmentId");
        String employeeSex = req.getParameter("employeeSex");
        String employeeOccupation = req.getParameter("employeeOccupation");
        String employeeState = "1";
        Employee employee = new Employee();
        employee.setEmployeeNum(employeeNum);employee.setEmployeeName(employeeName);
        employee.setEmployeeBirth(employeeBirth);employee.setEmployeeEmail(employeeEmail);
        employee.setEmployeeSalary(employeeSalary);employee.setEmployeeDate(employeeDate);
        employee.setEmployeeTel(employeeTel);employee.setDepartmentsId(departmentId);
        employee.setEmployeeSex(employeeSex);employee.setEmployeeOccupation(employeeOccupation);
        employee.setEmployeeState(employeeState);
        employeeSV.insertEmployee(employee);

        Map<String, String> map = new HashMap<String, String>();
        return map;
    }
}
