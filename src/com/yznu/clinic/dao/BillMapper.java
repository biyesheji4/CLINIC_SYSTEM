package com.yznu.clinic.dao;

import java.util.List;
import java.util.Map;

import com.yznu.clinic.beans.Bill;
import com.yznu.clinic.beans.Cases;
import com.yznu.clinic.beans.Projects;

public interface BillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);

    List<Bill> getAllBill();

    int queryTotalsize(Map<String, Object> data);

    List<Bill> querysomedata(Map<String, Object> data);



    /*统计收入、支出*/
    public List<Bill> statistics(Map params);
    int insertBill(Bill bill);

    public List<Bill> querybillbyall(Map params);
}