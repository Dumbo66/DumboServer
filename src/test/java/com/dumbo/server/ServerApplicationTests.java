package com.dumbo.server;

import com.dumbo.server.util.DataDecodeUtil;
import com.dumbo.server.util.EncryptUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {

    @Test
    public void test() {
        String str="01032B0A370A370A370A370A37000300000000000000000A370A37000000000000000000000000000000004E270E0A006E4575023B001E1205050D2D3B03E7B00C";
        DataDecodeUtil.decode(str);
    }
}
