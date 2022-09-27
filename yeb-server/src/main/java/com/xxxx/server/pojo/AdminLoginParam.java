package com.xxxx.server.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户登录实体类：
 * 专门用来专递前端传给我们的用户名和密码
 * 登录只需要给后端传用户名和密码
 */
//lombok:
@Data
@EqualsAndHashCode(callSuper = false)
//具体的作用是开启链式编程，让我们写代码更加方便。
@Accessors(chain = true)
//接口文档：swagger:
@ApiModel(value = "AdminLogin对象",description = "")
public class AdminLoginParam {
    @ApiModelProperty(value = "用户名",required=true)
    private String username;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
    @ApiModelProperty(value = "验证码",required = true)
    private String code;
}
