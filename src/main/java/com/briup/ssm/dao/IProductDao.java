package com.briup.ssm.dao;

import java.util.List;

import com.briup.ssm.domain.Product;
import org.apache.ibatis.annotations.Select;

public interface IProductDao {
	List<Product> findAllProducts() throws Exception;
	void saveProduct(Product product)throws Exception;

	Product findProductById(String id);
}
