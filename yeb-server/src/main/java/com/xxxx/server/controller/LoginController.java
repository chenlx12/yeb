package com.xxxx.server.controller;

import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.AdminLoginParam;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(tags = "LoginController")
@RestController
public class LoginController {
    //注入service
    @Autowired
    private IAdminService adminService;
    /**
     * 获取当前登录的用户信息
     */

    //用swagger注解代表注释了
    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        //service层login登录方法
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }

    /**
     * 获取当前登录的用户信息
     */
    @ApiOperation(value = "获取当前用户登录的信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){
        if(null==principal){
            return null;
        }
        String username = principal.getName();
        //根据用户名获取完全的用户对象
        Admin admin = adminService.getAdminByUserName(username);
        //但是用户的密码不会返回给浏览器
        admin.setPassword(null);
        return admin;
    }

    /**
     * 退出登录
     * @return
     */
    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.success("注销成功");
    }
}
