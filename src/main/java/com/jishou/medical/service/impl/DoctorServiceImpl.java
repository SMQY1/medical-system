package com.jishou.medical.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jishou.medical.common.BusinessException;
import com.jishou.medical.common.PageResult;
import com.jishou.medical.dto.DoctorForm;
import com.jishou.medical.dto.DoctorQuery;
import com.jishou.medical.entity.Department;
import com.jishou.medical.entity.Doctor;
import com.jishou.medical.entity.Registration;
import com.jishou.medical.mapper.DepartmentMapper;
import com.jishou.medical.mapper.DoctorMapper;
import com.jishou.medical.mapper.RegistrationMapper;
import com.jishou.medical.service.DoctorService;
import com.jishou.medical.vo.DoctorVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 医生表 服务实现类
 * </p>
 *
 * @author 董洋
 * @since 2026-11-12
 */
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService {

    private final DepartmentMapper departmentMapper;
    private final RegistrationMapper registrationMapper;

    public DoctorServiceImpl(DepartmentMapper departmentMapper, RegistrationMapper registrationMapper) {
        this.departmentMapper = departmentMapper;
        this.registrationMapper = registrationMapper;
    }

    @Override
    public PageResult<DoctorVO> pageQuery(DoctorQuery query) {
        LambdaQueryWrapper<Doctor> wrapper = new LambdaQueryWrapper<Doctor>()
                .like(StringUtils.hasText(query.getName()), Doctor::getName, query.getName())
                .eq(query.getDeptId() != null, Doctor::getDeptId, query.getDeptId())
                .like(StringUtils.hasText(query.getTitle()), Doctor::getTitle, query.getTitle())
                .eq(query.getStatus() != null, Doctor::getStatus, toByte(query.getStatus()))
                .orderByAsc(Doctor::getId);
        Page<Doctor> page = this.page(
                new Page<>(query.normalizedPageNum(), query.normalizedPageSize()), wrapper);

        PageResult<DoctorVO> result = PageResult.of(page, DoctorVO::from);
        fillDeptName(result.getRecords());
        return result;
    }

    @Override
    public DoctorVO getDetail(Long id) {
        Doctor doctor = this.getById(id);
        if (doctor == null) {
            throw new BusinessException(404, "医生不存在");
        }
        DoctorVO vo = DoctorVO.from(doctor);
        Department department = departmentMapper.selectById(doctor.getDeptId());
        vo.setDeptName(department == null ? null : department.getName());
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(DoctorForm form) {
        checkDepartment(form.getDeptId());
        checkUnique(null, form);
        Doctor doctor = new Doctor();
        fill(doctor, form);
        this.save(doctor);
        return doctor.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long id, DoctorForm form) {
        Doctor exists = this.getById(id);
        if (exists == null) {
            throw new BusinessException(404, "医生不存在");
        }
        checkDepartment(form.getDeptId());
        checkUnique(id, form);
        Doctor doctor = new Doctor();
        doctor.setId(id);
        fill(doctor, form);
        this.updateById(doctor);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Doctor exists = this.getById(id);
        if (exists == null) {
            throw new BusinessException(404, "医生不存在");
        }
        Long registrationCount = registrationMapper.selectCount(
                new LambdaQueryWrapper<Registration>().eq(Registration::getDoctorId, id));
        if (registrationCount != null && registrationCount > 0) {
            throw new BusinessException(400, "该医生存在 " + registrationCount + " 条挂号记录，无法删除");
        }
        this.removeById(id);
    }

    private void fillDeptName(List<DoctorVO> records) {
        if (records.isEmpty()) {
            return;
        }
        List<Long> deptIds = records.stream().map(DoctorVO::getDeptId).distinct().toList();
        Map<Long, String> deptNames = departmentMapper.selectBatchIds(deptIds).stream()
                .collect(Collectors.toMap(Department::getId, Department::getName));
        records.forEach(vo -> vo.setDeptName(deptNames.get(vo.getDeptId())));
    }

    private void fill(Doctor doctor, DoctorForm form) {
        doctor.setDeptId(form.getDeptId());
        doctor.setDoctorNo(form.getDoctorNo().trim());
        doctor.setName(form.getName().trim());
        doctor.setGender(form.getGender());
        doctor.setTitle(form.getTitle().trim());
        doctor.setPhone(form.getPhone().trim());
        doctor.setEmail(form.getEmail());
        doctor.setIntroduction(form.getIntroduction());
        doctor.setStatus(form.getStatus() == null ? (byte) 1 : toByte(form.getStatus()));
    }

    private void checkDepartment(Long deptId) {
        Department department = departmentMapper.selectById(deptId);
        if (department == null) {
            throw new BusinessException(400, "所属科室不存在：" + deptId);
        }
    }

    private void checkUnique(Long id, DoctorForm form) {
        Long sameNo = this.count(new LambdaQueryWrapper<Doctor>()
                .eq(Doctor::getDoctorNo, form.getDoctorNo().trim())
                .ne(id != null, Doctor::getId, id));
        if (sameNo > 0) {
            throw new BusinessException(409, "医生工号已存在：" + form.getDoctorNo());
        }
        Long samePhone = this.count(new LambdaQueryWrapper<Doctor>()
                .eq(Doctor::getPhone, form.getPhone().trim())
                .ne(id != null, Doctor::getId, id));
        if (samePhone > 0) {
            throw new BusinessException(409, "医生手机号已存在：" + form.getPhone());
        }
    }

    private Byte toByte(Integer value) {
        return value == null ? null : value.byteValue();
    }
}
