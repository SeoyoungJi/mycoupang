package com.mycoupang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycoupang.model.ProductVO;
import com.mycoupang.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	MainService MainService;
	
	@RequestMapping(value = {"/main", "/"} )
	public String main() {
		
		return "main";
	}
	
	@GetMapping(value = {"/product"} )
	public ModelAndView product(ModelAndView mav, int product_no) {
		
		ProductVO product = MainService.selectProduct(product_no);
		
		mav.addObject("product", product);	
		mav.setViewName("product/product");
		
		return mav;
	}
	
	@RequestMapping(value = {"/upload"} )
	public String upload() {
		
		return "admin/upload";
	}
	
} 
