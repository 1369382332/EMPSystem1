package com.briup.ssm.dao;

import com.briup.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in (select roleid from users_role where userid=#{userId} )")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",
            many = @Many(select = "com.briup.ssm.dao.IPermissionDao.findPermissionByRoleId")),

    })
   List<Role> findAllRolesByUserId(String userId) throws Exception;
    @Select("select * from role")
    List<Role> findAllRole()throws Exception;
    @Insert("insert into role(rolename,roledesc)" +
            "values (#{roleName} ,#{roleDesc} )")
    void saveRole(Role role)throws Exception;

    @Select("select * from role where id not in( select roleId from users_role where userId=#{id})")
    List<Role> findOtherRole(String id);

    @Select("select * from role where id=#{id}")
    Role findRoleById(String id)throws Exception;
    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId")String roleId, @Param("permissionId")String permissionId)throws Exception;
}
