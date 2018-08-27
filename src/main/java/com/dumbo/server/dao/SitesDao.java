package com.dumbo.server.dao;

import com.dumbo.server.entity.Sites;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SitesDao {
    /*增*/
    int insert(Sites sites);

    /*删*/
    int deleteBySiteId(int siteId);

    /*改*/
    int update(Sites sites);

    /*查一条记录*/
    Sites selectBySiteId(int siteId);

    /*查所有记录*/
    List<Sites> selectAllSites();
}