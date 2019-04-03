package com.yznu.clinic.beans;

public class Medicines {
    private Integer id;

    private String medicinesInputdate;

    private String medicinesDate;

    private String medicinesName;

    private String medicinesType;

    private Integer medicinesLimit;

    private Integer medicinesNum;

    private Double medicinesMoney;

    private Double medicinesBid;

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