package com.mycoupang.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mycoupang.model.MemberVO;

public interface InterMemberService extends UserDetailsService{
	
	int idCheck(MemberVO member);
	
	
}
