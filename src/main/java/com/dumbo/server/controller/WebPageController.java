package com.dumbo.server.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Dumbo on 2018/8/16
 **/

@Controller
@Api(tags = "web页面跳转")
public class WebPageController {
    @RequestMapping("/index.html")
    public String goIndex(){
        return "index";
    }

    @RequestMapping("/about.html")
    public String goMe(){
        return "about";
    }

    @RequestMapping("/login.html")
    public String goLogin(){
        return "login";
    }

    @RequestMapping("/service-index.html")
    public String goService(){
        return "service-index";
    }

    @RequestMapping("/service-charts.html")
    public String goServiceChart(){
        return "service-charts";
    }

    @RequestMapping("/service-tables.html")
    public String goServiceTable(){
        return "service-tables";
    }
}
