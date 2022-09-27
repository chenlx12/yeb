package com.xxxx.server.config.security.component;

import com.xxxx.server.pojo.Menu;
import com.xxxx.server.pojo.Role;
import com.xxxx.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

//权限控制
//根据请求url分析请求所需的角色
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IMenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 获取请求的URL
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        //根据角色查询所有的菜单
        List<Menu> menus = menuService.getMenusWithRole();
        for (Menu menu : menus) {
            //判断请求URL与菜单中的URL能否匹配上。
            if(antPathMatcher.match(menu.getUrl(),requestUrl)){
                //如果能匹配上，则返回菜单URL所对应的所有角色是
                String[] strings = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                //返回能访问该URL的所有角色
                return SecurityConfig.createList(strings);
            }
        }
        //没匹配的URL默认登录即可访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
