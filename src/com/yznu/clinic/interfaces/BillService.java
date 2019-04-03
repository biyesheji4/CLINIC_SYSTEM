package com.yznu.clinic.interfaces;

import java.util.List;
import java.util.Map;

import com.yznu.clinic.beans.Bill;
import com.yznu.clinic.beans.Projects;

public interface BillService {

	//获取所有记录
	List<Bill> getallBill();
	//查询当前查询记录总条数
	int queryTotalsize(int pAGESIZE,Map<String, Object> data1);

	//List<Bill> queryProjectsBySql(int cURRPAGE, int pAGESIZE, String sta);
	//分页查询&条件查询
	List<Bill> querysomedata(Map<String, Object> data1);
	//删除一条数据
	int delbill(Integer id);
	//批量删除
	int delallbill(Integer[] allId);
	//加载修改信息
	Bill loadupdatebill(Integer id);
	//根据id修改
	int updatebill(Bill bill);
	//新增记录
	int addbill(Bill bill);


	/*统计收入、支出*/
	public List<Bill> statistics(Map params);
	int insertBill(Bill bill);

	public List<Bill> querybillbyall(Map params);
}
