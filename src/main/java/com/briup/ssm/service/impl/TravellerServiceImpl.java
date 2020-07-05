package com.briup.ssm.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.briup.ssm.domain.Traveller;
import com.briup.ssm.dao.TravellerMapper;
import com.briup.ssm.service.TravellerService;
@Service
public class TravellerServiceImpl implements TravellerService{

    @Resource
    private TravellerMapper travellerMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return travellerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Traveller record) {
        return travellerMapper.insert(record);
    }

    @Override
    public int insertSelective(Traveller record) {
        return travellerMapper.insertSelective(record);
    }

    @Override
    public Traveller selectByPrimaryKey(String id) {
        return travellerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Traveller record) {
        return travellerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Traveller record) {
        return travellerMapper.updateByPrimaryKey(record);
    }

}
