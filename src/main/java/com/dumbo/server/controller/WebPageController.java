package com.dumbo.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Dumbo on 2018/8/16
 **/

@Controller
public class WebPageController {
    @RequestMapping("/index.html")
    public String goIndex(){
        return "index";
    }

    @RequestMapping("/me.html")
    public String goMe(){
        return "me";
    }

    @RequestMapping("/console.html")
    public String goConsole(){
        return "console";
    }

    @RequestMapping("/service.html")
    public String goService(){
        return "service";
    }

    @RequestMapping("/app_install.html")
    public String goAppInstall(){
        return "app_install";
    }

    @RequestMapping("/login.html")
    public String goLogin(){
        return "login";
    }
}
