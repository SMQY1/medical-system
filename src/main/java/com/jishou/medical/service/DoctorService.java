package com.jishou.medical.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jishou.medical.common.PageResult;
import com.jishou.medical.dto.DoctorForm;
import com.jishou.medical.dto.DoctorQuery;
import com.jishou.medical.entity.Doctor;
import com.jishou.medical.vo.DoctorVO;

/**
 * <p>
 * 医生表 服务类
 * </p>
 *
 * @author 董洋
 * @since 2026-11-12
 */
public interface DoctorService extends IService<Doctor> {

    PageResult<DoctorVO> pageQuery(DoctorQuery query);

    DoctorVO getDetail(Long id);

    Long create(DoctorForm form);

    void update(Long id, DoctorForm form);

    void delete(Long id);
}
