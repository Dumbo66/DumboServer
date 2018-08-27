package com.dumbo.server;

import com.dumbo.server.util.EncryptUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {

    @Test
    public void test() {
        String str="90977299zhux";
        System.out.println(EncryptUtil.getMD5Str(str));
        System.out.println(EncryptUtil.encrypt(EncryptUtil.getMD5Str(str)));
    }
}
