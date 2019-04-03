package com.yznu.clinic.dao;

import com.yznu.clinic.beans.*;
import com.yznu.clinic.util.Query;

import java.util.List;
import java.util.Map;

public interface RegisteredMapper {
    /*新增*/
    int addRegistered(Registered registered);

    /*更新*/
    int updateRegistered(Registered record);

    /*根据ID值单个挂号单查询详情*/
    public Registered queryregisteredById(String id);

    /*挂号页面根据身份证号查询*/
    public List<Registered> queryregisteredByIdard(String idcard);

    /*挂号单明细*/
    public List queryregisteredPage(Query query);

    /*挂号单明细count*/
    public int getAllregisteredCount(Query query);

    /*删除*/
    public void deleteregistered(String id);


    public List<Registered> tuxing(Map params);
}