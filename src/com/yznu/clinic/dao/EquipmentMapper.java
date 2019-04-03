package com.yznu.clinic.dao;

import java.util.List;
import java.util.Map;

import com.yznu.clinic.beans.Equipment;
import com.yznu.clinic.beans.Projects;

public interface EquipmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    Equipment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);

    List<Equipment> selectAll();

    List<Projects> querysomedata(Map<String, Object> data);

    int queryTotalsize(Map<String, Object> data);

    List<Projects> queryProjectsBySql(Map<String, Object> data);
}