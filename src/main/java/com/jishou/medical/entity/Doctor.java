package com.jishou.medical.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 医生表
 * </p>
 *
 * @author 董洋
 * @since 2026-11-12
 */
@TableName("doctor")
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 医生主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属科室，外键
     */
    @TableField("dept_id")
    private Long deptId;

    /**
     * 医生工号，业务唯一
     */
    @TableField("doctor_no")
    private String doctorNo;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 性别：男/女
     */
    @TableField("gender")
    private String gender;

    /**
     * 职称，如主任医师
     */
    @TableField("title")
    private String title;

    /**
     * 手机号，业务唯一
     */
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 擅长领域
     */
    @TableField("introduction")
    private String introduction;

    /**
     * 状态：1 在岗，0 离岗
     */
    @TableField("status")
    private Byte status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除：0 正常，1 已删除
     */
    @TableField("deleted")
    @TableLogic
    private Byte deleted;

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
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

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Doctor{" +
        "id = " + id +
        ", deptId = " + deptId +
        ", doctorNo = " + doctorNo +
        ", name = " + name +
        ", gender = " + gender +
        ", title = " + title +
        ", phone = " + phone +
        ", email = " + email +
        ", introduction = " + introduction +
        ", status = " + status +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        ", deleted = " + deleted +
        "}";
    }
}
