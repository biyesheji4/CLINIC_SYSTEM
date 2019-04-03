package com.yznu.clinic.interfaces;

import com.yznu.clinic.beans.Registered;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;

import java.util.List;
import java.util.Map;

public interface RegisteredSV {
    /*新增*/
    int addRegistered(Registered registered);

    /*更新*/
    int updateRegistered(Registered record);

    /*单个挂号单详情*/
    public Registered queryregisteredById(String id);

    /*挂号页面根据身份证号查询*/
    public List<Registered> queryregisteredByIdard(String idcard);

    /*挂号单明细*/
    public PageList queryregisteredPage(Query query);


    //记录条数
    public int queryregisteredCount(Query query);

    /*删除*/
    public void deleteregistered(String id);


    public List<Registered> tuxing(Map params);
}