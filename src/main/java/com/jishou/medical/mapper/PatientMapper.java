package com.jishou.medical.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jishou.medical.entity.Patient;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PatientMapper extends BaseMapper<Patient> {
}
