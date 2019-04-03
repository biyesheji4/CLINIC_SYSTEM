package com.yznu.clinic.dao;


import com.yznu.clinic.beans.Programme;
import com.yznu.clinic.util.Query;

import java.util.List;

public interface ProgrammeMapper {

    /*诊疗方案过渡表明细*/
    public List queryProgrammePage(Query query);

    /*诊疗方案过渡表count*/
    public int getAllProgrammeCount(Query query);

    int insertSelective(Programme record);

    int updateProgramme(Programme programme);

    //根据ID查询诊疗方案信息
    public Programme queryprogrammeById(int id);

    /*删除*/
    public void deleteprogramme(String id);
}