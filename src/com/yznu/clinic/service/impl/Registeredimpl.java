package com.yznu.clinic.service.impl;

import com.yznu.clinic.beans.Registered;
import com.yznu.clinic.dao.RegisteredMapper;
import com.yznu.clinic.interfaces.RegisteredSV;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Registeredimpl implements RegisteredSV {
    @Autowired
    RegisteredMapper registeredMapper;

    /*添加*/
    @Override
    public int addRegistered(Registered registered) {
        return registeredMapper.addRegistered(registered);
    }

    /*更新*/
    @Override
    public int updateRegistered(Registered record) {
        return registeredMapper.updateRegistered(record);
    }

    /*单个挂号单详情*/
    @Override
    public Registered queryregisteredById(String id) {
        return registeredMapper.queryregisteredById(id);
    }

    /*挂号页面根据身份证号查询*/
    @Override
    public List<Registered> queryregisteredByIdard(String idcard) {
        return registeredMapper.queryregisteredByIdard(idcard);
    }


    /*挂号单明细(分页)*/
    @Override
    public PageList queryregisteredPage(Query query) {
        PageList pageList = new PageList();
        List rows = registeredMapper.queryregisteredPage(query);
        int total = registeredMapper.getAllregisteredCount(query);
        pageList.setRows(rows);
        pageList.setTotal(total);
        return pageList;
    }

    /*挂号单明细(分页)*/
    @Override
    public int queryregisteredCount(Query query) {
        int total = registeredMapper.getAllregisteredCount(query);

        return total;
    }

    /*删除*/
    @Override
    public void deleteregistered(String id) {
        if(null!=id&&!"".equals(id)){
            String[] ids = id.split(",");
            for (String strid : ids) {
                registeredMapper.deleteregistered(strid);
            }
        }

    }

    @Override
    public List<Registered> tuxing(Map params) {
        List<Registered> list = registeredMapper.tuxing(params);
        return list;
    }


}
