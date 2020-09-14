package com.mycoupang.security;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
	
	ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");
	
	Role(String string){ }

    private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
    
}
