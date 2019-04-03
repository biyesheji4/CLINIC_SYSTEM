package com.yznu.clinic.beans;

import java.util.List;

public class Registered {
    private Integer id;

    private String departmentsId;

    private Integer patientAge;

    private String patientName;

    private String patientIdcard;

    private String patientSex;

    private String patientTel;

    private String registeredContent;

    private String registeredDate;

    private String registeredMoney;

    private String registeredState;

    private String projectResult;

    private List<Cases> cases;

    public List<Cases> getCases() {
        return cases;
    }

    public String getProjectResult() {
        return projectResult;
    }

    public void setProjectResult(String projectResult) {
        this.projectResult = projectResult == null ? null : projectResult.trim();
    }

    public void setCases(List<Cases> cases) {
        this.cases = cases;
    }
    private Departments departments;

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentsId() {
        return departmentsId;
    }

    public void setDepartmentsId(String departmentsId) {
        this.departmentsId = departmentsId == null ? null : departmentsId.trim();
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getPatientIdcard() {
        return patientIdcard;
    }

    public void setPatientIdcard(String patientIdcard) {
        this.patientIdcard = patientIdcard == null ? null : patientIdcard.trim();
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex == null ? null : patientSex.trim();
    }

    public String getPatientTel() {
        return patientTel;
    }

    public void setPatientTel(String patientTel) {
        this.patientTel = patientTel == null ? null : patientTel.trim();
    }

    public String getRegisteredContent() {
        return registeredContent;
    }

    public void setRegisteredContent(String registeredContent) {
        this.registeredContent = registeredContent == null ? null : registeredContent.trim();
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate == null ? null : registeredDate.trim();
    }

    public String getRegisteredMoney() {
        return registeredMoney;
    }

    public void setRegisteredMoney(String registeredMoney) {
        this.registeredMoney = registeredMoney == null ? null : registeredMoney.trim();
    }

    public String getRegisteredState() {
        return registeredState;
    }

    public void setRegisteredState(String registeredState) {
        this.registeredState = registeredState == null ? null : registeredState.trim();
    }
}