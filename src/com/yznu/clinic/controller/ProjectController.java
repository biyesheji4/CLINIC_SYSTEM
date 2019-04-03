package com.yznu.clinic.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yznu.clinic.beans.Projects;
import com.yznu.clinic.interfaces.ProjectService;
import com.yznu.clinic.util.QueryPage;


@Controller
@RequestMapping("project")
public class ProjectController {
	@Autowired
	ProjectService projectService;

	//currPage参数表示显示第几页的数据
	private int CURRPAGE=0;
	//pageSize表示每页显示的数据条数
	private final int PAGESIZE=10;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

	@RequestMapping(value="/projectlist.do")
	public String projectlist(){
		return "project_list";
	}

	@RequestMapping(value="/projectupdate.do")
	public String projectupdate(){
		return "project_update";
	}

	@RequestMapping(value="/projectadd.do")
	public String projectadd(){
		return "project_add";
	}

	@RequestMapping(value="/getallproject.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getallproject(HttpSession session){
		List<Projects> data=projectService.getallproject();
		return JSON.toJSONString(data);
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
		long allnum=projectService.queryTotalsize(PAGESIZE,sta);
		totalsize=(int) Math.ceil((float)allnum/(float)PAGESIZE);
		List<Projects> data=projectService.queryProjectsBySql(CURRPAGE, PAGESIZE ,sta);
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
		long allnum=projectService.queryTotalsize(PAGESIZE,data1);

		totalsize=(int) Math.ceil((float)allnum/(float)PAGESIZE);
		List<Projects> data=projectService.querysomedata(data1);
		page.setCurrpage(CURRPAGE);
		page.setPagesize(PAGESIZE);
		page.setTotalsize(totalsize);
		page.setPagedata(data);
		page.setAllnum(allnum);
		return JSON.toJSONString(page);
	}

	@RequestMapping(value="/delproject.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delproject(@RequestBody Integer id,HttpSession session){
		int data=projectService.delproject(id);
		return JSON.toJSONString(data);
	}

	@RequestMapping(value="/delallproject.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delallproject(@RequestBody String idstr,HttpSession session){
		idstr=idstr.substring(1,idstr.length()-1);
		String[] idstrs=idstr.split(",");

		Integer[] allId=new Integer[idstrs.length];
		int i=0;
		for(String id:idstrs){
			allId[i]=Integer.parseInt(id);
			i++;
		}

		int data=projectService.delallproject(allId);
		return JSON.toJSONString(data);
	}

	@RequestMapping(value="/addproject.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addproject(@RequestBody Projects projects,HttpSession session){
		Date date=new Date();
		String time=sdf.format(date);
		projects.setProjectsDate(time);
		projects.setProjectsState("1");
		int data=projectService.addproject(projects);
		return JSON.toJSONString(data);
	}

	@RequestMapping(value="/loadingupdateproject.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String loadingupdateproject(@RequestBody Integer id,HttpSession session){
		HashMap<String, Object> data=new HashMap<String, Object>();
		Projects data2=projectService.loadupdateproject(id);
		List<HashMap<String, Object>> data1=projectService.queryusededepartment();
		data.put("data1", data1);
		data.put("data2", data2);
		return JSON.toJSONString(data);
	}

	@RequestMapping(value="/loadingaddproject.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String loadingaddproject(HttpSession session){
		HashMap<String, Object> data=new HashMap<String, Object>();
		List<HashMap<String, Object>> data1=projectService.queryusededepartment();
		data.put("data1", data1);
		return JSON.toJSONString(data);
	}

	@RequestMapping(value="/updateproject.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updateproject(@RequestBody Projects projects,HttpSession session){
		int data=projectService.updateproject(projects);
		return JSON.toJSONString(data);
	}


	/**
	 * 病历单诊疗项目查询
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "projectBypage.do")
	@ResponseBody
	public PageList queryprojectPage(Query query){
		String sort = (String) query.getParam().get("sort");
		String sortOrder = (String) query.getParam().get("sortOrder");
		String name = (String) query.getParam().get("name");

		query.getParam().put("sort", sort);
		query.getParam().put("sortOrder", sortOrder);
		query.getParam().put("name",name);
		PageList pageList = projectService.queryprojectPage(query);
		return pageList;
	}
}
