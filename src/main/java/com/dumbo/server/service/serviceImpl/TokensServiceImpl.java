package com.dumbo.server.service.serviceImpl;

import com.dumbo.server.shiro.JwtBean;
import com.dumbo.server.service.TokensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

/**
 * Created by Dumbo on 2018/5/18
 **/

public class TokensServiceImpl implements TokensService {
    private RedisTemplate redis;

    @Autowired
    public void setRedis(RedisTemplate redis){
        this.redis=redis;
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    /*创建一个token并关联上指定手机号*/
    @Override
    public JwtBean createToken(String userPhone) {
        //使用UUID作为源Token
        return null;
    }

    /*核查token是否有效*/
    @Override
    public boolean checkToken(String userPhone) {
        return false;
    }

    /*从鉴别信息字符串中解析Token*/
    @Override
    public JwtBean getToken(String authentication) {
        return null;
    }

    /*删除Token*/
    @Override
    public boolean deleteToken(String userPhone) {
        return false;
    }
}
