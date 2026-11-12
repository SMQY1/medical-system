package com.jishou.medical.controller;

import com.jishou.medical.common.PageResult;
import com.jishou.medical.common.Result;
import com.jishou.medical.dto.PatientForm;
import com.jishou.medical.dto.PatientQuery;
import com.jishou.medical.entity.Patient;
import com.jishou.medical.service.PatientService;
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
 * 患者管理：分页查询、详情、新增、修改、删除
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /** 多条件分页查询：name 模糊、phone 模糊、gender 精确、status 精确 */
    @GetMapping
    public Result<PageResult<Patient>> page(PatientQuery query) {
        return Result.success(patientService.pageQuery(query));
    }

    @GetMapping("/{id}")
    public Result<Patient> detail(@PathVariable Long id) {
        return Result.success(patientService.getDetail(id));
    }

    @PostMapping
    public Result<Long> create(@Valid @RequestBody PatientForm form) {
        return Result.success(patientService.create(form));
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @Valid @RequestBody PatientForm form) {
        patientService.update(id, form);
        return Result.success(null);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        patientService.delete(id);
        return Result.success(null);
    }
}
