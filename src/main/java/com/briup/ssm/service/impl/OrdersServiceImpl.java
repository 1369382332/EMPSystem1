package com.briup.ssm.service.impl;

import com.briup.ssm.dao.OrdersDao;
import com.briup.ssm.domain.Orders;
import com.briup.ssm.service.OrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao order;
    @Override
    public List<Orders> findAllOrder(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Orders> allOrders = order.findAllOrders();
        return allOrders;
    }

    @Override
    public Orders findbyId(String id) throws Exception {
        return order.findOrderById(id);
    }
}
