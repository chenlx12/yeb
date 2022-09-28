package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.MenuRole;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huyelin
 * @since 2022-09-22
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {
    //更新角色菜单
    Integer insertRecord(@Param("rid")Integer rid, @Param("mids")Integer[] mids);
}
