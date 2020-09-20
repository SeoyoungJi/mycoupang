package com.mycoupang.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import java.lang.String;

@Service
public class MemberService implements UserDetailsService{

	@Autowired
	MemberMapper MemberMapper;
	
	@Autowired
	SendMail sendMail;
	
	public int idCheck(MemberVO member) {	
		int check = MemberMapper.idCheck(member);	
		System.out.println("service:"+check); 
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
		System.out.println("확인용 certificationCode : "+certificationCode);
		
		HttpSession session = request.getSession();
		
		try {
			int updateCode = MemberMapper.updateCode(member.getUserid());
			
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
	
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		
		MemberVO member = MemberMapper.findMember(userid);
		
		if( member.getActive() == 0 ) { throw new UsernameNotFoundException("이메일 인증 후 로그인이 가능합니다."); }	
		if( member.getUserid() == null ) { throw new UsernameNotFoundException("로그인 실패"); }
		
	/*
	  	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	 
		if(("admin").equals(userid)) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue())); //롤 부여
		}
		else {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		}
		
		return new User(member.getUserid(), member.getUserpw(), authorities); //SpringSecurity에서 제공하는 UserDetails를 구현한 User를 반환
			 
		*/
		
	//	 GrantedAuthority authority = new SimpleGrantedAuthority(member.getRole());
		
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

	
}
