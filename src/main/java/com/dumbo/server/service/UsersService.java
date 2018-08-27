package com.dumbo.server.service;

import com.dumbo.server.entity.Response;
import com.dumbo.server.entity.Users;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * --用户业务接口-
 *
 * Created by Dumbo on 2018/5/18
 **/

public interface UsersService {
    /*手机号密码注册*/
    Response registerByPasw(Map<String,Object> map,MultipartFile file);

    /*删除某用户所有信息*/
    Response deleteUser(String phone);

    /*修改某用户信息*/
    Response updateUser(Users users);

    /*查询某用户信息*/
    Response selectUser(String phone);

    /*手机号密码登录*/
    Response loginByPasw(Users users);

    /*手机号验证码登录*/
    Response loginByVercode(Users users);

    /*手机号密码登录*/
    Response verifyAccessJwt(String jwt);

}
