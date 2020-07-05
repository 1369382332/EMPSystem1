package com.briup.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.briup.ssm.domain.Product;
import com.briup.ssm.service.IProductService;

//包含产品相关操作
@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;
	
	//查询所有产品
	@RequestMapping("/findAll")
	public ModelAndView findAll() throws Exception {
		//System.out.println("in findAll ...");
		
		ModelAndView mv = new ModelAndView();
		List<Product> list = productService.findAll();
		mv.addObject("productList",list);
		mv.setViewName("product-list");
		
		return mv;
	}
	@RequestMapping("/save")
	public String save(Product product) throws Exception{
		productService.save(product);
		return "redirect: findAll";
	}
}
