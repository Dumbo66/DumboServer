package com.dumbo.server.controller;

import com.dumbo.server.entity.Response;
import com.dumbo.server.service.serviceImpl.MomentsServiceImpl;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by Dumbo on 2018/7/22
 **/

@RestController
@Api(tags = "Moments动态数据接口")
@RequestMapping("/api/v1")
public class MomentsController {
    private final MomentsServiceImpl momentsServiceImpl;

    @Autowired
    public MomentsController(MomentsServiceImpl momentsServiceImpl) {
        this.momentsServiceImpl = momentsServiceImpl;
    }

    @PostMapping("/moments")
    @ApiOperation("上传Moments动态内容（只允许上传大小在1M以内的图片）")
    private Response postMoments(@RequestParam Map<String,Object> map, @RequestParam MultipartFile[] files){
        return momentsServiceImpl.postMoments(map,files);
    }

    @GetMapping("/moments")
    @ApiOperation("获取Moments动态内容（图文）")
    private Response getMoments(@RequestParam int pageNum,@RequestParam int pageSize){
        return momentsServiceImpl.getMoments(pageNum,pageSize);
    }

}
