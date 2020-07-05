package com.briup.ssm.controller;

import com.briup.ssm.domain.Permission;
import com.briup.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/permission")
@Controller
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        List<Permission> permissionList = permissionService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("permissionList", permissionList);
        mv.setViewName("permission-list");
        return mv;
    }
    @RequestMapping("/save")
    public String savePermission(Permission permission)throws Exception{
        permissionService.savePermission(permission);
        return "Redirect: findAll";
    }
}
