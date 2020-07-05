package com.briup.ssm.service;

import java.util.List;

import com.briup.ssm.domain.Product;

public interface IProductService {
	List<Product> findAll() throws Exception;
	void save(Product product) throws Exception;

}
