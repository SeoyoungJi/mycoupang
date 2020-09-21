package com.mycoupang.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mycoupang.model.MemberVO;

@Mapper
public interface MemberMapper {
	
	void register (MemberVO member);
	
	int idCheck (MemberVO member);
	
	MemberVO findMember (String userid);
	
	int updateCode (MemberVO member);

	
	int updateActive (MemberVO member);
}
