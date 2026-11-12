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
 * 电子处方表
 * </p>
 *
 * @author 董洋
 * @since 2026-11-12
 */
@TableName("prescription")
public class Prescription implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 处方主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 处方编号，业务唯一
     */
    @TableField("presc_no")
    private String prescNo;

    /**
     * 挂号记录，外键
     */
    @TableField("registration_id")
    private Long registrationId;

    /**
     * 药品名称
     */
    @TableField("medicine_name")
    private String medicineName;

    /**
     * 规格
     */
    @TableField("specification")
    private String specification;

    /**
     * 单次用量
     */
    @TableField("dosage")
    private String dosage;

    /**
     * 用药频次
     */
    @TableField("frequency")
    private String frequency;

    /**
     * 用药天数
     */
    @TableField("days")
    private Integer days;

    /**
     * 医嘱
     */
    @TableField("advice")
    private String advice;

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

    public String getPrescNo() {
        return prescNo;
    }

    public void setPrescNo(String prescNo) {
        this.prescNo = prescNo;
    }

    public Long getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Long registrationId) {
        this.registrationId = registrationId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
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
        return "Prescription{" +
        "id = " + id +
        ", prescNo = " + prescNo +
        ", registrationId = " + registrationId +
        ", medicineName = " + medicineName +
        ", specification = " + specification +
        ", dosage = " + dosage +
        ", frequency = " + frequency +
        ", days = " + days +
        ", advice = " + advice +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        ", deleted = " + deleted +
        "}";
    }
}
