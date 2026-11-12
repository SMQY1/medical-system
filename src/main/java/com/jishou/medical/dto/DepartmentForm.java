package com.jishou.medical.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 科室新增/修改入参
 */
public class DepartmentForm {

    @NotBlank(message = "科室编码不能为空")
    @Pattern(regexp = "^[A-Za-z0-9]{2,20}$", message = "科室编码只能为 2-20 位字母或数字")
    private String deptCode;

    @NotBlank(message = "科室名称不能为空")
    @Size(max = 50, message = "科室名称不能超过 50 个字符")
    private String name;

    @Size(max = 100, message = "科室位置不能超过 100 个字符")
    private String location;

    @Pattern(regexp = "^$|^(0\\d{2,3}-?\\d{7,8}|1[3-9]\\d{9})$", message = "科室电话格式不正确")
    private String phone;

    @Size(max = 255, message = "科室简介不能超过 255 个字符")
    private String description;

    /** 状态：1 启用，0 停用，不传默认启用 */
    private Integer status;

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
