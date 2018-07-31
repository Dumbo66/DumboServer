package com.dumbo.server.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * --Shiro配置--
 *
 * Created by Dumbo on 2018/5/20
 **/

@Configuration
public class ShiroConfig {
//    @Bean
//    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
//        System.out.println("ShiroConfig.shiroFilter()");
//        //实例化ShiroFilterFactoryBean
//        ShiroFilterFactoryBean shiroFilterFactory=new ShiroFilterFactoryBean();
//        shiroFilterFactory.setSecurityManager(securityManager);
//
//        // 添加自己的过滤器,取名为"JwtFilter"
//        Map<String,Filter> filter=new LinkedHashMap<>();
//        filter.put("JwtFilter",new JwtFilter());
//        shiroFilterFactory.setFilters(filter);
//
//        Map<String ,String> filterChainRule=new LinkedHashMap<>();
//        //定义不会被拦截的链接，从上往下顺序执行判断
//        // 所有请求通过我们自己的JWT Filter
//        filterChainRule.put("/**","JwtFilter");
//        shiroFilterFactory.setFilterChainDefinitionMap(filterChainRule);
//
//        return shiroFilterFactory;
//    }
//
//    public SecurityManager securityManager(){
//        DefaultSecurityManager securityManager=new DefaultSecurityManager();
//        securityManager.setRealm(myShiroRealm());
//        return securityManager;
//    }
//
//    public MyShiroRealm myShiroRealm(){
//        return new MyShiroRealm();
//    }
}
