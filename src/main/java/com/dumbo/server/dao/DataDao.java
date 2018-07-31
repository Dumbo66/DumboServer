package com.dumbo.server.dao;

import com.dumbo.server.entity.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataDao {
    int insert(Data record);

    /*查询某站点最新监测数据*/
    Data selectLatestData (int site);

    /*查询所有站点最新监测数据*/
    List<Data> selectAllLatestData(int count);

    /*查询某站点最近一小时平均监测数据*/
    Data selectPreOneHourDataAvg(int site);

    /*查询某站点某天每小时平均监测数据*/
    List<Data> selectPerHourDataAvg(@Param("site") int site, @Param("date") String date);

}