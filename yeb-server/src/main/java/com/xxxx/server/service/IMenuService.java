package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huyelin
 * @since 2022-09-22
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 根据角色id查询菜单列表
     * @return
     */
    List<Menu> getMenusByAdminId();

    /**
     * 根据角色,获取菜单列表
     */
    List<Menu> getMenusWithRole();
}
