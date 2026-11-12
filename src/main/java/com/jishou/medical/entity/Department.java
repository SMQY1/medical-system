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
 * 科室表
 * </p>
 *
 * @author 董洋
 * @since 2026-11-12
 */
@TableName("department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 科室主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 科室编码，业务唯一
     */
    @TableField("dept_code")
    private String deptCode;

    /**
     * 科室名称
     */
    @TableField("name")
    private String name;

    /**
     * 科室位置
     */
    @TableField("location")
    private String location;

    /**
     * 科室联系电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 科室简介
     */
    @TableField("description")
    private String description;

    /**
     * 状态：1 启用，0 停用
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
        return "Department{" +
        "id = " + id +
        ", deptCode = " + deptCode +
        ", name = " + name +
        ", location = " + location +
        ", phone = " + phone +
        ", description = " + description +
        ", status = " + status +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        ", deleted = " + deleted +
        "}";
    }
}
