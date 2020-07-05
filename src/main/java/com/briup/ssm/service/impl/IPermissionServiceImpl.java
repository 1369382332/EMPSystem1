package com.briup.ssm.service.impl;

import com.briup.ssm.dao.IPermissionDao;
import com.briup.ssm.domain.Permission;
import com.briup.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IPermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public List<Permission> findOtherPermissions(String id) throws Exception {
        return permissionDao.findOtherPermissions(id);
    }

    @Override
    public void savePermission(Permission permission) {
        permissionDao.savePermission(permission);
    }
}
