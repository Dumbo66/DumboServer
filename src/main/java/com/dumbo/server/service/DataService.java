package com.dumbo.server.service;

import com.dumbo.server.entity.MobileData;
import com.dumbo.server.entity.Response;

import java.util.Date;

/**
 * --监测数据业务接口--
 *
 * Created by Dumbo on 2018/5/18
 **/

public interface DataService {
    /*查询某站点最新监测数据*/
    public Response selectLatestData(int site);

    /*查询所有站点最新监测数据*/
    public Response selectAllLatestData(int count);

    /*查询某站点最近一小时平均监测数据*/
    public Response selectPreOneHourDataAvg(int site);

    /*查询某站点某天每小时平均监测数据*/
    public Response selectPerHourDataAvg(int site, String date);


    /*增加移动监测数据*/
    public Response insertMobileData(MobileData mobileData);

    /*删除移动监测数据*/
    public Response deleteMobileData(String date);

    /*修改移动监测数据*/
    public Response updateMobileData(MobileData mobileData);

    /*查询某天移动监测数据*/
    public Response selectMobileData(String date);
}
