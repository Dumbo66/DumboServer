package com.dumbo.server.service.serviceImpl;

import com.dumbo.server.dao.MobileDataDao;
import com.dumbo.server.dao.WaterDataDao;
import com.dumbo.server.entity.MobileData;
import com.dumbo.server.entity.Response;
import com.dumbo.server.service.MobileDataService;
import com.dumbo.server.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * --移动监测数据业务实现类--
 *
 * Created by Dumbo on 2018/8/29
 **/

@Service
public class MobileDataServiceImpl implements MobileDataService {
    private final MobileDataDao mobileDataDao;

    @Autowired
    public MobileDataServiceImpl(WaterDataDao waterDataDao, MobileDataDao mobileDataDao) {
        this.mobileDataDao=mobileDataDao;
    }
    /*增加移动监测数据*/
    @Override
    public Response insertMobileData(MobileData mobileData) {
        mobileDataDao.insert(mobileData);
        return ResponseUtil.ok("增加移动监测数据成功！");
    }

    /*删除移动监测数据*/
    @Override
    public Response deleteMobileData(String date) {
        mobileDataDao.deleteByDate(date);
        return ResponseUtil.ok("删除移动监测数据成功！");
    }

    /*修改移动监测数据*/
    @Override
    public Response updateMobileData(MobileData mobileData) {
        mobileDataDao.update(mobileData);
        return ResponseUtil.ok("修改移动监测数据成功！");
    }

    /*查询移动监测数据*/
    @Override
    public Response selectMobileData(String date) {
        return ResponseUtil.ok("查询移动监测数据成功！",mobileDataDao.selectByDate(date));
    }
}
