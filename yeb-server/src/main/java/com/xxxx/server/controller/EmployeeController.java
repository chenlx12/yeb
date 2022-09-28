package com.xxxx.server.controller;


import com.xxxx.server.pojo.*;
import com.xxxx.server.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huyelin
 * @since 2022-09-22
 */
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {
    @Autowired
    private IEmployeeService iEmployeeService;
    @Autowired
    private IPoliticsStatusService politicsStatusService;
    @Autowired
    private INationService nationService;

    @Autowired
    private IJoblevelService joblevelService;
    @Autowired
    private IPositionService positionService;
    @Autowired
    private IDepartmentService departmentService;


    @ApiOperation(value = "获取所有的员工(分页)")
    @GetMapping("/")
    /**
     * currentPage:打开时，默认当前是第几页
     * size，每页有多少条数据
     */
    public RespPageBean getEmployee(@RequestParam(defaultValue = "1" ) Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    Employee employee,
                                    LocalDate[] beginDateScope
    ){
        return iEmployeeService.getEmployeeByPage(currentPage,size,employee,beginDateScope);

    }

    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping("/politicsstatus")
    public List<PoliticsStatus> getAllPoliticsStatus() {
        return politicsStatusService.list();
    }

    @ApiOperation(value = "获取所有民族")
    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.list();
    }
    @ApiOperation(value = "获取所有职称")
    @GetMapping("/joblevels")
    public List<Joblevel> getAllJobLevels() {
        return joblevelService.list();
    }

    @ApiOperation(value = "获取所有职位")
    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionService.list();
    }


    @ApiOperation(value = "获取所有部门")
    @GetMapping("/deps")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }
    @ApiOperation(value = "获取工号")
    @GetMapping("/maxWorkID")
    public RespBean maxWorkID() {
        return iEmployeeService.maxWorkId();
    }
    @ApiOperation(value = "添加员工")
    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee) {
        return iEmployeeService.insertEmployee(employee);
    }

    @ApiOperation(value = "更新员工")
    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee){
        if(iEmployeeService.updateById(employee)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除员工")
    @DeleteMapping("/{id}")
    public RespBean deleteEmp(@PathVariable Integer id){
        if (iEmployeeService.removeById(id)){
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }


}
