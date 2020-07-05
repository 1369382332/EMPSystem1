package com.briup.ssm.service;

import com.briup.ssm.domain.Traveller;
public interface TravellerService{


    int deleteByPrimaryKey(String id);

    int insert(Traveller record);

    int insertSelective(Traveller record);

    Traveller selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Traveller record);

    int updateByPrimaryKey(Traveller record);

}
