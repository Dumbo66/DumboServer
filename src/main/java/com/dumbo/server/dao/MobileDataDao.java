package com.dumbo.server.dao;

import com.dumbo.server.entity.MobileData;
import java.util.Date;
import java.util.List;

public interface MobileDataDao {
    /*增*/
    int insert(MobileData mobileData);

    /*删*/
    int deleteByDate(String date);

    /*改*/
    int update(MobileData mobileData);

    /*查*/
    List<MobileData> selectByDate(String date);
}