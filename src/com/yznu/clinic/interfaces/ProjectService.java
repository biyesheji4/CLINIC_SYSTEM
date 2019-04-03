package com.yznu.clinic.interfaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yznu.clinic.beans.Projects;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;

public interface ProjectService {

	//获取所有记录
	List<Projects> getallproject();
	//删除一条数据
	int delproject(Integer id);
	//批量删除
	int delallproject(Integer[] allId);
	//新增记录
	int addproject(Projects projects);
	//加载修改信息
	int updateproject(Projects projects);
	//根据id修改
	Projects loadupdateproject(Integer id);


	//List<Projects> queryProjectsBySql(int currPage, int pageSize, String sta);
	//查询当前查询记录总条数
	int queryTotalsize(int pagesize,Map<String, Object> data);
	//分页查询&条件查询
	List<Projects> querysomedata(Map<String, Object> data);
	//加载科室列表
	List<HashMap<String, Object>> queryusededepartment();


	Projects selectByPrimaryKey(Integer id);
	/*诊疗项目明细*/
	public PageList queryprojectPage(Query query);
}
