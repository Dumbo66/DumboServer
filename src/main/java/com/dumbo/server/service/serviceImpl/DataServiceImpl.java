package com.dumbo.server.service.serviceImpl;

import com.dumbo.server.dao.DataDao;
import com.dumbo.server.dao.MobileDataDao;
import com.dumbo.server.entity.MobileData;
import com.dumbo.server.entity.Response;
import com.dumbo.server.service.DataService;
import com.dumbo.server.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * --监测数据业务实现类--
 *
 * Created by Dumbo on 2018/4/21
 **/

@Service
public class DataServiceImpl implements DataService {
    private final DataDao dataDao;
    private final MobileDataDao mobileDataDao;

    @Autowired
    public DataServiceImpl(DataDao dataDao,MobileDataDao mobileDataDao) {
        this.dataDao = dataDao;
        this.mobileDataDao=mobileDataDao;
    }

    /*****************************定点监测数据*******************************/
    /*查询某站点最新监测数据*/
    @Override
    public Response selectLatestData(int site){
        return ResponseUtil.ok("请求数据成功！",dataDao.selectLatestData(site));
    }

    /*查询所有站点最新监测数据*/
    @Override
    public Response selectAllLatestData(int count){
        return ResponseUtil.ok("请求数据成功！",dataDao.selectAllLatestData(count));
    }

    /*查询某站点最近一小时平均监测数据*/
    @Override
    public Response selectPreOneHourDataAvg(int site){
        return  ResponseUtil.ok("请求数据成功！",dataDao.selectPreOneHourDataAvg(site));
    }

    /*查询某站点某天每小时平均监测数据*/
    @Override
    public Response selectPerHourDataAvg(int site, String date){
        return  ResponseUtil.ok("请求数据成功！",dataDao.selectPerHourDataAvg(site,date));
    }

    /*****************************移动监测数据*******************************/
    @Override
    public Response insertMobileData(MobileData mobileData) {
        mobileDataDao.insert(mobileData);
        return ResponseUtil.ok("增加移动监测数据成功！");
    }

    @Override
    public Response deleteMobileData(String date) {
        mobileDataDao.deleteByDate(date);
        return ResponseUtil.ok("删除移动监测数据成功！");
    }

    @Override
    public Response updateMobileData(MobileData mobileData) {
        mobileDataDao.update(mobileData);
        return ResponseUtil.ok("修改移动监测数据成功！");
    }

    @Override
    public Response selectMobileData(String date) {
        return ResponseUtil.ok("查询移动监测数据成功！",mobileDataDao.selectByDate(date));
    }


}
