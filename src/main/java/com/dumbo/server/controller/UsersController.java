package com.dumbo.server.controller;

import com.dumbo.server.entity.Response;
import com.dumbo.server.entity.Users;
import com.dumbo.server.service.serviceImpl.UsersServiceImpl;
import com.dumbo.server.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * --用户控制类--
 *
 * Created by dumbo on 2018/4/21
 **/

@RestController
@Api(tags = "登录注册接口")
@RequestMapping("/api/v1")
public class UsersController {

    private final UsersServiceImpl usersServiceImpl;

    @Autowired
    public UsersController(UsersServiceImpl usersServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;
    }

    @PostMapping("/users")
    @ApiOperation("手机号密码注册")
    public Response  registerByPasw(@RequestParam Map<String,Object> map, @RequestParam MultipartFile file){
        return usersServiceImpl.registerByPasw(map,file);
    }

    @DeleteMapping("/users")
    @ApiOperation("删除某用户所有信息")
    public Response deleteUser(@RequestParam String phone){
        return usersServiceImpl.deleteUser(phone);
    }

    @PutMapping("/users")
    @ApiOperation("修改某用户信息")
    public Response  updateUser(@RequestBody Users users){
        return usersServiceImpl.updateUser(users);
    }

    @GetMapping("/users")
    @ApiOperation("查询某用户信息")
    public Response selectUser(@RequestParam String phone){
        return usersServiceImpl.selectUser(phone);
    }

    @PostMapping("/ver_codes")
    @ApiOperation("手机号验证码登录")
    public Response  loginByVerCode(@RequestBody Users users){
        return usersServiceImpl.loginByVercode(users);
    }

    @PostMapping("/tokens")
    @ApiOperation("手机号密码登录")
    public Response loginByPasw(@RequestBody Users users){
        return usersServiceImpl.loginByPasw(users);
    }

    @DeleteMapping("/tokens")
    @ApiOperation("退出登录")
    public Response exitLogin(){
        return ResponseUtil.ok("退出登录成功");
    }

    @PostMapping("/access_token")
    @ApiOperation("保持登录状态")
    public Response accessJwt(@RequestParam String accessJwt){
        System.out.println("你好"+accessJwt);
        return usersServiceImpl.verifyAccessJwt(accessJwt);
    }

    @PostMapping("/refresh_token")
    @ApiOperation("保持登录状态")
    public Response refreshJwt(@RequestParam String refreshJwt){
        return usersServiceImpl.verifyRefreshJwt(refreshJwt);
    }
}
