package com.jishou.medical.controller;

import com.jishou.medical.common.PageResult;
import com.jishou.medical.common.Result;
import com.jishou.medical.dto.DepartmentForm;
import com.jishou.medical.dto.DepartmentQuery;
import com.jishou.medical.entity.Department;
import com.jishou.medical.service.DepartmentService;
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
 * 科室管理：分页查询、详情、新增、修改、删除
 */
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /** 多条件分页查询：name 模糊、deptCode 模糊、status 精确 */
    @GetMapping
    public Result<PageResult<Department>> page(DepartmentQuery query) {
        return Result.success(departmentService.pageQuery(query));
    }

    @GetMapping("/{id}")
    public Result<Department> detail(@PathVariable Long id) {
        return Result.success(departmentService.getDetail(id));
    }

    @PostMapping
    public Result<Long> create(@Valid @RequestBody DepartmentForm form) {
        return Result.success(departmentService.create(form));
    }

    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable Long id, @Valid @RequestBody DepartmentForm form) {
        departmentService.update(id, form);
        return Result.success(null);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return Result.success(null);
    }
}
