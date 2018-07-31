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
@Api(tags = "监测数据接口")
@RequestMapping("/api/v1")
public class DataController {

    private final DataServiceImpl dataServiceImpl;

    @Autowired
    public DataController(DataServiceImpl dataServiceImpl) {
        this.dataServiceImpl = dataServiceImpl;
    }

    /*****************************定点监测数据*******************************/
    @GetMapping("/latest_data")
    @ApiOperation("查询某站点最新监测数据")
    public Response selectLatestData(@RequestParam int site){
        return dataServiceImpl.selectLatestData(site);
    }

    @GetMapping("/all_latest_data")
    @ApiOperation("查询多个站点最新监测数据")
    public Response selectAllLatestData(@RequestParam int count){
        return dataServiceImpl.selectAllLatestData(count);
    }

    @GetMapping("/pre_one_hour_data")
    @ApiOperation("查询某站点最近一小时平均监测数据")
    public Response selectPreOneHourDataAvg(@RequestParam int site){
        return dataServiceImpl.selectPreOneHourDataAvg(site);
    }

    @GetMapping("/per_hour_data")
    @ApiOperation("查询某站点某天每小时平均监测数据")
    public Response selectPerHourDataAvg(@RequestParam int site, @RequestParam String date){
        return dataServiceImpl.selectPerHourDataAvg(site,date);
    }

    /*****************************移动监测数据*******************************/
    @PostMapping("/mobile_data")
    @ApiOperation("增加移动监测数据")
    public Response insertMobileData(@RequestBody MobileData mobileData){
        return dataServiceImpl.insertMobileData(mobileData);
    }

    @DeleteMapping("/mobile_data")
    @ApiOperation("删除某天移动监测数据")
    public Response deleteMobileData(@RequestParam String date){
        return dataServiceImpl.deleteMobileData(date);
    }

    @PutMapping("/mobile_data")
    @ApiOperation("修改某天移动监测数据")
    public Response updateMobileData(@RequestBody MobileData mobileData){
        return dataServiceImpl.updateMobileData(mobileData);
    }

    @GetMapping("/mobile_data")
    @ApiOperation("查询某天移动监测数据")
    public Response selectMobileData(@RequestParam String date){
        return dataServiceImpl.selectMobileData(date);
    }
}
