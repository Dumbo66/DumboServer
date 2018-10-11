package com.dumbo.server.service;

import com.dumbo.server.entity.MobileData;
import com.dumbo.server.entity.Response;
import com.dumbo.server.entity.WaterData;

import java.util.Date;
import java.util.List;

/**
 * --监测数据业务接口--
 *
 * Created by Dumbo on 2018/5/18
 **/

public interface WaterDataService {
    /*查询某站点最新定点监测数据*/
    public Response selectLatestData(String siteId);

    /*查询所有监测数据*/
    public List<WaterData> selectAllData();

    /*查询所有站点最新定点监测数据*/
    public Response selectAllLatestData(int count);

    /*查询某站点最近一小时平均定点监测数据*/
    public Response selectPreOneHourDataAvg(String siteId);

    /*查询某站点某天每小时平均定点监测数据*/
    public Response selectPerHourDataAvg(String siteId, String date);
}
