package com.yznu.clinic.controller;

import com.yznu.clinic.beans.*;
import com.yznu.clinic.interfaces.*;
import com.yznu.clinic.util.DateUtils;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RegisteredController {
    @Autowired
    DepartmentsSV departmentsSV;
    @Autowired
    RegisteredSV registeredSV;
    @Autowired
    BillService billService;
    @Autowired
    CasesSV casesSV;
    @Autowired
    ProgrammeSV programmeSV;
    /**
     *registered.js查询挂号单信息
    */
    @RequestMapping(value ="registered.do")
    @ResponseBody
    public PageList queryregisteredPage(Query query) {
        String sort = (String) query.getParam().get("sort");
        String sortOrder = (String) query.getParam().get("sortOrder");
        String textarea = (String) query.getParam().get("textarea");
        String data = (String) query.getParam().get("data");


        query.getParam().put("sort", sort);
        query.getParam().put("sortOrder", sortOrder);
        query.getParam().put("textarea",textarea);
        query.getParam().put("data",data);
        PageList pageList = registeredSV.queryregisteredPage(query);
        return pageList;
    }

    /**
    查看挂号详情
     */
    @RequestMapping(value = "query-registered.do")
    @ResponseBody
    public ModelAndView queryregistered(HttpServletRequest req){
        String id = req.getParameter("id");
        Registered list = registeredSV.queryregisteredById(id);
        ModelAndView mav = new ModelAndView("query-registered");
        req.getSession().setAttribute("registered", list);
        return mav;
    }

    /**
     *根据挂号单ID号查询
     */
    @RequestMapping(value = "query-registeredone.do")
    @ResponseBody
    public Map queryregisteredone(HttpServletRequest req){
        String id = req.getParameter("patientid");
        Registered list = registeredSV.queryregisteredById(id);
        String date = DateUtils.getNowTime();
        Map map = new HashMap();
        map.put("registered",list);
        map.put("date",date);
        return map;
    }

    /**
     *根据身份证号查询
     */
    @RequestMapping(value = "query-registeredByIdcard.do")
    @ResponseBody
    public Map queryregisteredByIdcard(HttpServletRequest req){
        String patientIdcard = req.getParameter("patientIdcard");
        List<Registered> list = registeredSV.queryregisteredByIdard(patientIdcard);
        Map map = new HashMap();
        map.put("registered",list);
        return map;
    }

/**
进入更新模态框
 */
@RequestMapping(value = "input-update.do")
@ResponseBody
public Map inputUpdate(HttpServletRequest req){
    String id = req.getParameter("id");
    Registered list = registeredSV.queryregisteredById(id);
    List<Departments> departmentsAll= departmentsSV.queryAll();
    Map map = new HashMap();
    if(list.getDepartmentsId()!=null){
        List<Departments> onedepartment= departmentsSV.query(list.getDepartmentsId());
        map.put("one",onedepartment);
    }
    map.put("registereds",list);
    map.put("departmentsAll",departmentsAll);

    return map;
}

/**
执行更新
*/
 @RequestMapping(value = "updateRegistered.do")
 @ResponseBody
public Map updateRegistered(HttpServletRequest req){
     int id = Integer.parseInt(req.getParameter("id"));
     String patientName = req.getParameter("patientName");
     int patientAge = Integer.parseInt(req.getParameter("patientAge"));
     String patientTel = req.getParameter("patientTel");
     String registeredContent = req.getParameter("registeredContent");
     String departmentsId = req.getParameter("departmentsName");
     String patientSex = req.getParameter("patientSex");
    String patientIdcard = req.getParameter("patientIdcard");

         Registered re = new Registered();
         re.setId(id);re.setPatientName(patientName);re.setPatientSex(patientSex);
         re.setPatientAge(patientAge);re.setPatientTel(patientTel);
         re.setRegisteredContent(registeredContent);re.setPatientIdcard(patientIdcard);
         re.setDepartmentsId(departmentsId);
         registeredSV.updateRegistered(re);

     Map map = new HashMap();
     return map;
 }

    /**
      新增提交
       */
    @RequestMapping(value = "addRegistered.do")
    @ResponseBody
    public Map addRegistered(HttpServletRequest req, HttpSession  session){
        String patientIdcard = req.getParameter("patientIdcard");
        String patientName = req.getParameter("patientName");
        int patientAge = Integer.parseInt(req.getParameter("patientAge"));
        String patientTel = req.getParameter("patientTel");
        String registeredContent = req.getParameter("registeredContent");
        String registeredMoney = "3";
        String departmentsId = req.getParameter("departmentsName");
        String patientSex = req.getParameter("patientSex");
        String registeredState = "1";
        String registeredDate = DateUtils.getNowTime();

        Registered re = new Registered();
        re.setPatientIdcard(patientIdcard);
        re.setPatientName(patientName);re.setPatientSex(patientSex);
        re.setPatientAge(patientAge);re.setPatientTel(patientTel);re.setRegisteredMoney(registeredMoney);
        re.setRegisteredContent(registeredContent);
        re.setRegisteredDate(registeredDate);re.setDepartmentsId(departmentsId);re.setRegisteredState(registeredState);
        registeredSV.addRegistered(re);

        int id = re.getId();

        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        int billnum = Integer.parseInt(sdf.format(new Date()).trim());
        int employee_id = (int) req.getSession().getAttribute("employee_id");
        Bill bill = new Bill();
        bill.setBillMon(Double.parseDouble(registeredMoney));
        bill.setBillNum(billnum);
        bill.setBillDate(DateUtils.getNowTimeByhhmmss());
        bill.setBillName("挂号单收入");
        bill.setBillType("挂号单");
        bill.setBillOption("挂号");
        bill.setBillState("0");
        bill.setBillRemarks(patientName+"挂号单");
        bill.setEmployeeId(employee_id);
        billService.insertBill(bill);


        Map map = new HashMap();
        map.put("id",id);
        return  map;
    }


    /**
    registered.jsp页面删除按钮
     */
    @RequestMapping(value = "deleteregistered.do")
    @ResponseBody
    public Map delete(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        String state = "0";
        Registered r = new Registered();
        r.setId(id);r.setRegisteredState(state);
        registeredSV.updateRegistered(r);
        Map<String, String> map = new HashMap<String, String>();
        return map;
    }


    /**
     * 更新检查病人的检查结果
     */
    @RequestMapping(value = "updateproject.do")
    @ResponseBody
    public Map updateproject(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        String pid = req.getParameter("id");
        String project = req.getParameter("programme");
        int  programmeid = Integer.parseInt(req.getParameter("programmeid"));

        /*为挂号单新增检查结果*/
        Registered list = registeredSV.queryregisteredById(pid);
        Registered re = new Registered();
        re.setId(id);
        if(list.getProjectResult()!=null){
            re.setProjectResult(list.getProjectResult() +"。"+project);
        }else {
            re.setProjectResult(project);
        }
        registeredSV.updateRegistered(re);

        /*更新诊疗项目为已经诊疗*/
        Programme programme = new Programme();
        programme.setId(programmeid);
        programme.setProgrammeState("2");
        programmeSV.updateProgramme(programme);
        Map map = new HashMap();
        return map;
    }


    /**
     * 统计页面
     * @param req
     * @return
     */
    @RequestMapping(value = "tuxing.do", method = RequestMethod.POST)
    @ResponseBody
    public Map tuxing(HttpServletRequest req){
        String  registeredDate  = req.getParameter("yeartime");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        System.out.println(endDate);
        System.out.println(startDate);
        Map par = new HashMap();
        par.put("registeredDate",registeredDate);
        par.put("endDate",endDate);
        par.put("startDate",startDate);
        //挂号人数分析图数据
        List<Registered> list = registeredSV.tuxing(par);
        //医生诊疗人数排名数据
        List<Cases> cases = casesSV.querytop(par);
        //收入、支出统计
        List<Bill> bill = billService.statistics(par);

        Map map = new HashMap();
        map.put("lw",list);
        map.put("cas",cases);
        map.put("bill",bill);
       return map;
    }
}


