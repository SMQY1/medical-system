package com.jishou.medical.vo;

import com.jishou.medical.entity.Doctor;

import java.time.LocalDateTime;

/**
 * 医生列表/详情返回对象：在医生表基础上补充科室名称，
 * 避免前端为了显示科室名再发起一次请求。
 */
public class DoctorVO {

    private Long id;
    private Long deptId;
    private String deptName;
    private String doctorNo;
    private String name;
    private String gender;
    private String title;
    private String phone;
    private String email;
    private String introduction;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static DoctorVO from(Doctor doctor) {
        DoctorVO vo = new DoctorVO();
        vo.setId(doctor.getId());
        vo.setDeptId(doctor.getDeptId());
        vo.setDoctorNo(doctor.getDoctorNo());
        vo.setName(doctor.getName());
        vo.setGender(doctor.getGender());
        vo.setTitle(doctor.getTitle());
        vo.setPhone(doctor.getPhone());
        vo.setEmail(doctor.getEmail());
        vo.setIntroduction(doctor.getIntroduction());
        vo.setStatus(doctor.getStatus() == null ? null : doctor.getStatus().intValue());
        vo.setCreateTime(doctor.getCreateTime());
        vo.setUpdateTime(doctor.getUpdateTime());
        return vo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
