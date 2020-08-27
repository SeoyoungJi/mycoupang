package com.mycoupang.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mycoupang.member.model.MemberVO;

@Mapper
public interface MemberMapper {
	
	void registerUser (MemberVO user);
}
