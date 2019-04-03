package com.yznu.clinic.controller;

import com.yznu.clinic.beans.Bill;
import com.yznu.clinic.beans.Medicines;
import com.yznu.clinic.beans.Registered;
import com.yznu.clinic.interfaces.BillService;
import com.yznu.clinic.interfaces.MedicinesSV;
import com.yznu.clinic.util.DateUtils;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.rmi.MarshalledObject;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MedicinesController {
    @Autowired
    MedicinesSV medicinesSV;
    @Autowired
    BillService billService;

    //medicine页面查询
    @RequestMapping(value = "medicines.do")
    @ResponseBody
    public PageList querymedicinesPage(Query query){
        String dimtype = (String) query.getParam().get("dimtype");
        String sort = (String) query.getParam().get("sort");
        String sortOrder = (String) query.getParam().get("sortOrder");
        String data = (String) query.getParam().get("data");
        String name = (String) query.getParam().get("name");
        query.getParam().put("sort", sort);
        query.getParam().put("sortOrder", sortOrder);
        query.getParam().put("data",data);
        query.getParam().put("name",name);
        if(dimtype=="dimmedicins"||("dimmedicins").equals(dimtype)){
           query.getParam().put("nowDate",DateUtils.getNowTime());
            PageList pageList = medicinesSV.querymedicinesPage(query);
            return pageList;
        }else {
            PageList pageList = medicinesSV.querymedicinesPage(query);
            return pageList;
        }

    }

    //根据id查询药品
    @RequestMapping(value = "querymedicinesByid.do")
    @ResponseBody
    public Map querymedicinesByid(HttpServletRequest req){
        String id = req.getParameter("id");
        Medicines list = medicinesSV.querymedicinesById(id);
        Map map = new HashMap();
        map.put("medicines",list);
        return map;
    }

    //修改药品类别
    @RequestMapping(value = "updatemedicinesType.do")
    @ResponseBody
    public Map updatemedicinesType(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        String type = req.getParameter("type");
        Medicines me = new Medicines();
        me.setId(id);
        me.setMedicinesType(type);
        medicinesSV.updatemedicines(me);
        Map map = new HashMap();
        return map;
    }

    //进货
    @RequestMapping(value = "addmedicines.do")
    @ResponseBody
    public Map addmedicines(HttpServletRequest req){
        String medicinesName = req.getParameter("medicinesName");
        String medicinesType = req.getParameter("medicinesType");
        int medicinesNum = Integer.parseInt(req.getParameter("medicinesNum"));
        int medicinesLimit = Integer.parseInt(req.getParameter("medicinesLimit"));
        Double medicinesMoney =Double.parseDouble(req.getParameter("medicinesMoney"));
        Double medicinesBid = Double.parseDouble(req.getParameter("medicinesBid"));
        String medicinesDate = req.getParameter("medicinesDate");
        String medicinesInputdate =DateUtils.getNowTime();
        Medicines medicines = new Medicines();
        medicines.setMedicinesName(medicinesName);medicines.setMedicinesType(medicinesType);
        medicines.setMedicinesNum(medicinesNum); medicines.setMedicinesLimit(medicinesLimit);
        medicines.setMedicinesMoney(medicinesMoney);medicines.setMedicinesBid(medicinesBid);
        medicines.setMedicinesDate(medicinesDate); medicines.setMedicinesInputdate(medicinesInputdate);
        medicinesSV.addmedicines(medicines);

         /*进库后 执行账单 添加*/
        Double bill_mon = Double.parseDouble(req.getParameter("bill_mon"));
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        int billnum = Integer.parseInt(sdf.format(new Date()).trim());
        int  employee_num = Integer.parseInt(req.getParameter("employee_num"));
        int employee_id = Integer.parseInt(req.getParameter("employee_id"));
        Bill bill = new Bill();
        bill.setBillMon(bill_mon);
        bill.setBillNum(billnum);
        bill.setBillDate(DateUtils.getNowTimeByhhmmss());
        bill.setBillName("药品进库支出");
        bill.setBillType("药品进库");
        bill.setBillOption("药品");
        bill.setBillState("1");
        bill.setBillRemarks(medicinesName+"进库记录");
        bill.setEmployeeId(employee_id);
        bill.setEmployeeNum(employee_num);
        billService.insertBill(bill);
        Map map = new HashMap();
        return  map;
    }

    /*库存页面 药品过期时间判断*/
    @RequestMapping(value = "Datetime.do")
    @ResponseBody
    public Map Datetime(HttpServletRequest request){
        String medicinesDate = request.getParameter("medicinesDate");
        String a = DateUtils.getThisMonthDate(medicinesDate);
        String b = DateUtils.getMonthNDate(6);
        String c = DateUtils.getNowTime();
        Map map = new HashMap();
        map.put("a",a);
        map.put("b",b);
        map.put("c",medicinesDate);
        map.put("d",c);
        return map;
    }


    /*对过期药品进行删除*/
    @RequestMapping(value = "deletemedicine.do")
    @ResponseBody
    public Map delete(HttpServletRequest req){
        String id = req.getParameter("id");
        Medicines list =medicinesSV.querymedicinesById(id);
        medicinesSV.deletemedicines(id);

        String deltype = req.getParameter("deltype");
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        int billnum = Integer.parseInt(sdf.format(new Date()).trim());
        HttpSession session = req.getSession();
        int employee_id = (int) session.getAttribute("employee_id");
        int employee_num = (int) session.getAttribute("employee_num");
        Bill bill = new Bill();
        if(deltype.equals("1")){
            /*删除后 执行账单 添加*/
            Double bill_mon = list.getMedicinesBid()*list.getMedicinesNum();
            bill.setBillMon(bill_mon);
            bill.setBillNum(billnum);
            bill.setBillDate(DateUtils.getNowTimeByhhmmss());
            bill.setBillName("药品过期损失支出");
            bill.setBillType("药品过期损失");
            bill.setBillOption("药品过期损失");
            bill.setBillState("1");
            bill.setBillRemarks(list.getMedicinesName()+"过期损失记录");
            bill.setEmployeeId(employee_id);
            bill.setEmployeeNum(employee_num);
            billService.insertBill(bill);
        }
       else if(deltype.equals("2")){
            Double bill_mon = list.getMedicinesBid()*list.getMedicinesNum();
            bill.setBillMon(bill_mon);
            bill.setBillNum(billnum);
            bill.setBillDate(DateUtils.getNowTimeByhhmmss());
            bill.setBillName("药品出库收入");
            bill.setBillType("药品出库");
            bill.setBillOption("药品");
            bill.setBillState("0");
            bill.setBillRemarks(list.getMedicinesName()+"出库记录");
            bill.setEmployeeId(employee_id);
            bill.setEmployeeNum(employee_num);
            billService.insertBill(bill);
        }
        Map<String, String> map = new HashMap<String, String>();
        return map;
    }


    /*单个药品出库，执行更新药品的库存数量*/
    @RequestMapping(value = "share.do")
    @ResponseBody
    public Map share(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        int  medicinesNum = Integer.parseInt(req.getParameter("medicinesNum"));

        Medicines me = new Medicines();
        me.setId(id);
        me.setMedicinesNum(medicinesNum);
        medicinesSV.updatemedicines(me);


        Map map = new HashMap();
        return map;
    }
}
