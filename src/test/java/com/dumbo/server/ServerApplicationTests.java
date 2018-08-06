package com.dumbo.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
