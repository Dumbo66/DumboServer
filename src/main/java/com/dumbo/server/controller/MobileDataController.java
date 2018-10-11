package com.dumbo.server.controller;

import com.dumbo.server.entity.MobileData;
import com.dumbo.server.entity.Response;
import com.dumbo.server.service.serviceImpl.MobileDataServiceImpl;
import com.dumbo.server.service.serviceImpl.WaterDataServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * --移动监测数据控制类
 *
 * Created by Dumbo on 2018/8/29
 **/

@RestController
@Api(tags = "移动监测数据接口")
@RequestMapping("/api/v1")
public class MobileDataController {

    private final MobileDataServiceImpl dataServiceImpl;

    @Autowired
    public MobileDataController(MobileDataServiceImpl dataServiceImpl) {
        this.dataServiceImpl = dataServiceImpl;
    }

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
