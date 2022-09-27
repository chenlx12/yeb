package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Menu;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huyelin
 * @since 2022-09-22
 */

public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 通过用户id查询菜单列表
     */
    List<Menu> getMenusByAdminId(Integer id);
    //根据角色获得菜单列表
    List<Menu> getMenusWithRole();
}
