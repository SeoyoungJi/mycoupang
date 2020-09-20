package com.mycoupang.model;

public class MemberVO{
	
	private String userno;
	private String userid;
	private String userpw;
	private String name;
	private String gender;
	private String addr;
	private String hp;
	private String email;
	private String interest;
	private String registerday;
	private int active;
	private String role;
	private String code;
	
	public MemberVO() { }
	
	public MemberVO(String userno, String userid, String userpw, String name, String gender, String addr,
			String hp, String email, String interest, String registerday, int active, String role, String code) {
		this.userno = userno;
		this.userid = userid;
		this.userpw = userpw;
		this.name = name;
		this.gender = gender;
		this.addr = addr;
		this.hp = hp;
		this.email = email;
		this.interest = interest;
		this.registerday = registerday;
		this.active = active;
		this.role = role;
		this.code = code;
	}
    
	public String getUserno() {
		return userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}
   
	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getRegisterday() {
		return registerday;
	}

	public void setRegisterday(String registerday) {
		this.registerday = registerday;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
	
 
}
