package com.dumbo.server.service.serviceImpl;

import com.dumbo.server.constant.Path;
import com.dumbo.server.entity.Response;
import com.dumbo.server.service.FilesService;
import com.dumbo.server.util.ResponseUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by Dumbo on 2018/7/23
 **/

@Service
public class FilesServiceImpl implements FilesService {

    /*上传照片*/
    @Override
    public Response uploadPhotos(MultipartFile file) {
        if(file.isEmpty()){
            return null;
        }
        String contentType=file.getContentType();
        String fileName=file.getName();
        System.out.println("ffffffffffffffffffffffffffffffffffffffffffffffffffff"+fileName);

        //文件存放路径
        File photosFile=new File(Path.PHOTOS_PATH+File.separator+fileName);
        if(!photosFile.getParentFile().exists()){ //判断文件父目录是否存在
            photosFile.mkdirs();
        }

        //转存文件到指定路径
        try {
            file.transferTo(photosFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("上传照片成功！");
        return ResponseUtil.ok("上传照片成功！");
    }
}
