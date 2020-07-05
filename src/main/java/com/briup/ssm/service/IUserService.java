package com.briup.ssm.service;

import com.briup.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll()throws Exception;
    void save(UserInfo user)throws Exception;

    UserInfo findById(String userId)throws Exception;

    void addRoleToUser(String userId, String[] ids)throws Exception;
}
