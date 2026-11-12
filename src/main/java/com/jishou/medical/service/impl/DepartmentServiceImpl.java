package com.jishou.medical.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jishou.medical.common.BusinessException;
import com.jishou.medical.common.PageResult;
import com.jishou.medical.dto.DepartmentForm;
import com.jishou.medical.dto.DepartmentQuery;
import com.jishou.medical.entity.Department;
import com.jishou.medical.entity.Doctor;
import com.jishou.medical.mapper.DepartmentMapper;
import com.jishou.medical.mapper.DoctorMapper;
import com.jishou.medical.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 科室表 服务实现类
 * </p>
 *
 * @author 董洋
 * @since 2026-11-12
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    private final DoctorMapper doctorMapper;

    public DepartmentServiceImpl(DoctorMapper doctorMapper) {
        this.doctorMapper = doctorMapper;
    }

    @Override
    public PageResult<Department> pageQuery(DepartmentQuery query) {
        // 条件全部通过 LambdaQueryWrapper 以 #{} 预编译占位符传递，天然防止 SQL 注入
        LambdaQueryWrapper<Department> wrapper = new LambdaQueryWrapper<Department>()
                .like(StringUtils.hasText(query.getName()), Department::getName, query.getName())
                .like(StringUtils.hasText(query.getDeptCode()), Department::getDeptCode, query.getDeptCode())
                .eq(query.getStatus() != null, Department::getStatus, toByte(query.getStatus()))
                .orderByAsc(Department::getId);
        Page<Department> page = this.page(
                new Page<>(query.normalizedPageNum(), query.normalizedPageSize()), wrapper);
        return PageResult.of(page);
    }

    @Override
    public Department getDetail(Long id) {
        Department department = this.getById(id);
        if (department == null) {
            throw new BusinessException(404, "科室不存在");
        }
        return department;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(DepartmentForm form) {
        checkUnique(null, form);
        Department department = new Department();
        fill(department, form);
        this.save(department);
        return department.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long id, DepartmentForm form) {
        Department exists = this.getById(id);
        if (exists == null) {
            throw new BusinessException(404, "科室不存在");
        }
        checkUnique(id, form);
        Department department = new Department();
        department.setId(id);
        fill(department, form);
        this.updateById(department);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Department exists = this.getById(id);
        if (exists == null) {
            throw new BusinessException(404, "科室不存在");
        }
        Long doctorCount = doctorMapper.selectCount(
                new LambdaQueryWrapper<Doctor>().eq(Doctor::getDeptId, id));
        if (doctorCount != null && doctorCount > 0) {
            throw new BusinessException(400, "该科室下仍有 " + doctorCount + " 名医生，无法删除");
        }
        this.removeById(id);
    }

    private void fill(Department department, DepartmentForm form) {
        department.setDeptCode(form.getDeptCode().trim());
        department.setName(form.getName().trim());
        department.setLocation(form.getLocation());
        department.setPhone(form.getPhone());
        department.setDescription(form.getDescription());
        department.setStatus(form.getStatus() == null ? (byte) 1 : toByte(form.getStatus()));
    }

    private void checkUnique(Long id, DepartmentForm form) {
        Long sameCode = this.count(new LambdaQueryWrapper<Department>()
                .eq(Department::getDeptCode, form.getDeptCode().trim())
                .ne(id != null, Department::getId, id));
        if (sameCode > 0) {
            throw new BusinessException(409, "科室编码已存在：" + form.getDeptCode());
        }
        Long sameName = this.count(new LambdaQueryWrapper<Department>()
                .eq(Department::getName, form.getName().trim())
                .ne(id != null, Department::getId, id));
        if (sameName > 0) {
            throw new BusinessException(409, "科室名称已存在：" + form.getName());
        }
    }

    private Byte toByte(Integer value) {
        return value == null ? null : value.byteValue();
    }
}
