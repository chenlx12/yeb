package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.Menu;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    //根据用户id查询角色列表
    List<Role> getRoles(Integer adminId);

}
