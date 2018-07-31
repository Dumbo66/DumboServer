package com.dumbo.server.shiro;

import com.dumbo.server.constant.SecretKey;
import io.jsonwebtoken.*;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * Created by Dumbo on 2018/5/20
 **/

public class JwtFactory {
    //有效时间3min（3min后过期）
    private static final long EXP_MILLIS=3*60*1000;
    //可刷新时间（20天）
    private static final long REFRESH_MILLIS=20*24*60*60*1000;

    /**
     * 生成AccessJwt
     * @param userId 令牌ID
     * @param subject 客户标识(用户ID)
     * @param roles 用户角色
     * @param permissions 用户权限
     * @return JWT字符串
     */
    public static String createAccessJwt(String userId, String subject, String roles, String permissions){
        //当前时间
        Date now=new Date(System.currentTimeMillis());
        //过期时间
        Date exp=new Date(System.currentTimeMillis()+EXP_MILLIS);
        //秘钥
        byte[] secretBytes=DatatypeConverter.parseBase64Binary(SecretKey.JWT_SECRET_KEY);

        JwtBuilder jwtBuilder= Jwts.builder().setId(userId+"")
                .setSubject(subject)
                .setIssuer("dumbo_server")
                .setIssuedAt(now)
                .setExpiration(exp)
                .claim("roles",roles)
                .claim("permissions",permissions)
                .signWith(SignatureAlgorithm.HS256,secretBytes);
        return jwtBuilder.compact();
    }

    /**
     * 生成RefreshJwt
     * @param userId 令牌ID
     * @return JWT字符串
     */
    public static String createRefreshJwt(String userId){
        //当前时间
        Date now=new Date(System.currentTimeMillis());
        //过期时间
        Date exp=new Date(System.currentTimeMillis()+REFRESH_MILLIS);
        //秘钥
        byte[] secretBytes=DatatypeConverter.parseBase64Binary(SecretKey.JWT_SECRET_KEY);

        JwtBuilder jwtBuilder= Jwts.builder().setId(userId+"")
                .setIssuer("dumbo_server")
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256,secretBytes);
        return jwtBuilder.compact();
    }
}
