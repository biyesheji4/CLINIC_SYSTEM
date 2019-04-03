package com.yznu.clinic.beans;

public class Programme {
    private Integer id;

    private String programmeDate;

    private String programmeMedicine;

    private String programmeProjects;

    private String patientId;

    private String programmeState;

    private Double programmeMony;

    private String medicineId;

    private String medicineNum;

    private Registered registered;

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId == null ? null : medicineId.trim();
    }

    public String getMedicineNum() {
        return medicineNum;
    }

    public void setMedicineNum(String medicineNum) {
        this.medicineNum = medicineNum == null ? null : medicineNum.trim();
    }

    public Double getProgrammeMony() {
        return programmeMony;
    }

    public String getProgrammeState() {
        return programmeState;
    }

    public void setProgrammeState(String programmeState) {
        this.programmeState = programmeState;
    }

    public void setProgrammeMony(Double programmeMony) {
        this.programmeMony = programmeMony;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProgrammeDate() {
        return programmeDate;
    }

    public void setProgrammeDate(String programmeDate) {
        this.programmeDate = programmeDate == null ? null : programmeDate.trim();
    }

    public String getProgrammeMedicine() {
        return programmeMedicine;
    }

    public void setProgrammeMedicine(String programmeMedicine) {
        this.programmeMedicine = programmeMedicine == null ? null : programmeMedicine.trim();
    }

    public String getProgrammeProjects() {
        return programmeProjects;
    }

    public void setProgrammeProjects(String programmeProjects) {
        this.programmeProjects = programmeProjects == null ? null : programmeProjects.trim();
    }

    public Registered getRegistered() {
        return registered;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}