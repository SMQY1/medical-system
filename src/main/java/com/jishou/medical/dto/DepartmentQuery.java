package com.jishou.medical.dto;

/**
 * 科室列表查询条件：名称模糊、编码模糊、状态精确
 */
public class DepartmentQuery extends PageQuery {

    private String name;
    private String deptCode;
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
