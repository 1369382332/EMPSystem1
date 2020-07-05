package com.briup.ssm.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.briup.ssm.dao.MemberMapper;
import com.briup.ssm.domain.Member;
import com.briup.ssm.service.MemberService;
@Service
public class MemberServiceImpl implements MemberService{

    @Resource
    private MemberMapper memberMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Member record) {
        return memberMapper.insert(record);
    }

    @Override
    public int insertSelective(Member record) {
        return memberMapper.insertSelective(record);
    }

    @Override
    public Member selectByPrimaryKey(String id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Member record) {
        return memberMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Member record) {
        return memberMapper.updateByPrimaryKey(record);
    }

}
