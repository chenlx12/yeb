package com.xxxx.server.controller;


import com.xxxx.server.pojo.Menu;
import com.xxxx.server.service.IAdminService;
import com.xxxx.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/system/cfg")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    /**
     * 虽然是通过用户id查询菜单列表，但是没有传id
     * 如果用户正常能登录以后，用户的相关信息一般在后端获取的，而不是前端传进来的，可能出问题
     * 通过全局变量获取到，用户id
     */
    @ApiOperation(value = "通过用户Id查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenusByAdminId(){
        return menuService.getMenusByAdminId();
    }
}
