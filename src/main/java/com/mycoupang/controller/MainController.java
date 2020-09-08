package com.mycoupang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping(value = {"/main", "/"} )
	public String main() {
		
		return "main";
	}
	
	@RequestMapping(value = {"/product"} )
	public String product() {
		
		return "product/product";
	}
	
	@RequestMapping(value = {"/upload"} )
	public String upload() {
		
		return "admin/upload";
	}
	
} 
