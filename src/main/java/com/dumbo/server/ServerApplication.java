package com.dumbo.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

/**
 *  --工程启动类--
 *
 *  导出war包时修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
 *  Created by Dumbo on 2018/4/1
 **/

@SpringBootApplication  // 等同于@Configuration+@EnableAutoConfiguration+@ComponentScan
@MapperScan("com.dumbo.server.dao") //扫描指定包下dao
@EnableCaching  //开启缓存功能
public class ServerApplication /*extends SpringBootServletInitializer */{

    /*一般启动类*/
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

//    /*导出war包时启动类*/
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        // 注意这里要指向原先用main方法执行的Application启动类
//        return builder.sources(ServerApplication.class);
//    }
}
