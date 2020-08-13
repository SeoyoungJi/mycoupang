package com.mycoupang.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mainController {
	
	@RequestMapping(value = {"/main", "/"} )
	public String main() {
		
		return "main";
	}
	
	@RequestMapping(value = "/header" )
	public String header() {
		
		return "header";
	}
}