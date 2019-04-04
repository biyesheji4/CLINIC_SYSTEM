package com.yznu.clinic.dao;

import com.yznu.clinic.beans.Medicines;
import com.yznu.clinic.dao.MedicinesMapper;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;

import java.util.List;
import java.util.Map;

public interface MedicinesMapper {
    /*药品明细*/
    public List querymedicinesPage(Query query);

    /*药品明细count*/
    public int getAllmedicinesCount(Query query);

    /*根据ID查询药品*/
    public Medicines querymedicinesById(String id);

    /*修改药品*/
    int updatemedicines(Medicines medicines);

    /*批量更新*/
    int updateBatch(List<Medicines> list);

    /*库存不足  进货*/
    int addmedicines(Medicines medicines);

    /*删除药品*/
    public void deletemedicines(String id);

    /*根据药品名称、类别查询*/
    public List querymedicines(Map map);

    /*查询分类药品的药品总量*/
    public List querymedicinesSum(Map map);
}