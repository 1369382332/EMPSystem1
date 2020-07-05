package com.briup.ssm.controller;

import com.briup.ssm.domain.Role;
import com.briup.ssm.domain.UserInfo;
import com.briup.ssm.service.IRoleService;
import com.briup.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @RequestMapping("/findAll")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll()throws Exception{
        ModelAndView mv=new ModelAndView();
        List<UserInfo> list = userService.findAll();
        mv.addObject("userList",list);
        mv.setViewName("user-list");
        return mv;
    }
    @RequestMapping("/save")
    public String save(UserInfo user)throws Exception{
        userService.save(user);
        return "redirect:findAll";
    }
    @RequestMapping("/findById")
    public ModelAndView findById(String id)throws Exception{
        ModelAndView mv=new ModelAndView();
        UserInfo user=userService.findById(id);
        mv.addObject("user",user);
        mv.setViewName("user-show");
        return mv;
    }
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(String id)throws Exception{
        ModelAndView mv=new ModelAndView();
        UserInfo user = userService.findById(id);
        List<Role> roleList = roleService.findOtherRole(id);
        mv.addObject("user", user);
        mv.addObject("roleList", roleList);
        mv.setViewName("user-role-add");
        return mv;
    }
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(String userId, String[] ids) throws Exception {
        userService.addRoleToUser(userId,ids);
        return "redirect:findAll";
    }
}
