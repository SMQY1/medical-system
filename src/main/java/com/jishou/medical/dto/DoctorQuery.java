package com.jishou.medical.dto;

/**
 * 医生列表查询条件：姓名模糊、科室精确、职称模糊、状态精确
 */
public class DoctorQuery extends PageQuery {

    private String name;
    private Long deptId;
    private String title;
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
