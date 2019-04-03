package com.yznu.clinic.beans;

public class Departments {
    private Integer id;

    private String departmentsName;

    private String departmentsContent;

    private String departmentsDate;

    private String departmentsRemarks;

    private String departmentsState;

    private String departmentsAddress;

    private Integer departmentsNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentsName() {
        return departmentsName;
    }

    public void setDepartmentsName(String departmentsName) {
        this.departmentsName = departmentsName == null ? null : departmentsName.trim();
    }

    public String getDepartmentsContent() {
        return departmentsContent;
    }

    public void setDepartmentsContent(String departmentsContent) {
        this.departmentsContent = departmentsContent == null ? null : departmentsContent.trim();
    }

    public String getDepartmentsDate() {
        return departmentsDate;
    }

    public void setDepartmentsDate(String departmentsDate) {
        this.departmentsDate = departmentsDate == null ? null : departmentsDate.trim();
    }

    public String getDepartmentsRemarks() {
        return departmentsRemarks;
    }

    public void setDepartmentsRemarks(String departmentsRemarks) {
        this.departmentsRemarks = departmentsRemarks == null ? null : departmentsRemarks.trim();
    }

    public String getDepartmentsState() {
        return departmentsState;
    }

    public void setDepartmentsState(String departmentsState) {
        this.departmentsState = departmentsState == null ? null : departmentsState.trim();
    }

    public String getDepartmentsAddress() {
        return departmentsAddress;
    }

    public void setDepartmentsAddress(String departmentsAddress) {
        this.departmentsAddress = departmentsAddress == null ? null : departmentsAddress.trim();
    }

    public Integer getDepartmentsNum() {
        return departmentsNum;
    }

    public void setDepartmentsNum(Integer departmentsNum) {
        this.departmentsNum = departmentsNum;
    }
}