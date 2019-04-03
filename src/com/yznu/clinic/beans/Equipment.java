package com.yznu.clinic.beans;

public class Equipment {
    private Integer id;

    private String equipmentName;

    private String equipmentContent;

    private String equipmentDate;

    private String equipmentRemarks;

    private String equipmentState;

    private String equipmentAddress;

    private Integer equipmentNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName == null ? null : equipmentName.trim();
    }

    public String getEquipmentContent() {
        return equipmentContent;
    }

    public void setEquipmentContent(String equipmentContent) {
        this.equipmentContent = equipmentContent == null ? null : equipmentContent.trim();
    }

    public String getEquipmentDate() {
        return equipmentDate;
    }

    public void setEquipmentDate(String equipmentDate) {
        this.equipmentDate = equipmentDate == null ? null : equipmentDate.trim();
    }

    public String getEquipmentRemarks() {
        return equipmentRemarks;
    }

    public void setEquipmentRemarks(String equipmentRemarks) {
        this.equipmentRemarks = equipmentRemarks == null ? null : equipmentRemarks.trim();
    }

    public String getEquipmentState() {
        return equipmentState;
    }

    public void setEquipmentState(String equipmentState) {
        this.equipmentState = equipmentState == null ? null : equipmentState.trim();
    }

    public String getEquipmentAddress() {
        return equipmentAddress;
    }

    public void setEquipmentAddress(String equipmentAddress) {
        this.equipmentAddress = equipmentAddress == null ? null : equipmentAddress.trim();
    }

    public Integer getEquipmentNum() {
        return equipmentNum;
    }

    public void setEquipmentNum(Integer equipmentNum) {
        this.equipmentNum = equipmentNum;
    }
}