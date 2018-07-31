package com.dumbo.server.controller;

import com.dumbo.server.entity.Response;
import com.dumbo.server.entity.Sites;
import com.dumbo.server.service.serviceImpl.SitesServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * --监测点信息控制类--
 *
 * Created by Dumbo on 2018/4/24
 **/

@RestController
@Api(tags = "监测点信息接口")
@RequestMapping("/v1")
public class SitesController {
    private final SitesServiceImpl sitesServiceImpl;

    @Autowired
    public SitesController(SitesServiceImpl sitesServiceImpl) {
        this.sitesServiceImpl = sitesServiceImpl;
    }

    @PostMapping("/sites")
    @ApiOperation("增加一条监测点信息")
    public Response insertSite(@RequestBody Sites sites){
        return sitesServiceImpl.insertSite(sites);
    }

    @DeleteMapping("/sites")
    @ApiOperation("删除一条监测点信息")
    public Response deleteSite(@RequestParam int site){
        return sitesServiceImpl.deleteSite(site);
    }

    @PutMapping("/sites")
    @ApiOperation("修改一条监测点信息")
    public Response updateSite(@RequestBody Sites sites){
        return sitesServiceImpl.updateSite(sites);
    }

    @GetMapping("/sites")
    @ApiOperation("查询一条监测点信息")
    public Response selectSite(@RequestParam int site){
        return sitesServiceImpl.selectSite(site);
    }

    @GetMapping("/all_sites")
    @ApiOperation("查询所有监测点信息")
    public Response selectAllSites(){
        return sitesServiceImpl.selectAllSites();
    }

}
