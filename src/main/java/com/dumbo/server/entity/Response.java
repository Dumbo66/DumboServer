package com.dumbo.server.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * --响应实体类--
 *
 * Created by Dumbo on 2018/4/22
 **/

public class Response {
    //返回http状态码
    private int code;
    //返回信息
    private String message;
    //返回数据
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Response(int code,String message){
        this.code=code;
        this.message=message;
    }

    public Response(int code,String message,Object object){
        this.code=code;
        this.message=message;
        this.data =object;
    }
}
