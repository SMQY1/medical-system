package com.jishou.medical.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 医生新增/修改入参
 */
public class DoctorForm {

    @NotNull(message = "所属科室不能为空")
    private Long deptId;

    @NotBlank(message = "医生工号不能为空")
    @Pattern(regexp = "^[A-Za-z0-9]{3,20}$", message = "医生工号只能为 3-20 位字母或数字")
    private String doctorNo;

    @NotBlank(message = "医生姓名不能为空")
    @Size(max = 50, message = "医生姓名不能超过 50 个字符")
    private String name;

    @NotBlank(message = "性别不能为空")
    @Pattern(regexp = "^[男女]$", message = "性别只能为男或女")
    private String gender;

    @NotBlank(message = "职称不能为空")
    @Size(max = 30, message = "职称不能超过 30 个字符")
    private String title;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @Email(message = "邮箱格式不正确")
    @Size(max = 64, message = "邮箱不能超过 64 个字符")
    private String email;

    @Size(max = 255, message = "擅长领域不能超过 255 个字符")
    private String introduction;

    /** 状态：1 在岗，0 离岗 */
    private Integer status;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDoctorNo() {
        return doctorNo;
    }

    public void setDoctorNo(String doctorNo) {
        this.doctorNo = doctorNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
