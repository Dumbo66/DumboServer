package com.dumbo.server.service;

import com.dumbo.server.entity.Response;

/**
 * Created by Dumbo on 2018/8/29
 **/

public interface AirDataService {
    /*查询某站点最新空气质量监测数据*/
    public Response selectLatestData(String siteId);

    /*查询所有站点最新空气质量监测数据*/
    public Response selectAllLatestData(int count);

    /*查询某站点最近一小时平均空气质量监测数据*/
    public Response selectPreOneHourDataAvg(String siteId);

    /*查询某站点某天每小时平均空气质量监测数据*/
    public Response selectPerHourDataAvg(String siteId, String date);
}
