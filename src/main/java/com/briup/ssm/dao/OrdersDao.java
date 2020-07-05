package com.briup.ssm.dao;

import com.briup.ssm.domain.Orders;

import java.util.List;

public interface OrdersDao {
    List<Orders> findAllOrders();
    Orders findOrderById(String id)throws Exception;
}
