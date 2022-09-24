package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huyelin
 * @since 2022-09-22
 */
public interface IAdminService extends IService<Admin> {
    //登录后返回token
    RespBean login(String username, String password, String code, HttpServletRequest request);
    //根据用户名获取用户
    Admin getAdminByUserName(String username);
}
