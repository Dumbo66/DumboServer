package com.dumbo.server.controller;

import com.dumbo.server.entity.MobileData;
import com.dumbo.server.entity.Response;
import com.dumbo.server.service.serviceImpl.DataServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * --监测数据控制类--
 *
 * Created by Dumbo on 2018/4/21
 **/

@RestController
@Api(tags = "水质监测数据接口")
@RequestMapping("/api/v1/data")
public class WaterDataController {

    private final DataServiceImpl dataServiceImpl;

    @Autowired
    public WaterDataController(DataServiceImpl dataServiceImpl) {
        this.dataServiceImpl = dataServiceImpl;
    }

    @GetMapping("/latest_water_data")
    @ApiOperation("查询某站点最新水质监测数据")
    public Response selectLatestData(@RequestParam String siteId){
        return dataServiceImpl.selectLatestData(siteId);
    }

    @GetMapping("/all_latest_water_data")
    @ApiOperation("查询多个站点最新睡姿监测数据")
    public Response selectAllLatestData(@RequestParam int count){
        return dataServiceImpl.selectAllLatestData(count);
    }

    @GetMapping("/pre_one_hour_water_data")
    @ApiOperation("查询某站点最近一小时平均水质监测数据")
    public Response selectPreOneHourDataAvg(@RequestParam String siteId){
        return dataServiceImpl.selectPreOneHourDataAvg(siteId);
    }

    @GetMapping("/per_hour_water_data")
    @ApiOperation("查询某站点某天每小时平均水质监测数据")
    public Response selectPerHourDataAvg(@RequestParam String siteId, @RequestParam String date){
        return dataServiceImpl.selectPerHourDataAvg(siteId,date);
    }
}
