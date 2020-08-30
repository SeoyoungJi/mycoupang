package com.mycoupang.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycoupang.member.model.MemberVO;
import com.mycoupang.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService MemberService;
	
	@RequestMapping(value = "/login" )
	public String login() {
		
		return "/login/login";
	}
	
	@RequestMapping(value = "/register" )
	public String register() {
		
		return "/register/register";
	}
	
	@RequestMapping(value = "/idCheck" , method=RequestMethod.POST)
	@ResponseBody
	public int idCheck(MemberVO user) {
		
		int check = MemberService.idCheck(user);
		return check;
	}
	
}
