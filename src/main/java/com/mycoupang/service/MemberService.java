package com.mycoupang.service;

import java.util.ArrayList;
import java.util.List;

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

@Service
public class MemberService implements UserDetailsService{

	@Autowired
	MemberMapper MemberMapper;
	
	public int idCheck(MemberVO member) {	
		int check = MemberMapper.idCheck(member);	
		System.out.println("service:"+check); 
		return check;
	}
	
	@Transactional
	public void register(MemberVO member) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setUserpw(passwordEncoder.encode(member.getUserpw()));
		
		MemberMapper.register(member);
				
	}
	
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		
		MemberVO member = MemberMapper.findMember(userid);
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		System.out.println("active: "+member.getActive());
		System.out.println("userid: "+member.getUserid());
		
		if( member.getActive() == 0 ) { throw new UsernameNotFoundException("이메일 인증 후 로그인이 가능합니다."); }	
		if( member.getUserid() == null ) { throw new UsernameNotFoundException("로그인 실패"); }
			
		if(("admin").equals(userid)) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue())); //롤 부여
		}
		else {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		}
		
		return new User(member.getUserid(), member.getUserpw(), authorities); //SpringSecurity에서 제공하는 UserDetails를 구현한 User를 반환
		
	}
}
