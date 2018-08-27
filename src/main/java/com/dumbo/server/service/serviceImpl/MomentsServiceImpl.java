package com.dumbo.server.service.serviceImpl;

import com.dumbo.server.constant.Common;
import com.dumbo.server.dao.MomentsDao;
import com.dumbo.server.entity.Moments;
import com.dumbo.server.entity.Response;
import com.dumbo.server.service.MomentsService;
import com.dumbo.server.util.ResponseUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Dumbo on 2018/7/23
 **/

@Service
public class MomentsServiceImpl implements MomentsService {
    private String path="";//图片存储url
    private final MomentsDao momentsDao;

    @Autowired
    public MomentsServiceImpl(MomentsDao momentsDao) {
        this.momentsDao = momentsDao;
    }

    /*上传照片*/
    @Override
    public Response postMoments(Map<String,Object> map , MultipartFile[] files) {
        for (MultipartFile file : files) {
            //自定义存储文件名
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String fileName = "img_" + sdf.format(date) + "_" + (int) (Math.random() * 10000) + ".jpg";

            if(path.equals("")){
                path=path+"/pictures/"+fileName;
            }else{
                path=path+"&/pictures/"+fileName;
            }

            //文件存放文件夹
            File photosFile = new File(Common.MOMENT_PHOTOS_PATH + File.separator + fileName);
            //判断文件父目录是否存在
            if (!photosFile.getParentFile().exists()) {
                photosFile.getParentFile().mkdirs();
            }

            //转存文件到指定路径
            try {
                file.transferTo(photosFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //获取文本描述信息
        String userId=map.get("user_id").toString();
        String avatarUrl=map.get("avatar_url").toString();
        String nickName=map.get("nick_name").toString();
        String recordTime=map.get("record_time").toString();
        String desc=map.get("description").toString();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time= null;
        try {
            time = sdf.parse(recordTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String addr=map.get("address").toString();
        String lat=map.get("lat").toString();
        String lng=map.get("lng").toString();

        //new Moments()
        Moments moments=new Moments();
        moments.setUserId(Integer.valueOf(userId));
        moments.setAvatarUrl(avatarUrl);
        moments.setNickName(nickName);
        moments.setDescription(desc);
        moments.setRecordTime(time);
        moments.setAddress(addr);
        moments.setPicturesUrl(path);
        moments.setLatBd09ll(Double.valueOf(lat));
        moments.setLngBd09ll(Double.valueOf(lng));

        //插入数据库
        momentsDao.insert(moments);

        path="";
        return ResponseUtil.ok("上传照片成功！");
    }

    @Override
    public Response getMoments(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize,"record_time desc");
        return ResponseUtil.ok("请求动态Moments成功！",momentsDao.selectAll());
    }
}
