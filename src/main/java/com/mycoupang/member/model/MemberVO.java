package com.mycoupang.member.model;

public class MemberVO {
	
	private Integer userno;
	private String userid;
	private String userpw;
	private String username;
	private String gender;
	private String addr1;
	private String addr2;
	private String hp;
	private String email;
	private String registerday;
	private Integer active;
	
	public MemberVO() { }
	
	public MemberVO(Integer userno, String userid, String userpw, String username, String gender, String addr1,
			String addr2, String hp, String email, String registerday, Integer active) {
		super();
		this.userno = userno;
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.gender = gender;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.hp = hp;
		this.email = email;
		this.registerday = registerday;
		this.active = active;
	}
	
	public Integer getUserno() {
		return userno;
	}
	public void setUserno(Integer userno) {
		this.userno = userno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegisterday() {
		return registerday;
	}
	public void setRegisterday(String registerday) {
		this.registerday = registerday;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	
	
 
}
