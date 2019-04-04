package com.yznu.clinic.beans;

public class Medicines {
    private Integer id;

    private String medicinesInputdate;//药品进库时间

    private String medicinesDate;//药品过期时间

    private String medicinesName;//药品名称

    private String medicinesType;//药品类别

    private Integer medicinesLimit;//药品库存下限

    private Integer medicinesNum;//药品库存数量

    private Double medicinesMoney;//药品售价

    private Double medicinesBid;//药品进价

    private Integer medicinesBatch;//药品批次

    public Integer getMedicinesBatch() {
        return medicinesBatch;
    }

    public void setMedicinesBatch(Integer medicinesBatch) {
        this.medicinesBatch = medicinesBatch;
    }

    public Double getMedicinesBid() {
        return medicinesBid;
    }

    public void setMedicinesBid(Double medicinesBid) {
        this.medicinesBid = medicinesBid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedicinesInputdate() {
        return medicinesInputdate;
    }

    public void setMedicinesInputdate(String medicinesInputdate) {
        this.medicinesInputdate = medicinesInputdate == null ? null : medicinesInputdate.trim();
    }

    public String getMedicinesDate() {
        return medicinesDate;
    }

    public void setMedicinesDate(String medicinesDate) {
        this.medicinesDate = medicinesDate == null ? null : medicinesDate.trim();
    }

    public String getMedicinesName() {
        return medicinesName;
    }

    public void setMedicinesName(String medicinesName) {
        this.medicinesName = medicinesName == null ? null : medicinesName.trim();
    }

    public String getMedicinesType() {
        return medicinesType;
    }

    public void setMedicinesType(String medicinesType) {
        this.medicinesType = medicinesType == null ? null : medicinesType.trim();
    }

    public Integer getMedicinesLimit() {
        return medicinesLimit;
    }

    public void setMedicinesLimit(Integer medicinesLimit) {
        this.medicinesLimit = medicinesLimit;
    }

    public Integer getMedicinesNum() {
        return medicinesNum;
    }

    public void setMedicinesNum(Integer medicinesNum) {
        this.medicinesNum = medicinesNum;
    }

    public Double getMedicinesMoney() {
        return medicinesMoney;
    }

    public void setMedicinesMoney(Double medicinesMoney) {
        this.medicinesMoney = medicinesMoney;
    }
}