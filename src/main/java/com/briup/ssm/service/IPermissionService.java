package com.briup.ssm.service;

import com.briup.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll()throws Exception;

    List<Permission> findOtherPermissions(String id)throws Exception;

    void savePermission(Permission permission);
}
