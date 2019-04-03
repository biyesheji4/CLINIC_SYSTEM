package com.yznu.clinic.controller;

import com.yznu.clinic.beans.Departments;
import com.yznu.clinic.beans.Employee;
import com.yznu.clinic.interfaces.DepartmentsSV;

import com.yznu.clinic.interfaces.EmployeeSV;
import com.yznu.clinic.interfaces.RegisteredSV;
import com.yznu.clinic.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    DepartmentsSV departmentsSV;
    @Autowired
    RegisteredSV registeredSV;
    @Autowired
    EmployeeSV employeeSV;

    @RequestMapping(value = "index.do")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping(value = "login.do")
    public ModelAndView login(HttpServletRequest req) {

        req.getSession().invalidate();//清除 session 中的所有信息
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value = "logins.do")
    @ResponseBody
    public Map logins(HttpServletRequest req) {
        String validateC = (String) req.getSession().getAttribute("validateCode");
        String veryCode = req.getParameter("veryCode");
        int username = Integer.parseInt(req.getParameter("username"));
        String password = req.getParameter("password");
        Map map = new HashMap();
        map.put("username",username);
        map.put("password",password);
        Employee list = employeeSV.login(map);

            if (validateC.equals(veryCode)) {
                if (list != null) {
                    Map mav = new HashMap();
                    mav.put("view","index");
                    HttpSession session = req.getSession();
                    session.setAttribute("type", list.getEmployeeOccupation());
                    session.setAttribute("employee_num",list.getEmployeeNum());
                    session.setAttribute("employee_name",list.getEmployeeName());
                    session.setAttribute("employee_email",list.getEmployeeEmail());
                    session.setAttribute("employee_date",list.getEmployeeDate());
                    session.setAttribute("employee_tel",list.getEmployeeTel());
                    session.setAttribute("employee_id",list.getId());
                    session.setAttribute("departments",list.getDepartments().getDepartmentsName());
                    session.setAttribute("departmentsid",list.getDepartments().getId());
                    return mav;
                } else {
                    Map mav = new HashMap();
                    mav.put("view","usernotexist");
                    return mav;
                }
            } else {
                Map mav = new HashMap();
                mav.put("view","false");
                return mav;
            }

    }

    /*
    页面跳转公用
     */
    @RequestMapping(value = "redirect.do")
    public ModelAndView registeredlist(HttpServletRequest req) {
        String userId = req.getSession().getAttribute("type") == null ? null : req.getSession().getAttribute("type").toString();
        String view = req.getParameter("view");
        ModelAndView mv = new ModelAndView();
        if (null == view) {
            mv.setViewName("err/404");
        } else {
            if (null == userId ) {
                mv.setViewName("err/verifyFail");
            }else {
                mv.setViewName(view);
                String data = DateUtils.getNowTime();
                mv.addObject("data", data);
                String registeredid = req.getParameter("registeredid");
                mv.addObject("registeredid",registeredid);
                List<Departments> departmentsAll= departmentsSV.queryAll();
                req.getSession().setAttribute("departmentsAll",departmentsAll);
            }
        }
        return mv;
    }

}


