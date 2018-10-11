package com.dumbo.server.controller;

import com.dumbo.server.entity.Response;
import com.dumbo.server.service.serviceImpl.AirDataServiceImpl;
import com.dumbo.server.service.serviceImpl.WaterDataServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * --空气质量监测数据管理类
 *
 * Created by Dumbo on 2018/8/29
 **/

@RestController
@Api(tags = "空气质量监测数据接口")
@RequestMapping("/api/v1")
public class AirDataController {

    private final AirDataServiceImpl dataServiceImpl;

    @Autowired
    public AirDataController(AirDataServiceImpl dataServiceImpl) {
        this.dataServiceImpl = dataServiceImpl;
    }

    @GetMapping("/latest_air_data")
    @ApiOperation("查询某站点最新空气质量监测数据")
    public Response selectLatestData(@RequestParam String siteId){
        return dataServiceImpl.selectLatestData(siteId);
    }

    @GetMapping("/all_latest_air_data")
    @ApiOperation("查询多个站点最新空气质量监测数据")
    public Response selectAllLatestData(@RequestParam int count){
        return dataServiceImpl.selectAllLatestData(count);
    }

    @GetMapping("/pre_one_hour_air_data")
    @ApiOperation("查询某站点最近一小时平均空气质量监测数据")
    public Response selectPreOneHourDataAvg(@RequestParam String siteId){
        return dataServiceImpl.selectPreOneHourDataAvg(siteId);
    }

    @GetMapping("/per_hour_air_data")
    @ApiOperation("查询某站点某天每小时平均空气质量监测数据")
    public Response selectPerHourDataAvg(@RequestParam String siteId, @RequestParam String date){
        return dataServiceImpl.selectPerHourDataAvg(siteId,date);
    }
}
