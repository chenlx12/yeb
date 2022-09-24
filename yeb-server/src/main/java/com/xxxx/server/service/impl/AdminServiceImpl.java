package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.config.security.JwtTokenUtil;
import com.xxxx.server.mapper.AdminMapper;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huyelin
 * @since 2022-09-22
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String)request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code)||!captcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码输入错误，请重新输入！");
        }
        //security主要是通过：UserDetailsService里面的username来实现登录的
        //将浏览器传过来的username，放进去。 返回的是userDetails用户详细信息（账号、密码、权限等等）
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        //判断传过来的username是否为空 或者 （浏览器输入的和数据库密码不一致） 则密码或者用户名是错的
        if(userDetails==null ||passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error("用户名或者密码不正确");
        }
        //判断是否禁用
        if(!userDetails.isEnabled()){
            return RespBean.error("账号被禁用");
        }
        /**
         * 更新security登录用户对象
         * 参数：userDetails,凭证密码null,权限列表
         *
         * security的全局里面
         */

        UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken
                (userDetails,null,userDetails.getAuthorities());
        //上下文持有人
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        /**
         * 生成token返回给前端
         * 如果以上都没有进入判断，说明用户和密码是正确的：就可以拿到jwt令牌了：
         * 根据用户信息生成令牌
         */

        String token = jwtTokenUtil.generateToken(userDetails);
        //有了token，就用map返回：
        Map<String,String> tokenMap=new HashMap<>();
        //将token返回去
        tokenMap.put("token",token);
        //头部信息也返回去前端，让他放在请求头里面
        tokenMap.put("tokenHead",tokenHead);
        return RespBean.success("登陆成功",tokenMap);
    }

    @Override
    public Admin getAdminByUserName(String username) {
        /*
         * 查询一个（泛型是admin。equals（提示：表的字段"username"：username));
         * 1.用户名去匹配
         * 2.账户是否禁用
         */
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username).eq("enabled",true));
    }
}
