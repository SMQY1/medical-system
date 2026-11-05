package com.jishou.medical.service;

import com.jishou.medical.entity.Department;
import com.jishou.medical.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public List<Department> listAll() {
        return departmentMapper.selectList(null);
    }
}
