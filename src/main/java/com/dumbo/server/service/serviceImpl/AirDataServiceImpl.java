package com.dumbo.server.service.serviceImpl;

import com.dumbo.server.dao.AirDataDao;
import com.dumbo.server.entity.Response;
import com.dumbo.server.service.AirDataService;
import com.dumbo.server.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * --水质监测数据业务实现类--
 *
 * Created by Dumbo on 2018/8/29
 **/

@Service
public class AirDataServiceImpl implements AirDataService {
    private final AirDataDao airDataDao;

    @Autowired
    public AirDataServiceImpl(AirDataDao airDataDao) {
        this.airDataDao = airDataDao;
    }

    /*查询某站点最新定点监测数据*/
    @Override
    public Response selectLatestData(String siteId){
        return ResponseUtil.ok("请求数据成功！", airDataDao.selectLatestData(siteId));
    }

    /*查询所有站点最新定点监测数据*/
    @Override
    public Response selectAllLatestData(int count){
        return ResponseUtil.ok("请求数据成功！", airDataDao.selectAllLatestData(count));
    }

    /*查询某站点最近一小时平均定点监测数据*/
    @Override
    public Response selectPreOneHourDataAvg(String siteId){
        return  ResponseUtil.ok("请求数据成功！", airDataDao.selectPreOneHourDataAvg(siteId));
    }

    /*查询某站点某天每小时平均定点监测数据*/
    @Override
    public Response selectPerHourDataAvg(String siteId, String date){
        return  ResponseUtil.ok("请求数据成功！", airDataDao.selectPerHourDataAvg(siteId,date));
    }
}
