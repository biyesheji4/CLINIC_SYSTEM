package com.yznu.clinic.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yznu.clinic.beans.Bill;
import com.yznu.clinic.beans.Equipment;
import com.yznu.clinic.beans.Projects;
import com.yznu.clinic.interfaces.EquipmentService;
import com.yznu.clinic.util.QueryPage;

@Controller
@RequestMapping("equipment")
public class EquipmentController {
	@Autowired
	EquipmentService equipmentService;

	//currPage参数表示显示第几页的数据
	private int CURRPAGE=0;
	//pageSize表示每页显示的数据条数
	private final int PAGESIZE=10;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	
	@RequestMapping(value="/equipmentlist.do")
	public String equipmentlist(){
		return "equipment_list";
	}
	
	@RequestMapping(value="/equipmentupdate.do")
	public String equipmentupdate(){
		return "equipment_update";
	}
	
	@RequestMapping(value="/equipmentadd.do")
	public String equipmentadd(){
		return "equipment_add";
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
		long allnum=equipmentService.queryTotalsize(PAGESIZE,sta);
		totalsize=(int) Math.ceil((float)allnum/(float)PAGESIZE);
		List<Projects> data=equipmentService.queryProjectsBySql(CURRPAGE, PAGESIZE ,sta);
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
		long allnum=equipmentService.queryTotalsize(PAGESIZE,data1);
		
		totalsize=(int) Math.ceil((float)allnum/(float)PAGESIZE);
		List<Projects> data=equipmentService.querysomedata(data1);
		page.setCurrpage(CURRPAGE);
		page.setPagesize(PAGESIZE);
		page.setTotalsize(totalsize);
		page.setPagedata(data);
		page.setAllnum(allnum);
		return JSON.toJSONString(page);
	}
	
	@RequestMapping(value="/getallequipment.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getallequipment(HttpSession session){
		List<Equipment> data=equipmentService.getallequipment();
		return JSON.toJSONString(data);
	}
	
	@RequestMapping(value="/delequipment.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delequipment(@RequestBody Integer id,HttpSession session){
		int data=equipmentService.delequipment(id);
		return JSON.toJSONString(data);
	}
	
	@RequestMapping(value="/delallequipment.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delallequipment(@RequestBody String idstr,HttpSession session){
		idstr=idstr.substring(1,idstr.length()-1);
		String[] idstrs=idstr.split(",");
		
		Integer[] allId=new Integer[idstrs.length];
		int i=0;
		for(String id:idstrs){
			allId[i]=Integer.parseInt(id);
			i++;
		}
		
		int data=equipmentService.delallequipment(allId);
		return JSON.toJSONString(data);
	}
	
	@RequestMapping(value="/addequipment.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addequipment(@RequestBody Equipment equipment,HttpSession session){
		Date date=new Date();
		String time=sdf.format(date);
		equipment.setEquipmentDate(time);
		equipment.setEquipmentState("1");
		int data=equipmentService.addequipment(equipment);
		return JSON.toJSONString(data);
	}
	
	@RequestMapping(value="/loadingupdateequipment.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String loadingupdateequipment(@RequestBody Integer id,HttpSession session){
		Equipment data=equipmentService.loadupdateequipment(id);
		return JSON.toJSONString(data);
	}
	
	@RequestMapping(value="/updateequipment.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updateequipment(@RequestBody Equipment equipment,HttpSession session){
		int data=equipmentService.updateequipment(equipment);
		return JSON.toJSONString(data);
	}
}
