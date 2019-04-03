package com.yznu.clinic.controller;

import com.alibaba.fastjson.JSON;
import com.yznu.clinic.beans.Departments;
import com.yznu.clinic.beans.Projects;
import com.yznu.clinic.interfaces.DepartmentsSV;
import com.yznu.clinic.interfaces.RegisteredSV;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;
import com.yznu.clinic.util.QueryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("departments")
public class DepartmentController {
    @Autowired
    DepartmentsSV departmentsSV;
    @Autowired
    RegisteredSV registeredSV;

    //currPage参数表示显示第几页的数据
    private int CURRPAGE=0;
    //pageSize表示每页显示的数据条数
    private final int PAGESIZE=10;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    @RequestMapping(value="/departmentslist.do")
    public String departmentslist(){
        return "departments_list";
    }

    @RequestMapping(value="/departmentsupdate.do")
    public String departmentsupdate(){
        return "departments_update";
    }

    @RequestMapping(value="/departmentsadd.do")
    public String departmentsadd(){
        return "departments_add";
    }

/*	@RequestMapping(value="/getpageproject.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getpageproject(@RequestBody Integer currpage,HttpServletRequest request){
		QueryPage page=new QueryPage();
		String state=request.getParameter("state");
		String sta="0";
		if("stop".equals(state)){
			sta="0";
		}else if("use".equals(state)){
			sta="1";
		}
		int totalsize=0;
		CURRPAGE=currpage;
		long allnum=departmentsService.queryTotalsize(PAGESIZE,sta);
		totalsize=(int) Math.ceil((float)allnum/(float)PAGESIZE);
		List<Projects> data=departmentsService.queryProjectsBySql(CURRPAGE, PAGESIZE ,sta);
		page.setCurrpage(CURRPAGE);
		page.setPagesize(PAGESIZE);
		page.setTotalsize(totalsize);
		page.setPagedata(data);
		page.setAllnum(allnum);
		return JSON.toJSONString(page);
	}*/

    @RequestMapping(value="/querysomedata.action", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String querysomedata(HttpServletRequest request){
        QueryPage page=new QueryPage();
        Map<String, Object> data1=new HashMap<String, Object>();
        CURRPAGE=Integer.parseInt(request.getParameter("currpage"));
        data1.put("date1", request.getParameter("date1"));
        data1.put("date2", request.getParameter("date2"));
        data1.put("searchindex", request.getParameter("searchindex"));
        data1.put("currpage",CURRPAGE);
        data1.put("delflag", request.getParameter("delflag"));
        data1.put("pageSize", PAGESIZE);
        data1.put("currIndex", (CURRPAGE-1)*PAGESIZE);
        int totalsize=0;
        long allnum=departmentsSV.queryTotalsize(PAGESIZE,data1);

        totalsize=(int) Math.ceil((float)allnum/(float)PAGESIZE);
        List<Projects> data=departmentsSV.querysomedata(data1);
        page.setCurrpage(CURRPAGE);
        page.setPagesize(PAGESIZE);
        page.setTotalsize(totalsize);
        page.setPagedata(data);
        page.setAllnum(allnum);
        return JSON.toJSONString(page);
    }

    @RequestMapping(value="/getalldepartments.action", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getalldepartments(HttpSession session){
        List<Departments> data=departmentsSV.getalldepartments();
        return JSON.toJSONString(data);
    }

    @RequestMapping(value="/deldepartments.action", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deldepartments(@RequestBody Integer id,HttpSession session){
        int data=departmentsSV.deldepartments(id);
        return JSON.toJSONString(data);
    }

    @RequestMapping(value="/delalldepartments.action", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delalldepartments(@RequestBody String idstr,HttpSession session){
        idstr=idstr.substring(1,idstr.length()-1);
        String[] idstrs=idstr.split(",");

        Integer[] allId=new Integer[idstrs.length];
        int i=0;
        for(String id:idstrs){
            allId[i]=Integer.parseInt(id);
            i++;
        }

        int data=departmentsSV.delalldepartments(allId);
        return JSON.toJSONString(data);
    }

    @RequestMapping(value="/adddepartments.action", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String adddepartments(@RequestBody Departments departments,HttpSession session){
        Date date=new Date();
        String time=sdf.format(date);
        departments.setDepartmentsDate(time);
        departments.setDepartmentsState("1");
        int data=departmentsSV.adddepartments(departments);
        return JSON.toJSONString(data);
    }

    @RequestMapping(value="/loadingupdatedepartments.action", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String loadingupdatedepartments(@RequestBody Integer id,HttpSession session){
        Departments data=departmentsSV.loadupdatedepartments(id);
        return JSON.toJSONString(data);
    }

    @RequestMapping(value="/updatedepartments.action", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updatedepartments(@RequestBody Departments departments,HttpSession session){
        int data=departmentsSV.updatedepartments(departments);
        return JSON.toJSONString(data);
    }







    /*
     * 窗口查询查询部门信息
     */
    @RequestMapping(value = "departmentId.do")
    @ResponseBody
    public PageList getdepartmentsid(Query query) {
        String sort = (String) query.getParam().get("sort");
        String sortOrder = (String) query.getParam().get("sortOrder");
        String departmentsName = (String) query.getParam().get("departmentsName");
        query.getParam().put("sort", sort);
        query.getParam().put("sortOrder", sortOrder);
        query.getParam().put("departmentsName", departmentsName);
        PageList pageList = departmentsSV.getdepartmentID(query);
        return pageList;
    }


    /*
    根据ID查询部门信息
     */
    @RequestMapping(value = "getdepartmentbyId")
    @ResponseBody
    public List<Departments> getdepartmentbyId(HttpServletRequest req) {
        String id = req.getParameter("id");
        List<Departments> list = departmentsSV.query(id);
        return list;
    }

/*
查询所有科室
 */
@RequestMapping(value = "getdepartmentAll.do")
@ResponseBody
public Map getdepartmentAll(HttpServletRequest req){
    List<Departments> departmentsAll= departmentsSV.queryAll();
    Map map = new HashMap();
    map.put("departmentsAll",departmentsAll);
    return map;
}


    /*
    index.jsp测试用
     */
    @RequestMapping(value = "departement.do")
    @ResponseBody
    public PageList querydepartmentsPage(Query query) {
        String sort = (String) query.getParam().get("sort");
        String sortOrder = (String) query.getParam().get("sortOrder");
        query.getParam().put("sort", sort);
        query.getParam().put("sortOrder", sortOrder);
        PageList pageList = departmentsSV.querydepartmentsPage(query);
        return pageList;
    }
}
