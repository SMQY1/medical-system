package com.jishou.medical.dto;

/**
 * 患者列表查询条件：姓名模糊、手机号模糊、性别精确、状态精确
 */
public class PatientQuery extends PageQuery {

    private String name;
    private String phone;
    private String gender;
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
