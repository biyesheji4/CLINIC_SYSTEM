package com.yznu.clinic.interfaces;

import java.util.List;
import java.util.Map;

import com.yznu.clinic.beans.Equipment;
import com.yznu.clinic.beans.Projects;

public interface EquipmentService {

	//获取所有记录
	List<Equipment> getallequipment();
	//删除一条数据
	int delequipment(Integer id);
	//批量删除
	int delallequipment(Integer[] allId);
	//新增记录
	int addequipment(Equipment equipment);
	//加载修改信息
	Equipment loadupdateequipment(Integer id);
	//根据id修改
	int updateequipment(Equipment equipment);
	//查询当前查询记录总条数
	int queryTotalsize(int pAGESIZE,Map<String, Object> data1);

	//List<Projects> queryProjectsBySql(int cURRPAGE, int pAGESIZE, String sta);
	//分页查询&条件查询
	List<Projects> querysomedata(Map<String, Object> data1);


}
