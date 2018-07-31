package com.dumbo.server.shiro;

import com.dumbo.server.dao.UsersDao;
import com.dumbo.server.entity.Users;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Dumbo on 2018/5/20
 **/

public class MyShiroRealm extends AuthorizingRealm {
    private UsersDao usersDao;

    @Autowired
    public MyShiroRealm(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public MyShiroRealm(){};

    @Override
    /*检测用户权限*/
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    /*验证用户*/
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户ID
        int userId= (int) token.getPrincipal();
        System.out.println("userId="+userId);

        //到数据库查找用户是否存在
        Users users =usersDao.selectById(userId);
        if(users !=null){
//            if()
        }
        return new SimpleAuthenticationInfo(userId,token.getCredentials(),getName());
    }
}
