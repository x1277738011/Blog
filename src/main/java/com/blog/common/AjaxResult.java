package com.blog.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class AjaxResult implements Serializable {
    //状态码
    private Integer code;
    //状态码描述信息
    private String msg;
    //返回的数据
    private Object data;
/**
 * 操作成功
 *
 */
    public static AjaxResult success(Object data){
        AjaxResult result = new AjaxResult();
        result.setCode(200);
        result.setMsg("");
        result.setData(data);
        return result;
    }
    public static AjaxResult success(Integer code,Object data){
        AjaxResult result = new AjaxResult();
        result.setCode(code);
        result.setMsg("");
        result.setData(data);
        return result;
    }
    public static AjaxResult success(Integer code,String msg,Object data){
        AjaxResult result = new AjaxResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
    /**
     * 操作失败
     */
    public static AjaxResult fail(Integer code,String msg){
        AjaxResult result = new AjaxResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}
