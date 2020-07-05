package com.briup.ssm.service;

import com.briup.ssm.domain.Member;
public interface MemberService{


    int deleteByPrimaryKey(String id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

}
