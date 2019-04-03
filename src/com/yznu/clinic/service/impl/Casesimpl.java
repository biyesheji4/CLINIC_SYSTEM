package com.yznu.clinic.service.impl;

import com.yznu.clinic.beans.Cases;
import com.yznu.clinic.dao.CasesMapper;
import com.yznu.clinic.interfaces.CasesSV;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Casesimpl implements CasesSV {

    @Autowired
    CasesMapper casesMapper;
    @Override
    public List<com.yznu.clinic.beans.Cases> querytop(Map params) {
        return casesMapper.querytop(params);
    }

    @Override
    public PageList querycasePage(Query query) {
        PageList pageList = new PageList();
        List rows = casesMapper.querycasePage(query);
        int total = casesMapper.getAllcaseCount(query);
        pageList.setRows(rows);
        pageList.setTotal(total);
        return pageList;
    }

    @Override
    public List<Cases> querycaseByIdcard(String idcard) {
        return casesMapper.querycaseByIdcard(idcard);
    }

    @Override
    public int addcase(Cases cases) {
        return casesMapper.addcase(cases);
    }

    @Override
    public int updateCase(Cases cases) {
        return casesMapper.updateCase(cases);
    }

    @Override
    public void deletecase(String id) {
        if(null!=id&&!"".equals(id)){
            String[] ids = id.split(",");
            for (String strid : ids) {
                casesMapper.deletecase(strid);
            }
        }
    }


}
