package com.briup.ssm.dao;

import com.briup.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {
    @Select("select * from users where username=#{username} ")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phonenum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,
                    many =@Many(select = "com.briup.ssm.dao.IRoleDao.findAllRolesByUserId"))
    })
    UserInfo findUserByUsername(String username)throws Exception;
    @Select("select * from users")
    List<UserInfo> findAllUsers()throws Exception;
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void saveUser(UserInfo user)throws Exception;
    @Select("select * from users where id=#{userId} ")
    @Results({@Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phonenum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,
                    many =@Many(select = "com.briup.ssm.dao.IRoleDao.findAllRolesByUserId"))
    })
    UserInfo findById(String userId)throws Exception;

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId")String userId, @Param("roleId")String roleId);
}
