package com.xxxx.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//lombok:无参构造、全参构造
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    //状态码
    private long code;
    //提示信息
    private String message;
    //可能返回一下对象
    private Object obj;
    //可以定义一些返回的东西了：

    /**
     * 成功返回结果
     * @param message
     * @return
     */
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }

    /**
     * 成功返回结果
     * @param message
     * @param obj
     * @return
     */
    public static RespBean success(String message,Object obj){
        return new RespBean(200,message,obj);
    }

    /**
     * 失败返回的结果
     * @param message
     * @return
     */
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }

    /**
     * 失败返回结果：
     * @param message
     * @param obj
     * @return
     */
    public static RespBean error(String message,Object obj){
        return new RespBean(500,message,obj);
    }
}

