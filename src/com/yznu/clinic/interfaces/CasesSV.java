package com.yznu.clinic.interfaces;

import com.yznu.clinic.beans.Cases;
import com.yznu.clinic.beans.Registered;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;

import java.util.List;
import java.util.Map;

public interface CasesSV {

    /*病例明细*/
    public PageList querycasePage(Query query);

    /*根据身份证号查询*/
    public List<Cases> querycaseByIdcard(String idcard);

    /*添加病例*/
    int addcase(Cases cases);

    /*更新*/
    int updateCase(Cases cases);

    /*删除*/
    public void deletecase(String id);

    public List<Cases> querytop(Map params);
}
