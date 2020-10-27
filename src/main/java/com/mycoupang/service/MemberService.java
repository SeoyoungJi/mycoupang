package com.mycoupang.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycoupang.mapper.MemberMapper;
import com.mycoupang.model.MemberVO;
import com.mycoupang.security.Role;
import com.mycoupang.util.SendMail;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.lang.String;
import java.security.Principal;

@Service
public class MemberService implements UserDetailsService{

	@Autowired
	MemberMapper MemberMapper;
	
	@Autowired
	SendMail sendMail;
	
	public int idCheck(MemberVO member) {	
		
		int check = MemberMapper.idCheck(member);	
		
		return check;
	}
	
	@Transactional
	public void register(MemberVO member, HttpServletRequest request) throws Exception {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setUserpw(passwordEncoder.encode(member.getUserpw()));
			
		MemberMapper.register(member);
	
		//이메일 인증을 위한 랜덤키 생성
		Random random = new Random(); 
		String certificationCode = "mycoupang";
		
		int num = 0;
		
		for(int i=0; i<7; i++) {
			num = random.nextInt(9-0+1)+1; //0~9사이의 랜덤한 수 1개씩 생성
			certificationCode += num;
		}
		
		member.setCode(certificationCode);
	//	member.setCode(passwordEncoder.encode(member.getCode()));
		System.out.println("확인용 certificationCode : "+member.getCode());
		
		HttpSession session = request.getSession();
		
		try {
			int updateCode = MemberMapper.updateCode(member);
			
			if(updateCode == 1) {
				sendMail.mail(member, certificationCode);
				session.setAttribute("certificationCode", certificationCode); //인증코드를 세션에 저장
			}				
		}	
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	
	public int updateActive(MemberVO member) {
		
		int count = MemberMapper.updateActive(member);
		
		return count;
	}
	
	
	public MemberVO findMember(String userid) {
		
		MemberVO member = MemberMapper.findMember(userid);
		
		return member;
	}
	
	
	
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		
		MemberVO member = MemberMapper.findMember(userid);
		
		if( member.getActive() == 0 ) { throw new UsernameNotFoundException("이메일 인증 후 로그인이 가능합니다."); }	
		if( member.getUserid() == null ) { throw new UsernameNotFoundException("로그인 실패"); }		
		
		 GrantedAuthority authority = null;
		 
		 if(("admin").equals(userid)) {
			  authority = new SimpleGrantedAuthority("ROLE_ADMIN");
		 }
		 else {
			 authority = new SimpleGrantedAuthority("ROLE_MEMBER");
		 }
		 
		 System.out.println("role: "+authority);
		 UserDetails userDetails = (UserDetails)new User(userid, member.getUserpw(), Arrays.asList(authority));
		 
		 return userDetails; //SpringSecurity에서 제공하는 UserDetails를 구현한 User를 반환
	}
	
	//비밀번호 재설정 메일 발송
	public int findpw(MemberVO member) throws IOException {
		
		int count = MemberMapper.findpw(member);

		if(count == 1) {	
			
			try {
				sendMail.findpwMail(member);				
			} catch (MessagingException e ) {		
				e.printStackTrace();
			} 
		}
	
		return count;
	}
	
	//비밀번호 재설정
	public int updatePw(MemberVO member) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setUserpw(passwordEncoder.encode(member.getUserpw()));
		
		int count = MemberMapper.updatePw(member);
		System.out.println("비밀번호변경 "+count);
		System.out.println("비밀번호변경 "+member.getEmail());
		return count;
	}
	
	
	
	//회원정보 조회,변경을 위한 비밀번호 확인
	public int checkInfo(String userpw) {	
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		
		UserDetails userDetails = (UserDetails)principal;
	
		String encodedPassword = MemberMapper.findMember(userDetails.getUsername()).getUserpw();
		
		System.out.println("userpw "+userpw);
		System.out.println("encodedPassword "+encodedPassword);
		int check = 0;
		
		
		if(passwordEncoder.matches(userpw, encodedPassword )){
			check = 1;
		}
		System.out.println("check "+check);	
		return check;
	}
	

	
}
