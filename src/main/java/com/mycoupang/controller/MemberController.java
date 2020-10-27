package com.mycoupang.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.mycoupang.util.HttpPostRequest;
import com.mycoupang.util.PcResource;

@Controller
public class MemberController {
	
	@Autowired
	MemberService MemberService;
	
	@Autowired
	PcResource PcResource;
	
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
	public String register(MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception {
					
		MemberService.register(member, request);
	
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('가입시 사용한 이메일로 인증 메일을 발송했습니다.'); </script>");
		out.flush();
		
		return "/login/login";
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
	
	//이메일 인증
	@GetMapping("/emailConfirm")
	public String emailConfirm(MemberVO member, HttpServletResponse response) throws IOException {
			
		int check = MemberService.updateActive(member);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();					
		
		if(check == 1) {	
			out.println("<script>alert('이메일 인증이 완료됐습니다! 이제 로그인이 가능합니다.'); </script>");
		}
		else {
			out.println("<script>alert('이메일 인증에 실패했습니다. 다시 시도해주세요.'); </script>");
		}
				
		
		out.flush();
		return "/login/login";
	
	}
	
	@RequestMapping("/postRequest")
	public String postRequest(MemberVO member) {
		
		String url = "http://localhost:9090/mycoupang/changepw"; 	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", member.getEmail());	
		System.out.println("post: "+member.getEmail());
		return HttpPostRequest.httpPost(url, map);
		
		
	}
	
	//비밀번호 찾기 페이지
	@GetMapping("/findpw")
	public String findpw() {
	
		return "member/findpw";
	}
	
	//비밀번호 찾기
	@PostMapping("/findpwEnd")
	public String findpwEnd(MemberVO member, HttpServletResponse response) throws IOException {
	
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();	
		
		int count = MemberService.findpw(member);

		if(count == 1) {	
			out.println("<script>alert('가입시 사용한 이메일로 비밀번호 재설정 메일을 발송했습니다.'); </script>");
			out.flush();
			return "/login/login";
		}
		else {
			out.println("<script>alert('해당 회원 정보가 존재하지 않습니다. 다시 시도해주세요.'); </script>");
			out.flush();
			return "member/findpw";
		}
		
		
		
	}
	
	//비밀번호 재설정 페이지
	@PostMapping("/changepw")
	public ModelAndView changepw(ModelAndView mav, MemberVO member) {

		mav.setViewName("member/changepw");
		mav.addObject("email", member.getEmail());
		
		return mav;
	}
	
	
	//비밀번호 재설정 페이지
	@PostMapping("/changepwEnd")
	public String changepwEnd(MemberVO member, HttpServletResponse response, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
	
		int count = MemberService.updatePw(member);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();					
		
		if(count == 1) {	
			out.println("<script>alert('비밀번호 변경이 완료되었습니다.'); </script>");
			out.flush();
			return "/login/login";
		}
		else {						
		//	redirectAttributes.addFlashAttribute("email", member.getEmail());
			String referer = request.getHeader("referer");
			request.getSession().setAttribute("prevPage", referer);

			out.println("<script>alert('비밀번호 변경에 실패했습니다. 다시 시도해주세요.'); </script>");
			out.flush();
			
			return "member/changepw";
		}				
		
		
		
				
	}
	
	
	//회원정보 확인
	@GetMapping("/checkInfo")
	public ModelAndView checkInfo(ModelAndView mav, Principal principal) {
		
		MemberVO member = MemberService.findMember(principal.getName());
		
		if(member == null) {
			mav.setViewName("/login/login");
		}
		else {
			mav.setViewName("member/checkInfo");
			mav.addObject("member", member);
		}		
	
		return mav;
	}
	
	//회원정보 확인
	@PostMapping("/checkInfoEnd")
	public String checkInfo(String userpw, HttpServletResponse response) throws IOException {
		
		int check = MemberService.checkInfo(userpw);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();				
		
		if(check == 1) {
			return "/member/mypage";
		}
		else {
			out.println("<script>alert('비밀번호가 일치하지 않습니다. 다시 입력해주세요.'); </script>");
			out.flush();
			return "rediredct:"; //수정 필요!
		}
		
	}
	
	//pc자원 확인
	@GetMapping("/resource")
	public ModelAndView resource(ModelAndView mav) {
		
		DecimalFormat format = new DecimalFormat();
		format.applyLocalizedPattern("0.00");
		
		double cpu = PcResource.showCPU();
		
		HashMap<String, Integer> diskMap = PcResource.showDisk();
		HashMap<String, Integer> memoryMap = PcResource.showSystemMemory();
		
		
		mav.addObject("cpu", format.format(cpu));
		mav.addObject("diskMap", diskMap);
		mav.addObject("memoryMap", memoryMap);
		mav.setViewName("/member/resource");
		
		return mav;
	}
}
