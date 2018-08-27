package com.dumbo.server.entity;

import com.dumbo.server.util.EncryptUtil;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by Dumbo on 2018/5/22
 **/

public class PaswToken implements AuthenticationToken {
    private String appId;
    private String password;
    private String timestamp;
    private String host;
    private String tokenKey;

    public PaswToken(String appId, String password, String timestamp, String host, String tokenKey) {
        this.appId = appId;
        this.timestamp = timestamp;
        this.host = host;
        this.password = EncryptUtil.encrypt(tokenKey);
        this.tokenKey = tokenKey;

    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }

    public Object getPrincipal() {
        return this.appId;
    }

    public Object getCredentials() {
        return this.password;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }
}
