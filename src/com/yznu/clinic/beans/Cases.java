package com.yznu.clinic.beans;

public class Cases {
    private Integer id;

    private String casesContent;

    private Integer casesProgramme;

    private String casesDate;

    private  String casesState;

    private String patientId;

    private String doctorId;

    private Employee employee;


    private Programme programme;

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getCasesState() {
        return casesState;
    }

    public Integer getCasesProgramme() {
        return casesProgramme;
    }

    public void setCasesProgramme(Integer casesProgramme) {
        this.casesProgramme = casesProgramme;
    }

    public void setCasesState(String casesState) {
        this.casesState = casesState == null ? null : casesState.trim();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCasesContent() {
        return casesContent;
    }

    public void setCasesContent(String casesContent) {
        this.casesContent = casesContent == null ? null : casesContent.trim();
    }

    public String getCasesDate() {
        return casesDate;
    }

    public void setCasesDate(String casesDate) {
        this.casesDate = casesDate == null ? null : casesDate.trim();
    }


    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId == null ? null : doctorId.trim();
    }

}