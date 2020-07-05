package com.briup.ssm.controller;

import com.briup.ssm.domain.Orders;
import com.briup.ssm.service.OrdersService;
import com.briup.ssm.service.impl.OrdersServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService OrdersService;

//    @RequestMapping("/findAll")
//    public ModelAndView findAllOrders(){
//        List<Orders> allOrder = OrdersService.findAllOrder();
//        ModelAndView mv=new ModelAndView();
//        mv.addObject("ordersList",allOrder);
//        mv.setViewName("orders-list");
//        return mv;
//    }
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "pageNum",required = true,defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "4") Integer pageSize){
        List<Orders> list = OrdersService.findAllOrder(pageNum,pageSize);
        PageInfo<Orders> info=new PageInfo<>(list);
        ModelAndView mv=new ModelAndView();
        mv.addObject("pageinfo",info);
        mv.setViewName("orders-pages-list");
        return mv;
    }
    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv=new ModelAndView();
        Orders orders = OrdersService.findbyId(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
