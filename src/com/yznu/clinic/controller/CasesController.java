package com.yznu.clinic.controller;

import com.yznu.clinic.beans.Cases;
import com.yznu.clinic.beans.Programme;
import com.yznu.clinic.beans.Registered;
import com.yznu.clinic.interfaces.CasesSV;
import com.yznu.clinic.interfaces.ProgrammeSV;
import com.yznu.clinic.interfaces.RegisteredSV;
import com.yznu.clinic.util.DateUtils;
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
public class CasesController {
    @Autowired
    CasesSV casesSV;
    @Autowired
    RegisteredSV registeredSV;
    @Autowired
    ProgrammeSV programmeSV;

    /**
    *进入病例管理
    * */
    @RequestMapping(value = "CaseByPage.do")
    @ResponseBody
    public PageList CaseByPage(Query query){
        String sort = (String) query.getParam().get("sort");
        String sortOrder = (String) query.getParam().get("sortOrder");
        String data = (String) query.getParam().get("data");
        query.getParam().put("sort", sort);
        query.getParam().put("sortOrder", sortOrder);
        query.getParam().put("data",data);
        PageList pageList = casesSV.querycasePage(query);
        return pageList;
    }




    /**
        进入病历单页面
    */
    @RequestMapping(value = "openbingli.do")
    @ResponseBody
    public ModelAndView openbingli(HttpServletRequest req){
        String id = req.getParameter("id");
        Registered list = registeredSV.queryregisteredById(id);
//        List<Cases> listcase = casesSV.querycaseByIdcard(list.getPatientIdcard());
        String date = DateUtils.getNowTime();
        ModelAndView mav = new ModelAndView("case-write");
        mav.addObject("date",date);
        req.getSession().setAttribute("registered", list);
//        req.getSession().setAttribute("content", listcase);
        return mav;
    }

    /**
    进入历史病例
     */
    @RequestMapping(value = "beforbingli.do")
    @ResponseBody
    public Map beforbingli(HttpServletRequest req){
        String id = req.getParameter("id");
        Registered list = registeredSV.queryregisteredById(id);
        List<Cases> listcase = casesSV.querycaseByIdcard(list.getPatientIdcard());
        Map map = new HashMap();
        map.put("bingli",listcase);
        return map;
    }

    /**
    添加病例
     */
    @RequestMapping(value = "addcase.do")
    @ResponseBody
    public Map addcase(HttpServletRequest req){
        String patientid = req.getParameter("patientid");
        String date = req.getParameter("date");
        String content = req.getParameter("content");
        String programmemedicine = req.getParameter("programme");
        String employeeid = req.getParameter("employeeid");
        String state = "1";
        Double mon = Double.parseDouble(req.getParameter("money"));
        String mid = req.getParameter("mid");
        String mnum = req.getParameter("mnum");

        //添加 诊疗项目进诊疗方案过渡表
        Programme p =  new Programme();
        p.setProgrammeMedicine(programmemedicine);
        p.setProgrammeDate(date);
        p.setPatientId(patientid);
        p.setProgrammeMony(mon);
        p.setProgrammeState("0");
        p.setMedicineId(mid);
        p.setMedicineNum(mnum);
        programmeSV.insertSelective(p);
        int programmeId =p.getId();

        /*将信息填入病例表*/
        Cases cases = new Cases();
        cases.setCasesDate(date);
        cases.setCasesState(state);
        cases.setCasesContent(content);
        cases.setDoctorId(employeeid);
        cases.setCasesProgramme(programmeId);
        cases.setPatientId(patientid);
        casesSV.addcase(cases);
        int caseId = cases.getId();

        /*病例提交后 更新挂号单状态*/
        int id = Integer.parseInt(req.getParameter("patientid"));
        Registered r = new Registered();
        r.setId(id);
        r.setRegisteredState("2");
        registeredSV.updateRegistered(r);


        Map map = new HashMap();
        map.put("programmeId",programmeId);
        map.put("caseId",caseId);
        return map;
    }


    /**
     * 删除病例
     */
    @RequestMapping(value = "deletecase.do")
    @ResponseBody
    public Map deletecase(HttpServletRequest req){
        int  id = Integer.parseInt(req.getParameter("id"));
        String state = "0";
        Cases c = new Cases();
        c.setId(id);c.setCasesState(state);
        casesSV.updateCase(c);
        Map map = new HashMap();
        return map;
    }

    /**
    * 诊疗项目撤回
    */
    @RequestMapping(value = "recall.do")
    @ResponseBody
    public Map recall(HttpServletRequest req){
        Map map = new HashMap();
       String caseId = req.getParameter("caseId");
       String programmeId = req.getParameter("programmeId");
        Programme list =  programmeSV.queryprogrammeById(Integer.parseInt(programmeId));
        System.out.println(list.getProgrammeState());
       if(list.getProgrammeState().equals("0")){
           casesSV.deletecase(caseId);
           programmeSV.deleteprogramme(programmeId);

           /*病例撤回 更新挂号单状态*/
           int id = Integer.parseInt(req.getParameter("registeredid"));
           Registered r = new Registered();
           r.setId(id);
           r.setRegisteredState("1");
           registeredSV.updateRegistered(r);
           map.put("msg","撤回成功！");
       }else {
           map.put("msg","撤回失败！");
       }
        return map;
    }






//    @RequestMapping(value = "querytop.do")
//    @ResponseBody
//    public Map querytop(){
//        Map map = new HashMap();
//        List<Cases> list = casesSV.querytop();
//        map.put("cas",list);
//        return map;
//    }
}
