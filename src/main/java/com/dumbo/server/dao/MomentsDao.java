package com.dumbo.server.dao;

import com.dumbo.server.entity.Moments;

import java.util.List;

public interface MomentsDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(Moments record);

    int insertSelective(Moments record);

    List<Moments> selectByCount(Integer count);

    int updateByPrimaryKeySelective(Moments record);

    int updateByPrimaryKey(Moments record);
}