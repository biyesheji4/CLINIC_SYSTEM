package com.yznu.clinic.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yznu.clinic.beans.Equipment;
import com.yznu.clinic.beans.Projects;
import com.yznu.clinic.dao.EquipmentMapper;
import com.yznu.clinic.interfaces.EquipmentService;

@Service
public class EquipmentServiceImpl implements EquipmentService{
	@Autowired
	EquipmentMapper equipmentMapper;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

	//获取所有记录
	public List<Equipment> getallequipment() {
		// TODO Auto-generated method stub
		List<Equipment> data=equipmentMapper.selectAll();
		return data;
	}

	//删除一条数据
	public int delequipment(Integer id) {
		// TODO Auto-generated method stub
		Equipment equipment=new Equipment();
		equipment.setId(id);
		equipment.setEquipmentState("0");
		int data=equipmentMapper.updateByPrimaryKeySelective(equipment);
		return data;
	}

	//批量删除
	public int delallequipment(Integer[] allId) {
		// TODO Auto-generated method stub
		int data=0;
		Equipment equipment=new Equipment();
		for(int id:allId){
			equipment.setId(id);
			equipment.setEquipmentState("0");
			data=equipmentMapper.updateByPrimaryKeySelective(equipment);
		}
		return data;
	}

	//新增记录
	public int addequipment(Equipment equipment) {
		// TODO Auto-generated method stub
		int data=equipmentMapper.insertSelective(equipment);
		return data;
	}

	//加载修改信息
	public Equipment loadupdateequipment(Integer id) {
		// TODO Auto-generated method stub
		Equipment data=equipmentMapper.selectByPrimaryKey(id);
		return data;
	}

	//根据id修改
	public int updateequipment(Equipment equipment) {
		// TODO Auto-generated method stub
		int data=equipmentMapper.updateByPrimaryKeySelective(equipment);
		return data;
	}


	/*public List<Projects> queryProjectsBySql(int currPage, int pageSize, String sta) {
		// TODO Auto-generated method stub
		 Map<String, Object> data = new HashedMap();
	        data.put("currIndex", (currPage-1)*pageSize);
	        data.put("pageSize", pageSize);
	        data.put("state", sta);
	        return equipmentMapper.queryProjectsBySql(data);

	}*/
	//查询当前查询记录总条数
	public int queryTotalsize(int pagesize,Map<String, Object> data) {
		// TODO Auto-generated method stub
		String date1=(String) data.get("date1");
		String date2=(String) data.get("date2");
		String searchindex=(String) data.get("searchindex");
		if(date1==null||date1==""){
			date1="0000-00-00 00:00:00";
		}else{
			date1=date1+" 00:00:00";
		}
		if(date2==null||date2==""){
			Date date=new Date();
			date2=sdf.format(date);
		}else{
			date2=date2+" 00:00:00";
		}
		if(searchindex==null||searchindex==""){
			searchindex="%%";
		}else{
			searchindex="%"+searchindex+"%";
		}

		data.put("date1", date1);
		data.put("date2", date2);
		data.put("searchindex", searchindex);
		return equipmentMapper.queryTotalsize(data);
	}

	//分页查询&条件查询
	public List<Projects> querysomedata(Map<String, Object> data) {
		// TODO Auto-generated method stub
		String date1=(String) data.get("date1");
		String date2=(String) data.get("date2");
		String searchindex=(String) data.get("searchindex");
		if(date1==null||date1==""){
			date1="0000-00-00 00:00:00";
		}else{
			date1=date1+" 00:00:00";
		}
		if(date2==null||date2==""){
			Date date=new Date();
			date2=sdf.format(date);
		}else{
			date2=date2+" 00:00:00";
		}
		if(searchindex==null||searchindex==""){
			searchindex="%%";
		}else{
			searchindex="%"+searchindex+"%";
		}

		data.put("date1", date1);
		data.put("date2", date2);
		data.put("searchindex", searchindex);

		List<Projects> data1=equipmentMapper.querysomedata(data);

		return data1;
	}

}
