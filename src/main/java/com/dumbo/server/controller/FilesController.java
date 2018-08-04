package com.dumbo.server.controller;

import com.dumbo.server.entity.Response;
import com.dumbo.server.service.serviceImpl.FilesServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Dumbo on 2018/7/22
 **/

@RestController
@Api(tags = "文件上传接口")
@RequestMapping("/api/v1")
public class FilesController {
    private final FilesServiceImpl filesServiceImpl;

    @Autowired
    public FilesController(FilesServiceImpl filesServiceImpl) {
        this.filesServiceImpl = filesServiceImpl;
    }

    @PostMapping("/pictures")
    @ApiOperation("上传图片（只允许上传大小在1M以内的图片）")
    private Response uploadPictures(@RequestParam MultipartFile[] files){
        return filesServiceImpl.uploadPictures(files);
    }
}
