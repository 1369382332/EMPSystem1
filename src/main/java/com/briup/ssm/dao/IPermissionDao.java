package com.briup.ssm.dao;

import com.briup.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in" +
            "(select permissionid from role_permission where roleid=#{id} )")
    List<Permission> findPermissionByRoleId(String id)throws Exception;
    @Select("select * from permission")
    public List<Permission> findAll();

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(String roleId) throws Exception;
    @Insert("insert into permission(permissionName,url) values (#{permissionName} ,#{url} )")
    void savePermission(Permission permission);
}
