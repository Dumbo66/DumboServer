package com.dumbo.server.dao;


import com.dumbo.server.entity.WaterData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WaterDataDao {
    int insert(WaterData record);

    /*查询某站点最新水质监测数据*/
    WaterData selectLatestData (String siteId);

    /*查询所有站点最新水质监测数据*/
    List<WaterData> selectAllData();

    /*查询所有站点最新水质监测数据*/
    List<WaterData> selectAllLatestData(int count);

    /*查询某站点最近一小时平均水质监测数据*/
    WaterData selectPreOneHourDataAvg(String siteId);

    /*查询某站点某天每小时平均水质监测数据*/
    List<WaterData> selectPerHourDataAvg(@Param("siteId") String siteId, @Param("date") String date);

}
