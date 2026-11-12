package com.jishou.medical.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jishou.medical.common.BusinessException;
import com.jishou.medical.common.PageResult;
import com.jishou.medical.dto.PatientForm;
import com.jishou.medical.dto.PatientQuery;
import com.jishou.medical.entity.Patient;
import com.jishou.medical.entity.Registration;
import com.jishou.medical.mapper.PatientMapper;
import com.jishou.medical.mapper.RegistrationMapper;
import com.jishou.medical.service.PatientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 患者表 服务实现类
 * </p>
 *
 * @author 董洋
 * @since 2026-11-12
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {

    private final RegistrationMapper registrationMapper;

    public PatientServiceImpl(RegistrationMapper registrationMapper) {
        this.registrationMapper = registrationMapper;
    }

    @Override
    public PageResult<Patient> pageQuery(PatientQuery query) {
        LambdaQueryWrapper<Patient> wrapper = new LambdaQueryWrapper<Patient>()
                .like(StringUtils.hasText(query.getName()), Patient::getName, query.getName())
                .like(StringUtils.hasText(query.getPhone()), Patient::getPhone, query.getPhone())
                .eq(StringUtils.hasText(query.getGender()), Patient::getGender, query.getGender())
                .eq(query.getStatus() != null, Patient::getStatus, toByte(query.getStatus()))
                .orderByAsc(Patient::getId);
        Page<Patient> page = this.page(
                new Page<>(query.normalizedPageNum(), query.normalizedPageSize()), wrapper);
        return PageResult.of(page);
    }

    @Override
    public Patient getDetail(Long id) {
        Patient patient = this.getById(id);
        if (patient == null) {
            throw new BusinessException(404, "患者不存在");
        }
        return patient;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(PatientForm form) {
        checkUnique(null, form);
        Patient patient = new Patient();
        fill(patient, form);
        this.save(patient);
        return patient.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long id, PatientForm form) {
        Patient exists = this.getById(id);
        if (exists == null) {
            throw new BusinessException(404, "患者不存在");
        }
        checkUnique(id, form);
        Patient patient = new Patient();
        patient.setId(id);
        fill(patient, form);
        this.updateById(patient);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Patient exists = this.getById(id);
        if (exists == null) {
            throw new BusinessException(404, "患者不存在");
        }
        Long registrationCount = registrationMapper.selectCount(
                new LambdaQueryWrapper<Registration>().eq(Registration::getPatientId, id));
        if (registrationCount != null && registrationCount > 0) {
            throw new BusinessException(400, "该患者存在 " + registrationCount + " 条挂号记录，无法删除");
        }
        this.removeById(id);
    }

    private void fill(Patient patient, PatientForm form) {
        patient.setPatientNo(form.getPatientNo().trim());
        patient.setName(form.getName().trim());
        patient.setGender(form.getGender());
        patient.setBirthDate(form.getBirthDate());
        patient.setIdCard(form.getIdCard().trim().toUpperCase());
        patient.setPhone(form.getPhone().trim());
        patient.setAddress(form.getAddress());
        patient.setEmergencyContact(form.getEmergencyContact());
        patient.setEmergencyPhone(form.getEmergencyPhone());
        patient.setStatus(form.getStatus() == null ? (byte) 1 : toByte(form.getStatus()));
    }

    private void checkUnique(Long id, PatientForm form) {
        Long sameNo = this.count(new LambdaQueryWrapper<Patient>()
                .eq(Patient::getPatientNo, form.getPatientNo().trim())
                .ne(id != null, Patient::getId, id));
        if (sameNo > 0) {
            throw new BusinessException(409, "病历号已存在：" + form.getPatientNo());
        }
        Long sameIdCard = this.count(new LambdaQueryWrapper<Patient>()
                .eq(Patient::getIdCard, form.getIdCard().trim().toUpperCase())
                .ne(id != null, Patient::getId, id));
        if (sameIdCard > 0) {
            throw new BusinessException(409, "身份证号已存在：" + form.getIdCard());
        }
        Long samePhone = this.count(new LambdaQueryWrapper<Patient>()
                .eq(Patient::getPhone, form.getPhone().trim())
                .ne(id != null, Patient::getId, id));
        if (samePhone > 0) {
            throw new BusinessException(409, "手机号已存在：" + form.getPhone());
        }
    }

    private Byte toByte(Integer value) {
        return value == null ? null : value.byteValue();
    }
}
