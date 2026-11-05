package com.jishou.medical.service;

import com.jishou.medical.entity.Doctor;
import com.jishou.medical.mapper.DoctorMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorMapper doctorMapper;

    public DoctorService(DoctorMapper doctorMapper) {
        this.doctorMapper = doctorMapper;
    }

    public List<Doctor> listByDepartment(Long departmentId) {
        return doctorMapper.selectList(null);
    }
}
