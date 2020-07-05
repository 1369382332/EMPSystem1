package com.briup.ssm.service.impl;

import com.briup.ssm.dao.IUserDao;
import com.briup.ssm.domain.Role;
import com.briup.ssm.domain.UserInfo;
import com.briup.ssm.service.IUserService;
import com.briup.ssm.utils.BCryptPasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private BCryptPasswordEncoder PasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=null;
        try {
            userInfo = userDao.findUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        User user=new User("{noop}"+userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthorities(userInfo.getRoles()));
        boolean b=userInfo.getStatus()==0?true:false;
        UserDetails userDetails=new User(userInfo.getUsername(),userInfo.getPassword(),b,true,true,true,getAuthorities(userInfo.getRoles()));
        return userDetails;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (Role role:roles) {
            String roleName = role.getRoleName();
            list.add(new SimpleGrantedAuthority("ROLE_"+roleName));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll() throws Exception {

        return userDao.findAllUsers();
    }

    @Override
    public void save(UserInfo user) throws Exception {
        String password = user.getPassword();
        String newpassword = PasswordEncoder.encode(password);
        user.setPassword(newpassword);
        userDao.saveUser(user);
    }

    @Override
    public UserInfo findById(String userId) throws Exception {
        UserInfo user=userDao.findById(userId);
        return user;
    }

    @Override
        public void addRoleToUser(String userId, String[] ids) throws Exception {
        for (String roleId:ids) {
            userDao.addRoleToUser(userId,roleId);
        }

    }

//    private Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> list=new ArrayList<>();
//        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        list.add(new SimpleGrantedAuthority("ROLE_USER"));
//        return list;
//    }
}
