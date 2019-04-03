package com.yznu.clinic.dao;

import com.yznu.clinic.beans.*;
import com.yznu.clinic.util.Query;

import java.util.List;
import java.util.Map;

public interface CasesMapper {

    /*病例明细*/
    public List querycasePage(Query query);

    /*病例明细count*/
    public int getAllcaseCount(Query query);


    /*根据身份证号查询*/
    public List<Cases> querycaseByIdcard(String idcard);

    /*添加病例*/
    int addcase(Cases cases);

    /*更新*/
    int updateCase(Cases cases);

    /*删除*/
    public void deletecase(String id);

    /*医生诊疗人数排名*/
    public List<Cases> querytop(Map params);
}