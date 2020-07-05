package com.briup.ssm.controller;

import com.briup.ssm.domain.Permission;
import com.briup.ssm.domain.Role;
import com.briup.ssm.service.IPermissionService;
import com.briup.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;
    @RequestMapping("/findAll")
    public ModelAndView findAll()throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Role> allRole = roleService.findAllRole();
        mv.addObject("roleList",allRole);
        mv.setViewName("role-list");
        return mv;
    }
    @RequestMapping("/save")
    public String saveRole(Role role)throws Exception{
        roleService.saveRole(role);
        return "redirect: findAll";
    }
    @RequestMapping("/findRoleByIdAndAllPermissions")
    public ModelAndView findRoleByIdAndAllPermissions(String id)throws Exception{
        ModelAndView mv=new ModelAndView();
        //查询指定role对
        Role role = roleService.findRoleById(id);
        mv.addObject("role", role);

        //查询当前角色尚未选择的 所有权限
        List<Permission> list = permissionService.findOtherPermissions(id);
        mv.addObject("permissionList", list);

        mv.setViewName("role-permission-add");
        return mv;
    }
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(String roleId,String[] ids)throws Exception{
        roleService.addPermissionToRole(roleId,ids);
        return "Redirect: findAll";
    }
}
