package com.jishou.medical.controller;

import com.jishou.medical.common.PageResult;
import com.jishou.medical.common.Result;
import com.jishou.medical.dto.DoctorForm;
import com.jishou.medical.dto.DoctorQuery;
import com.jishou.medical.service.DoctorService;
import com.jishou.medical.vo.DoctorVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 医生管理：分页查询、详情、新增、修改、删除
 */
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /** 多条件分页查询：name 模糊、deptId 精确、title 模糊、status 精确 */
    @GetMapping
    public Result<PageResult<DoctorVO>> page(DoctorQuery query) {
        return Result.success(doctorService.pageQuery(query));
    }

    @GetMapping("/{id}")
    public Result<DoctorVO> detail(@PathVariable Long id) {
        return Result.success(doctorService.getDetail(id));
    }

    @PostMapping
    public Result<Long> create(@Valid @RequestBody DoctorForm form) {
        return Result.success(doctorService.create(form));
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @Valid @RequestBody DoctorForm form) {
        doctorService.update(id, form);
        return Result.success(null);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        doctorService.delete(id);
        return Result.success(null);
    }
}
