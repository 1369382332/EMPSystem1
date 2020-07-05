package com.briup.ssm.service.impl;

import com.briup.ssm.dao.IRoleDao;
import com.briup.ssm.domain.Role;
import com.briup.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IRoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAllRole() throws Exception {
        return roleDao.findAllRole();
    }

    @Override
    public void saveRole(Role role) throws Exception {
        roleDao.saveRole(role);
    }

    @Override
    public List<Role> findOtherRole(String id) {
        return roleDao.findOtherRole(id);
    }

    @Override
    public Role findRoleById(String id) throws Exception {
        return roleDao.findRoleById(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) throws Exception {
        for (String permissionId : ids) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
