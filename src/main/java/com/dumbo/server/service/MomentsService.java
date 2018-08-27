package com.dumbo.server.service;

import com.dumbo.server.entity.Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by Dumbo on 2018/7/23
 **/

public interface MomentsService {
    /*上传动态*/
    public Response postMoments(Map<String,Object> map , MultipartFile[] files);

    /*请求动态*/
    public Response getMoments(int page,int count);
}
