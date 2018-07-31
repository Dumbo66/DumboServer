package com.dumbo.server.service;

import com.dumbo.server.shiro.JwtBean;

/**
 * --Tokens操作业务接口-
 *
 * Created by Dumbo on 2018/5/18
 **/

public interface TokensService {
    /*创建一个token并关联上指定手机号*/
    public JwtBean createToken(String userPhone);

    /*核查token是否有效*/
    public boolean checkToken(String userPhone);

    /*从鉴别信息字符串中解析Token*/
    public JwtBean getToken(String authentication);

    /*删除Token*/
    public boolean deleteToken(String userPhone);
}
