package com.dumbo.server.dao;

import com.dumbo.server.entity.AirData;
import com.dumbo.server.entity.WaterData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AirDataDao {
    int deleteByPrimaryKey(Integer dataId);

    int insert(AirData record);

    AirData selectByPrimaryKey(Integer dataId);

    int updateByPrimaryKey(AirData record);

    /*查询某站点最新空气质量监测数据*/
    AirData selectLatestData (String siteId);

    /*查询所有站点最新空气质量监测数据*/
    List<AirData> selectAllLatestData(int count);

    /*查询某站点最近一小时平均空气质量监测数据*/
    AirData selectPreOneHourDataAvg(String siteId);

    /*查询某站点某天每小时平均空气质量监测数据*/
    List<AirData> selectPerHourDataAvg(@Param("siteId") String siteId, @Param("date") String date);
}