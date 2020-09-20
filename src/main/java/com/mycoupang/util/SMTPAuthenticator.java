package com.mycoupang.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {

	//gmail서버 사용을 위해 계정 정보 입력 (추후 gitignore로 변경)
	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("mycoupang10", "znvkdroqkf!");
	}
}
