package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.Employee;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huyelin
 * @since 2022-09-22
 */
public interface IEmployeeService extends IService<Employee> {
    /**
     * 获取所有员工（分页）
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    //获取工号
    RespBean maxWorkId();
    //添加员工
    RespBean insertEmployee(Employee employee);
    //查询员工
    List<Employee> getEmployee(Integer id);
    //查询账套
    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size);
}
