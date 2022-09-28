package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.server.pojo.Employee;
import io.lettuce.core.dynamic.annotation.Param;

import java.time.LocalDate;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huyelin
 * @since 2022-09-22
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 获取所有员工（分页）
     * @param page
     * @param employee
     * @param beginDateScope
     * @return
     */
    IPage<Employee> getEmployeeByPage(Page<Employee> page,
                                      @Param("employee") Employee employee,
                                      @Param("beginDateScope") LocalDate[] beginDateScope);
}

