package com.dumbo.server.service;

import com.dumbo.server.entity.Response;
import com.dumbo.server.entity.Sites;

/**
 * --监测点信息业务接口-
 *
 * Created by Dumbo on 2018/5/18
 **/

public interface SitesService {
    /*增*/
    public Response insertSite(Sites sites);

    /*删*/
    public Response deleteSite(int siteId);

    /*改*/
    public Response updateSite(Sites sites);

    /*查询一条记录*/
    public Response selectSite(int siteId);

    /*查询所有记录*/
    public Response selectAllSites();
}
