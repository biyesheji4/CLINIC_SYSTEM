package com.yznu.clinic.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yznu.clinic.beans.Projects;
import com.yznu.clinic.util.Query;

public interface ProjectsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Projects record);

    int insertSelective(Projects record);

    Projects selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Projects record);

    int updateByPrimaryKey(Projects record);

    List<Projects> selectAll();

    List<Projects> queryProjectsBySql(Map<String,Object> data);

    int queryTotalsize(Map<String, Object> data);

    List<Projects> querysomedata(Map<String, Object> data);

    List<HashMap<String, Object>> queryusededepartment();


    /*诊疗项目明细*/
    public List queryprojectPage(Query query);

    /*诊疗项目明细count*/
    public int getAllprojectCount(Query query);
}