package com.dumbo.server.service;

import com.dumbo.server.entity.MobileData;
import com.dumbo.server.entity.Response;

/**
 * Created by Dumbo on 2018/8/29
 **/

public interface MobileDataService {
    /*增加移动监测数据*/
    public Response insertMobileData(MobileData mobileData);

    /*删除移动监测数据*/
    public Response deleteMobileData(String date);

    /*修改移动监测数据*/
    public Response updateMobileData(MobileData mobileData);

    /*查询某天移动监测数据*/
    public Response selectMobileData(String date);
}
