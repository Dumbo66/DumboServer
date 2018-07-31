package com.dumbo.server.util;

import com.dumbo.server.constant.ResponseCode;
import com.dumbo.server.entity.Response;

/**
 *  --Response工具类--
 *
 * Created by Dumbo on 2018/4/21
 **/

public class ResponseUtil {
    /*不带返回数据*/
    public static Response ok(String message) {
        return new Response(ResponseCode.OK, message);
    }
    /*带返回数据*/
    public static Response ok(String message,Object object) {
        return new Response(ResponseCode.OK,message,object);
    }

    public static Response unregistered() {
        return new Response(ResponseCode.UNREGISTERED, "账号未注册");
    }

    public static Response registered() {
        return new Response(ResponseCode.REGISTERED, "账号已注册");
    }

    public static Response registered(Object object) {
        return new Response(ResponseCode.REGISTERED, "账号已注册",object);
    }

    public static Response phoneOrPaswIsErrored() {
        return new Response(ResponseCode.PHONE_OR_PASW_ERROR, "账号或密码错误");
    }

    public static Response paswIsNull() {
        return new Response(ResponseCode.PASW_IS_NULL, "账号未设置密码");
    }

    public static Response tokenIsInvalid(){
        return new Response(ResponseCode.TOKEN_IS_INVALID,"令牌无效");
    }

    public static Response tokenIsExpired(){
        return new Response(ResponseCode.ACCESS_TOKEN_IS_EXPIRED,"access_token已过期,重新获取token");
    }

    public static Response loginAgain(){
        return new Response(ResponseCode.REFRESH_TOKEN_IS_EXPIRED,"refresh_token已过期,重新登录");
    }

    public static Response siteIsNull(){
        return new Response(ResponseCode.SITE_IS_NULL,"站点号不存在");
    }
}
