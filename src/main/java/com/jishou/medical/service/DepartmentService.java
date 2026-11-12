package com.jishou.medical.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jishou.medical.common.PageResult;
import com.jishou.medical.dto.DepartmentForm;
import com.jishou.medical.dto.DepartmentQuery;
import com.jishou.medical.entity.Department;

/**
 * <p>
 * 科室表 服务类
 * </p>
 *
 * @author 董洋
 * @since 2026-11-12
 */
public interface DepartmentService extends IService<Department> {

    PageResult<Department> pageQuery(DepartmentQuery query);

    Department getDetail(Long id);

    Long create(DepartmentForm form);

    void update(Long id, DepartmentForm form);

    void delete(Long id);
}
