package com.jishou.medical.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 挂号记录表
 * </p>
 *
 * @author 董洋
 * @since 2026-11-12
 */
@TableName("registration")
public class Registration implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 挂号主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 挂号单号，业务唯一
     */
    @TableField("reg_no")
    private String regNo;

    /**
     * 患者，外键
     */
    @TableField("patient_id")
    private Long patientId;

    /**
     * 医生，外键
     */
    @TableField("doctor_id")
    private Long doctorId;

    /**
     * 就诊时间
     */
    @TableField("visit_time")
    private LocalDateTime visitTime;

    /**
     * 挂号费
     */
    @TableField("fee")
    private BigDecimal fee;

    /**
     * 状态：0 待就诊，1 已就诊，2 已取消
     */
    @TableField("status")
    private Byte status;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

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

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDateTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDateTime visitTime) {
        this.visitTime = visitTime;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return "Registration{" +
        "id = " + id +
        ", regNo = " + regNo +
        ", patientId = " + patientId +
        ", doctorId = " + doctorId +
        ", visitTime = " + visitTime +
        ", fee = " + fee +
        ", status = " + status +
        ", remark = " + remark +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        ", deleted = " + deleted +
        "}";
    }
}
