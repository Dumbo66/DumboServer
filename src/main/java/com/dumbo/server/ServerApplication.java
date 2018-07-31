package com.dumbo.server;

import com.dumbo.server.constant.Path;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.File;

/**
 *  --工程启动类--
 *
 * Created by Dumbo on 2018/4/1
 **/

@SpringBootApplication  // 等同于@Configuration+@EnableAutoConfiguration+@ComponentScan
@MapperScan("com.dumbo.server.dao") //扫描指定包下dao
@EnableCaching  //开启缓存功能
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
