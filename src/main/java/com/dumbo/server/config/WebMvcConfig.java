package com.dumbo.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Dumbo on 2018/8/5
 *
 * 配置通过url访问本地照片
 **/

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler是指你想在url请求的路径
        //addResourceLocations是图片存放的真实路径
        registry.addResourceHandler("/pictures/**").addResourceLocations("file:C:\\Dumbo\\Pictures\\");
        super.addResourceHandlers(registry);
    }
}
