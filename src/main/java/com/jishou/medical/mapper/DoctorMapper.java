package com.jishou.medical.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jishou.medical.entity.Doctor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DoctorMapper extends BaseMapper<Doctor> {
}
