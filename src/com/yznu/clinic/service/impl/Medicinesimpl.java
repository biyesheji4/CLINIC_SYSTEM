package com.yznu.clinic.service.impl;

import com.yznu.clinic.beans.Bill;
import com.yznu.clinic.beans.Medicines;
import com.yznu.clinic.dao.BillMapper;
import com.yznu.clinic.dao.MedicinesMapper;
import com.yznu.clinic.interfaces.MedicinesSV;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Medicinesimpl implements MedicinesSV {
    @Autowired
    MedicinesMapper medicinesMapper;
    @Autowired
    BillMapper billMapper;

    @Override
    public PageList querymedicinesPage(Query query) {
        PageList pageList = new PageList();
        List rows = medicinesMapper.querymedicinesPage(query);
        int total = medicinesMapper.getAllmedicinesCount(query);
        pageList.setRows(rows);
        pageList.setTotal(total);
        return pageList;
    }

    /*根据ID查询药品*/
    @Override
    public Medicines querymedicinesById(String id) {
        return medicinesMapper.querymedicinesById(id);
    }
    /*修改药品*/
    @Override
    public int updatemedicines(Medicines medicines) {
        return medicinesMapper.updatemedicines(medicines);
    }

    @Override
    public int updateBatch(List<Medicines> list) {
        return medicinesMapper.updateBatch(list);
    }

    /*库存不足  进货*/
    @Override
    public int addmedicines(Medicines medicines) {
        return medicinesMapper.addmedicines(medicines);
    }

    /*删除药品*/
    @Override
    public void deletemedicines(String id) {
        if(null!=id&&!"".equals(id)){
            String[] ids = id.split(",");
            for (String strid : ids) {
                medicinesMapper.deletemedicines(strid);
            }
        }
    }

    @Override
    public List querymedicines(Map map) {
        return medicinesMapper.querymedicines(map);
    }

    @Override
    public List querymedicinesSum(Map map) {
        return medicinesMapper.querymedicinesSum(map);
    }
}
