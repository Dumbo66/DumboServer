package com.dumbo.server.service.serviceImpl;

import com.dumbo.server.constant.Path;
import com.dumbo.server.entity.Response;
import com.dumbo.server.service.FilesService;
import com.dumbo.server.util.ResponseUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dumbo on 2018/7/23
 **/

@Service
public class FilesServiceImpl implements FilesService {

    /*上传照片*/
    @Override
    public Response uploadPictures(MultipartFile[] files) {
        for (MultipartFile file : files) {
            System.out.println("fffffffffffffffffffffffffffffffffffffffffffffffffffff" + file.getOriginalFilename());

            System.out.println(file.getName());

            //自定义存储文件名
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String fileName = "img_" + sdf.format(date) + "_" + (int) (Math.random() * 10000) + ".jpg";

            //文件存放文件夹
            File photosFile = new File(Path.PHOTOS_PATH + File.separator + fileName);
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
        return ResponseUtil.ok("上传照片成功！");
    }
}
