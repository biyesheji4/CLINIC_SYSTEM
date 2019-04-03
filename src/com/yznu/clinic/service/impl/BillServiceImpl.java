package com.yznu.clinic.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yznu.clinic.beans.Bill;
import com.yznu.clinic.beans.Projects;
import com.yznu.clinic.dao.BillMapper;
import com.yznu.clinic.interfaces.BillService;

@Service
public class BillServiceImpl implements BillService{
	@Autowired
	BillMapper billMapper;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

	//获取所有记录
	public List<Bill> getallBill() {
		// TODO Auto-generated method stub
		List<Bill> bill=billMapper.getAllBill();
		return bill;
	}

	/*public List<Bill> queryProjectsBySql(int currPage, int pageSize, String sta) {
		// TODO Auto-generated method stub
		 Map<String, Object> data = new HashedMap();
	        data.put("currIndex", (currPage-1)*pageSize);
	        data.put("pageSize", pageSize);
	        data.put("state", sta);
	        return billMapper.queryProjectsBySql(data);

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
		return billMapper.queryTotalsize(data);
	}

	//分页查询&条件查询
	public List<Bill> querysomedata(Map<String, Object> data) {
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

		List<Bill> data1=billMapper.querysomedata(data);

		return data1;
	}

	//删除一条数据
	public int delbill(Integer id) {
		// TODO Auto-generated method stub
		int data=0;
		Bill bill=billMapper.selectByPrimaryKey(id);
		if(bill.getBillState().equals("0")){
			bill.setBillState("-1");
			data=billMapper.updateByPrimaryKeySelective(bill);
		}else if(bill.getBillState().equals("1")){
			bill.setBillState("-2");
			data=billMapper.updateByPrimaryKeySelective(bill);
		}
		return data;
	}

	//批量删除
	public int delallbill(Integer[] allId) {
		// TODO Auto-generated method stub
		int data=0;
		for(int id:allId){
			Bill bill=billMapper.selectByPrimaryKey(id);
			if(bill.getBillState().equals("0")){
				bill.setBillState("-1");
				data=billMapper.updateByPrimaryKeySelective(bill);
			}else if(bill.getBillState().equals("1")){
				bill.setBillState("-2");
				data=billMapper.updateByPrimaryKeySelective(bill);
			}

		}
		return data;
	}


	//加载修改信息
	public Bill loadupdatebill(Integer id) {
		// TODO Auto-generated method stub
		Bill data=billMapper.selectByPrimaryKey(id);
		return data;
	}

	//根据id修改
	public int updatebill(Bill bill) {
		// TODO Auto-generated method stub
		int data=billMapper.updateByPrimaryKeySelective(bill);
		return data;
	}

	//新增记录
	public int addbill(Bill bill) {
		// TODO Auto-generated method stub
		int data=billMapper.insertSelective(bill);
		return data;
	}


	/**
	 * 统计收入、支出
	 * @param params
	 * @return
	 */
	@Override
	public List<Bill> statistics(Map params) {
		return billMapper.statistics(params);
	}

	@Override
	public int insertBill(Bill bill) {
		return  billMapper.insertBill(bill);
	}

	@Override
	public List<Bill> querybillbyall(Map params) {
		String date1=(String) params.get("date1");
		String date2=(String) params.get("date2");
		String searchindex=(String) params.get("searchindex");
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

		params.put("date1", date1);
		params.put("date2", date2);
		params.put("searchindex", searchindex);
		return billMapper.querybillbyall(params);
	}
}
