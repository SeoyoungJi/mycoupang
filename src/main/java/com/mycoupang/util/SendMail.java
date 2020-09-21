package com.mycoupang.util;

import java.io.File;
import java.util.Properties;

import org.springframework.stereotype.Component;
import com.mycoupang.model.MemberVO;
import com.mycoupang.util.SMTPAuthenticator;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;

@Component
public class SendMail {
	
	public void mail(MemberVO member, String certificationCode) throws Exception {
		
		
		//메일에 정보를 담기 위한 객체 생성
		Properties prop = new Properties();
		
		//SMTP서버 계정 설정
		prop.put("mail.smtp.user", "sysysysysy102@gmail.com");
		
		//SMTP 서버 정보 설정
		prop.put("mail.smtp.host", "smtp.gmail.com");         	
    	prop.put("mail.smtp.port", "465");
    	prop.put("mail.smtp.starttls.enable", "true");
    	prop.put("mail.smtp.auth", "true");
    	prop.put("mail.smtp.debug", "true");
    	prop.put("mail.smtp.socketFactory.port", "465");
    	prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	prop.put("mail.smtp.socketFactory.fallback", "false");
    	
    	prop.put("mail.smtp.ssl.enable", "true");
    	prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
      	
    	
    	Authenticator auth = new SMTPAuthenticator();
    	Session session = Session.getInstance(prop, auth);	
    	
    	//메일 전송시 상황 출력
    	//session.setDebug(true);
    	
    	//메일 내용을 담기 위한 객체
    	MimeMessage msg = new MimeMessage(session);
    	
    	//메일 제목 설정
    	String mailTitle = "[마이쿠팡] 회원가입 인증 메일입니다.";
    	msg.setSubject(mailTitle);
    	
    	
    	//발신 메일 주소
    	String sender = "sysysysysy102@gmail.com";
    	Address addr1 = new InternetAddress(sender);
    	msg.setFrom(addr1);
    	
    	//수신 메일 주소
    	Address addr2 = new InternetAddress(member.getEmail());
    	msg.addRecipient(Message.RecipientType.TO, addr2);
    	
    	//메일 본문 설정
    	msg.setContent(new MimeMultipart());
    	
    	Multipart multi = (Multipart) msg.getContent();
    	  	
    	String html = "<html><head></head><body><div style=\"margin: 0 auto; margin-top: 10px;\">	\r\n" + 
    				  "<div style=\"padding: 0 0 30px 680px;  margin: auto;\">\r\n" + 
    			      "	<img src=\"cid:mailIcon\" height=\"200px\" width=\"200px\"/>					\r\n" + 
    			      "	</div>\r\n" + 
    			      "	<table style = \"border-top: 4px solid black; border-bottom: 4px solid black; max-width: 679px; margin: auto; width: 100%;   font-family: Roboto,'나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">\r\n" + 
    			      "	<tr>\r\n" + 
    			      "	<td colspan =4>\r\n" + 
    			      "	<img src=\"cid:logoImg\" height=\"30px\" width=\"70px\"/>\r\n" + 
    			      "	</td>	\r\n" + 
    			      "	</tr>\r\n" + 
    			      "\r\n" + 
    			      "	<tr>\r\n" + 
    			      "	<td width=\"39\"></td>\r\n" + 
    			      "	<td colspan =\"2\" width = \"600\" style = \"font-size: 28px; color: #666; padding: 25px 0;\">\r\n" + 
    			      	member.getUserid() +"님, <br/>환영합니다!<br/><br/>\r\n" + 
    			      "	<span style = \"color : #444; font-weight: bold;\">회원가입이 곧 완료</span>됩니다.\r\n" + 
    			      "	</td>\r\n" + 
    			      "	</tr>\r\n" + 
    			      "	\r\n" + 
    			      "	<tr>\r\n" + 
    			      "	<td width=\"39\"></td>\r\n" + 
    			      " <td width=\"600\" style = \"font-size: 18px; color: #666; word-break: keep-all; padding: 25px 0; text-decoration: underline; \">\r\n" + 
    			      "	<a href='http://localhost:9090/mycoupang/emailConfirm?userid="+member.getUserid()+"&code="+member.getCode()+"' target='_blenk'>인증하고 마이쿠팡의 모든 서비스 이용하러 가기 </a>\r\n" + 
    			      "	</td>\r\n" + 
    			      "	<td width=\"39\"></td>\r\n" + 
    			      " <tr>\r\n" + 
    			      "	</table>\r\n" + 
    			      " </div></body><html>";
    	
    	multi.addBodyPart(getContents(html));
    	
    	multi.addBodyPart(getImage("C:\\img\\mycoupangLOGO.JPG", "logoImg"));
    	multi.addBodyPart(getImage("C:\\img\\mailIcon.png", "mailIcon"));

    	
    	//메일 전송
    	Transport.send(msg);
    	
    	
    	
    	
    	
    	
    	
	}
	

	
	// 이미지를 로컬로 부터 읽어와서 BodyPart 클래스로 만든다 (바운더리 변환)
	private BodyPart getImage(String filename, String contextId) throws MessagingException {
	// 파일을 읽어와서 BodyPart 클래스로 받는다.
	BodyPart bodyPart = new MimeBodyPart();
	DataSource source = new FileDataSource(filename);
	bodyPart.setDataHandler(new DataHandler(source));

	if (contextId != null) {
	// ContextId 설정
		bodyPart.setHeader("Content-ID", "<" + contextId + ">");
	}
	return bodyPart;
	}


	
	
	private BodyPart getContents(String html) throws MessagingException {
		BodyPart bodyPart = new MimeBodyPart();
		// setText를 이용할 경우 일반 텍스트 내용으로 설정된다.
		// bodyPart.setText(html);
		// html 형식으로 설정
		bodyPart.setContent(html, "text/html; charset=utf-8");
		return bodyPart;
	}


		
}
