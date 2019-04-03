package com.yznu.clinic.interfaces;

import com.yznu.clinic.beans.Programme;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;

import java.util.List;

public interface ProgrammeSV {

    /*诊疗方案过渡表明细*/
    public PageList queryProgrammePage(Query query);

    int insertSelective(Programme record);
    int updateProgramme(Programme programme);

    public Programme queryprogrammeById(int id);   //根据ID查询诊疗方案信息

    /*删除*/
    public void deleteprogramme(String id);
}
