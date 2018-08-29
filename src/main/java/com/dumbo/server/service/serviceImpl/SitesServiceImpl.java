package com.dumbo.server.service.serviceImpl;

import com.dumbo.server.dao.SitesDao;
import com.dumbo.server.entity.Response;
import com.dumbo.server.entity.Sites;
import com.dumbo.server.service.SitesService;
import com.dumbo.server.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dumbo on 2018/4/24
 **/

@Service
public class SitesServiceImpl implements SitesService {
    private final SitesDao sitesDao;

    @Autowired
    public SitesServiceImpl(SitesDao sitesDao) {
        this.sitesDao = sitesDao;
    }

    /*增*/
    @Override
    public Response insertSite(Sites sites){
        sitesDao.insert(sites);
        return ResponseUtil.ok("增加监测点成功");
    }

    /*删*/
    @Override
    public Response deleteSite(String siteId){
        sitesDao.deleteByPrimaryKey(siteId);
        return ResponseUtil.ok("删除监测点成功");
    }

    /*改*/
    @Override
    public Response updateSite(Sites sites){
        sitesDao.updateByPrimaryKey(sites);
        return ResponseUtil.ok("修改监测点信息成功");
    }

    /*查询一条记录*/
    @Override
    public Response selectSite(String siteId){
        return ResponseUtil.ok("查询监测点"+siteId+"信息成功",sitesDao.selectByPrimaryKey(siteId));
    }

    /*查询所有记录*/
    @Override
    public Response selectAllSites(){
        return ResponseUtil.ok("查询所有监测点信息成功",sitesDao.selectAllSites());
    }
}
