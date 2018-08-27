package com.dumbo.server.dao;

import com.dumbo.server.entity.Moments;

import java.util.List;

public interface MomentsDao {
    List<Moments> selectAll();

    int deleteByPrimaryKey(Integer momentId);

    int insert(Moments record);

    int insertSelective(Moments record);

    Moments selectByPrimaryKey(Integer momentId);

    int updateByPrimaryKeySelective(Moments record);

    int updateByPrimaryKey(Moments record);
}