package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.mapper.MenuRoleMapper;
import com.xxxx.server.pojo.MenuRole;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huyelin
 * @since 2022-09-22
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {
    @Autowired
    private MenuRoleMapper menuRoleMapper;
    //更新角色菜单
    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        //删除菜单
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        //添加菜单：
        if(null == mids || 0==mids.length){
            return RespBean.success("更新成功");
        }
        Integer integer = menuRoleMapper.insertRecord(rid, mids);
        if(integer == mids.length){
            return RespBean.success("更新成功");
        }

        return RespBean.error("更新失败");
    }
}
