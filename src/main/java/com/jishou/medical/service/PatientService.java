package com.jishou.medical.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jishou.medical.common.PageResult;
import com.jishou.medical.dto.PatientForm;
import com.jishou.medical.dto.PatientQuery;
import com.jishou.medical.entity.Patient;

/**
 * <p>
 * 患者表 服务类
 * </p>
 *
 * @author 董洋
 * @since 2026-11-12
 */
public interface PatientService extends IService<Patient> {

    PageResult<Patient> pageQuery(PatientQuery query);

    Patient getDetail(Long id);

    Long create(PatientForm form);

    void update(Long id, PatientForm form);

    void delete(Long id);
}
