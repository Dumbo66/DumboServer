package com.dumbo.server.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by Dumbo on 2018/5/22
 **/

public class JwtToken implements AuthenticationToken {
    private int userId; //用户ID
    private String userIp; //用户IP
    private String deviceInfo; //用户设备信息
    private String jwt; //Json Web Token值

    public JwtToken(int userId, String userIp, String deviceInfo, String jwt){
        this.userId=userId;
        this.userIp=userIp;
        this.deviceInfo=deviceInfo;
        this.jwt=jwt;
    }

    @Override
    //获取用户ID
    public Object getPrincipal() {
        return this.userId;
    }

    @Override
    //获取凭证
    public Object getCredentials() {
        return this.jwt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
