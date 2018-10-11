package com.dumbo.server.service.serviceImpl;

import com.dumbo.server.dao.WaterDataDao;
import com.dumbo.server.entity.Response;
import com.dumbo.server.entity.WaterData;
import com.dumbo.server.service.WaterDataService;
import com.dumbo.server.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * --水质监测数据业务实现类--
 *
 * Created by Dumbo on 2018/4/21
 **/

@Service
public class WaterDataServiceImpl implements WaterDataService {
    private final WaterDataDao waterDataDao;

    @Autowired
    public WaterDataServiceImpl(WaterDataDao waterDataDao) {
        this.waterDataDao = waterDataDao;
    }

    @Override
    public List<WaterData> selectAllData() {
        return waterDataDao.selectAllData();
    }

    /*查询某站点最新定点监测数据*/
    @Override
    public Response selectLatestData(String siteId){
        return ResponseUtil.ok("请求数据成功！", waterDataDao.selectLatestData(siteId));
    }

    /*查询所有站点最新定点监测数据*/
    @Override
    public Response selectAllLatestData(int count){
        return ResponseUtil.ok("请求数据成功！", waterDataDao.selectAllLatestData(count));
    }

    /*查询某站点最近一小时平均定点监测数据*/
    @Override
    public Response selectPreOneHourDataAvg(String siteId){
        return  ResponseUtil.ok("请求数据成功！", waterDataDao.selectPreOneHourDataAvg(siteId));
    }

    /*查询某站点某天每小时平均定点监测数据*/
    @Override
    public Response selectPerHourDataAvg(String siteId, String date){
        return  ResponseUtil.ok("请求数据成功！", waterDataDao.selectPerHourDataAvg(siteId,date));
    }
}
