package com.jishou.medical.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jishou.medical.entity.Prescription;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrescriptionMapper extends BaseMapper<Prescription> {
}
