package com.dumbo.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * --用户实体类--
 *
 * Created by Dumbo on 2018/4/22
 **/

public class Users {
    @ApiModelProperty(value = "11位有效手机号")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private String phone;

    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiModelProperty(value = "用户姓名",allowEmptyValue = true)
    private String nick_name;

    @ApiModelProperty(value = "用户性别（只能：男/女/保密）",allowEmptyValue = true)
    private String sex;

    @ApiModelProperty(value = "用户生日（如1996-09-03）",allowEmptyValue = true)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date birthday;

    @ApiModelProperty(value = "以Base64编码的经RSA（2048）加密的密码MD5",allowEmptyValue = true)
    private String password;

    @ApiModelProperty(value = "用户头像URL",allowEmptyValue = true)
    private String iconUrl;

    public String getPhone() {
        return phone;
    }

    public Integer getId() {
        return id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public String getSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}