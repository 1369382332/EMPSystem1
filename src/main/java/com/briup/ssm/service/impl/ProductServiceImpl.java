package com.briup.ssm.service.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.ssm.dao.IProductDao;
import com.briup.ssm.domain.Product;
import com.briup.ssm.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDao productDao;
	
	@Override
	public List<Product> findAll() throws Exception {
		System.out.println("in service findAll ...");
//		PageHelper.startPage();
		List<Product> list = productDao.findAllProducts();
		//System.out.println("list.size: " + list.size());
		
		return list;
	}

	@Override
	public void save(Product product) throws Exception {
		productDao.saveProduct(product);
	}

}
