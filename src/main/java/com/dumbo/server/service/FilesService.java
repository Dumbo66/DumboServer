package com.dumbo.server.service;

import com.dumbo.server.entity.Response;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Dumbo on 2018/7/23
 **/

public interface FilesService {
    /*上传照片*/
    public Response uploadPhotos(MultipartFile file);
}
