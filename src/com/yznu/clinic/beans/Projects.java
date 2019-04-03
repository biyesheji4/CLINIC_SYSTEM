package com.yznu.clinic.beans;

public class Projects {
    private Integer id;

    private Integer departmentId;
    
    private Double projectMoney;

    private String projectsName;

    private String projectsContent;

    private String projectsDate;

    private String projectsRemarks;

    private String projectsState;

    private Integer projectsNum;

    public Double getProjectMoney() {
		return projectMoney;
	}

	public void setProjectMoney(Double projectMoney) {
		this.projectMoney = projectMoney;
	}

	public Integer getId() {
        return id;
    }

    public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectsName() {
        return projectsName;
    }

    public void setProjectsName(String projectsName) {
        this.projectsName = projectsName == null ? null : projectsName.trim();
    }

    public String getProjectsContent() {
        return projectsContent;
    }

    public void setProjectsContent(String projectsContent) {
        this.projectsContent = projectsContent == null ? null : projectsContent.trim();
    }

    public String getProjectsDate() {
        return projectsDate;
    }

    public void setProjectsDate(String projectsDate) {
        this.projectsDate = projectsDate == null ? null : projectsDate.trim();
    }

    public String getProjectsRemarks() {
        return projectsRemarks;
    }

    public void setProjectsRemarks(String projectsRemarks) {
        this.projectsRemarks = projectsRemarks == null ? null : projectsRemarks.trim();
    }

    public String getProjectsState() {
        return projectsState;
    }

    public void setProjectsState(String projectsState) {
        this.projectsState = projectsState == null ? null : projectsState.trim();
    }

    public Integer getProjectsNum() {
        return projectsNum;
    }

    public void setProjectsNum(Integer projectsNum) {
        this.projectsNum = projectsNum;
    }
}