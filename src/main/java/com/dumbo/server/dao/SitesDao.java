package com.dumbo.server.dao;

import com.dumbo.server.entity.Sites;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SitesDao {
    int deleteByPrimaryKey(String siteId);

    int insert(Sites record);

    int insertSelective(Sites record);

    Sites selectByPrimaryKey(String siteId);

    int updateByPrimaryKeySelective(Sites record);

    int updateByPrimaryKey(Sites record);

    /*查所有记录*/
    List<Sites> selectAllSites();
}