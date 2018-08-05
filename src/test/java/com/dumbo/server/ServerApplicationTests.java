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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {

    @Test
    public void test() {
      String str="&/pictures/img_20180805_104511_1166.jpg&/pictures/img_20180805_104511_3660.jpg";
        String[] ss=str.split("&");
        for (int i=0;i<ss.length ;i++){
            System.out.println(ss[i]);
        }
    }
}
