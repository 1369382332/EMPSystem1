package com.briup.ssm.service;

import com.briup.ssm.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAllRole()throws Exception;

    void saveRole(Role role)throws Exception;

    List<Role> findOtherRole(String id);

    Role findRoleById(String id)throws Exception;

    void addPermissionToRole(String roleId, String[] ids)throws Exception;
}
