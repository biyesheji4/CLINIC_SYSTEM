package com.yznu.clinic.service.impl;

import com.yznu.clinic.beans.Programme;
import com.yznu.clinic.dao.ProgrammeMapper;
import com.yznu.clinic.interfaces.ProgrammeSV;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammeImpl implements ProgrammeSV {
    @Autowired
    ProgrammeMapper programmeMapper;

    @Override
    public PageList queryProgrammePage(Query query) {
        PageList pageList = new PageList();
        List rows = programmeMapper.queryProgrammePage(query);
        int total = programmeMapper.getAllProgrammeCount(query);
        pageList.setRows(rows);
        pageList.setTotal(total);
        return pageList;
    }

    @Override
    public int insertSelective(Programme record) {
        return programmeMapper.insertSelective(record);
    }

    @Override
    public int updateProgramme(Programme programme) {
        return programmeMapper.updateProgramme(programme);
    }

    @Override
    public Programme queryprogrammeById(int id) {
        return programmeMapper.queryprogrammeById(id);
    }

    @Override
    public void deleteprogramme(String id) {
        programmeMapper.deleteprogramme(id);
    }

}
