package com.dumbo.server;

import com.dumbo.server.constant.SecretKey;
import com.dumbo.server.shiro.JwtFactory;
import com.dumbo.server.util.MD5Util;
import com.dumbo.server.util.RSAUtil;
import com.dumbo.server.util.ResponseUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.DatatypeConverter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {

    @Test
    public void test() {
        String refreshJwt=JwtFactory.createRefreshJwt(1+"");
        System.out.println(refreshJwt);

        try {//签名校验成功&未过期
            Claims claims=Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SecretKey.JWT_SECRET_KEY))
                    .parseClaimsJws(refreshJwt).getBody();
            //生成JWT并返回给客户端
            String newAccessJwt=JwtFactory.createAccessJwt(claims.getId(),"dumbo","general_user","all");
            System.out.println("签名校验成功");
        } catch (SignatureException e) {//签名校验失败
            System.out.println("签名校验失败");
        } catch (ExpiredJwtException e){//已过期
            System.out.println("AccessToken已过期,重新获取token");
        }
    }
}
