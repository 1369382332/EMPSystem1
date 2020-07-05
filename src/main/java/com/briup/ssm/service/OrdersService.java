package com.briup.ssm.service;

import com.briup.ssm.domain.Orders;

import java.util.List;

public interface OrdersService {
    List<Orders> findAllOrder(int pageNum,int pageSize);
    Orders findbyId(String id)throws Exception;
}
