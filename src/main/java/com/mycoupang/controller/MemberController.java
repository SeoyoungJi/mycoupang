package com.mycoupang.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycoupang.model.MemberVO;
import com.mycoupang.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService MemberService;
	
	@GetMapping("/login")
	public String loginPage() {
		
		return "/login/login";
	}
	
	@GetMapping("/register")
	public String registerPage() {
		
		return "/register/register";
	}
	
	//아이디 중복체크
	@RequestMapping(value = "/idCheck")
	@ResponseBody
	public int idCheck(MemberVO member) {

		int check = MemberService.idCheck(member);
		System.out.println("con: "+check);
		return check;
	}
	
	//회원가입 처리
	@PostMapping("/register")
	public String register(MemberVO member, RedirectAttributes attr, HttpServletRequest request) throws Exception {
		MemberService.register(member, request);
		attr.addFlashAttribute("msg", "가입시 사용한 이메일로 인증 후 로그인이 가능합니다.");
		return "redirect:/login";
	}
	
	//로그인 처리
	@PostMapping("/login")
	public String login() {
		
		return "/main";
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout() {
		
		return "/main";
	}
	
	//메일 인증
	@GetMapping("/emailConfirm")
	public String emailConfirm(MemberVO member) {
			
			MemberService.updateActive(member);
		
			return "/login/login";
		
	}
}
