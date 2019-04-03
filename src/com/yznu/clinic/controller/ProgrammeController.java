package com.yznu.clinic.controller;

import com.yznu.clinic.beans.*;
import com.yznu.clinic.dao.ProgrammeMapper;
import com.yznu.clinic.interfaces.*;
import com.yznu.clinic.util.DateUtils;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ProgrammeController {
    @Autowired
    ProgrammeSV programmeSV;
    @Autowired
    CasesSV casesSV;
    @Autowired
    BillService billService;
    @Autowired
    MedicinesSV medicinesSV;
    @Autowired
    ProjectService projectService;
    /**
     * 收费员进入诊疗项目收费
     */
    @RequestMapping(value = "programme.do")
    @ResponseBody
    public PageList programme(Query query){
        String sort = (String) query.getParam().get("sort");
        String sortOrder = (String) query.getParam().get("sortOrder");
        String data = (String) query.getParam().get("data");
        String state = (String) query.getParam().get("state");
        String programmetype = (String) query.getParam().get("programmetype");
        query.getParam().put("sort", sort);
        query.getParam().put("sortOrder", sortOrder);
        query.getParam().put("data",data);
        query.getParam().put("state",state);
        query.getParam().put("programmetype",programmetype);
        PageList pageList = programmeSV.queryProgrammePage(query);
        return pageList;
    }

    /**
     * 更新诊疗项目状态为已经提交
     */
    @RequestMapping(value = "updateProgramme.do")
    @ResponseBody
    public Map updateProgramme(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        String programmestate = req.getParameter("state");
        if(programmestate.equals("1")){
            Programme p = programmeSV.queryprogrammeById(id);

            /*挂号员收费后 执行账单 添加*/
            SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
            int billnum = Integer.parseInt(sdf.format(new Date()).trim());
            int employee_id = (int) req.getSession().getAttribute("employee_id");
            int  employee_num = (int) req.getSession().getAttribute("employee_num");
            Bill bill = new Bill();
            bill.setBillMon(p.getProgrammeMony());
            bill.setBillNum(billnum);
            bill.setBillDate(DateUtils.getNowTimeByhhmmss());
            bill.setBillName("收费员收费");
            bill.setBillType("收费员收费");
            bill.setBillOption("收费员收费");
            bill.setBillState("0");
            bill.setBillRemarks(p.getPatientId()+"号挂号单缴费记录");
            bill.setEmployeeId(employee_id);
            bill.setEmployeeNum(employee_num);
            billService.insertBill(bill);
            /*账单添加完成后提交至药品管理员处申请药品出库*/
            Programme programme = new Programme();
            programme.setId(id);
            programme.setProgrammeState(programmestate);
            programmeSV.updateProgramme(programme);
        }else if(programmestate.equals("2")){
            Programme programme = new Programme();
            programme.setId(id);
            programme.setProgrammeState(programmestate);
            programmeSV.updateProgramme(programme);

            Programme p = programmeSV.queryprogrammeById(id);
            String a = p.getMedicineId();
            String b = p.getMedicineNum();
            if(null!=a&&!"".equals(a)){
                Medicines m = new Medicines();
                String[] ids = a.split(",");
                String[] mnum = b.split(",");
                for (int i =0;i<ids.length;i++) {
                    if(ids[i]!=""&&ids[i]!=null){
                        m.setId(Integer.parseInt(ids[i]));
                        m.setMedicinesNum(Integer.parseInt(mnum[i]));
                        medicinesSV.updatemedicines(m);
                    }
                }
            }

        }

        Map map = new HashMap();
        return map;
    }


    /**
     * 医生病历单书写时，让病人进行诊疗项目的检查添加
     */
    @RequestMapping(value = "insertprojects.do")
    @ResponseBody
    public Map insertProjects(HttpServletRequest req){
        String patientid = req.getParameter("patientid");
        String date = req.getParameter("date");
        int id =Integer.parseInt(req.getParameter("id"));
        Projects project = projectService.selectByPrimaryKey(id);

        Programme p =  new Programme();
        p.setProgrammeDate(date);
        p.setPatientId(patientid);
        p.setProgrammeProjects(project.getProjectsName());
        p.setProgrammeMony(project.getProjectMoney());
        p.setProgrammeState("0");
        programmeSV.insertSelective(p);
        int programmeId =p.getId();

        Map map = new HashMap();
        return map;
    }


    /**
     * 根据ID查询单个
     */
    @RequestMapping(value = "queryprogrammeById.do")
    @ResponseBody
    public Map queryprogrammeById(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        Programme list = programmeSV.queryprogrammeById(id);
        Map map = new HashMap();
        map.put("programme",list);
        return map;
    }
}
