package com.jishou.medical.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * 患者新增/修改入参，手机号、身份证等敏感字段做格式校验
 */
public class PatientForm {

    @NotBlank(message = "病历号不能为空")
    @Pattern(regexp = "^[A-Za-z0-9]{4,32}$", message = "病历号只能为 4-32 位字母或数字")
    private String patientNo;

    @NotBlank(message = "患者姓名不能为空")
    @Size(max = 50, message = "患者姓名不能超过 50 个字符")
    private String name;

    @NotBlank(message = "性别不能为空")
    @Pattern(regexp = "^[男女]$", message = "性别只能为男或女")
    private String gender;

    @Past(message = "出生日期必须早于今天")
    private LocalDate birthDate;

    @NotBlank(message = "身份证号不能为空")
    @Pattern(regexp = "^\\d{17}[\\dXx]$", message = "身份证号必须为 18 位，最后一位可为 X")
    private String idCard;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @Size(max = 200, message = "联系地址不能超过 200 个字符")
    private String address;

    @Size(max = 50, message = "紧急联系人不能超过 50 个字符")
    private String emergencyContact;

    @Pattern(regexp = "^$|^1[3-9]\\d{9}$", message = "紧急联系人电话格式不正确")
    private String emergencyPhone;

    /** 状态：1 正常，0 停用 */
    private Integer status;

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
