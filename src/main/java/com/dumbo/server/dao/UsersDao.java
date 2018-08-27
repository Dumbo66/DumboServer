package com.dumbo.server.dao;

import com.dumbo.server.entity.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersDao {
    /*增*/
    int insert(Users users);

    /*删*/
    int deleteByPhone(String phone);

    /*改*/
    int update(Users users);

    /*通过电话号码查询*/
    Users selectByPhone(String phone);

    /*通过用户ID查询*/
    Users selectByUserId(int userId);

}