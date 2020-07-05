package com.briup.ssm.dao;

import com.briup.ssm.domain.Traveller;

public interface TravellerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Traveller record);

    int insertSelective(Traveller record);

    Traveller selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Traveller record);

    int updateByPrimaryKey(Traveller record);
}