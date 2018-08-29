package com.dumbo.server.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * --空气质量监测数据管理类
 *
 * Created by Dumbo on 2018/8/29
 **/

@RestController
@Api(tags = "空气质量监测数据接口")
@RequestMapping("/api/v1/data")
public class AirDataController {
}
