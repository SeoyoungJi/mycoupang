package com.mycoupang.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycoupang.mapper.MemberMapper;
import com.mycoupang.member.model.MemberVO;


@Service
public class MemberService {

	@Autowired
	MemberMapper MemberMapper;
	
	public int idCheck(MemberVO user) {	
		int check = MemberMapper.idCheck(user);		
		return check;
	}
}
