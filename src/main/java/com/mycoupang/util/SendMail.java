package com.mycoupang.util;


import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import com.mycoupang.model.MemberVO;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;

@Component
public class SendMail {
	
	@Autowired
	JavaMailSender javaMailSender;

	
	public void mail(MemberVO member, String certificationCode) throws Exception {			
    	
		//메일 내용을 담기 위한 객체
		MimeMessage msg = javaMailSender.createMimeMessage();
    	
    	//메일 제목 설정
    	String mailTitle = "[마이쿠팡] 회원가입 인증 메일입니다.";
    	msg.setSubject(mailTitle);    	    	

    	//수신 메일 주소
    	Address addr2 = new InternetAddress(member.getEmail());
    	msg.addRecipient(Message.RecipientType.TO, addr2);
    	
    	//메일 본문 설정
    	msg.setContent(new MimeMultipart());
    	
    	Multipart multi = (Multipart) msg.getContent();
    	  	
    	String html = " <html><head></head><body><div style='margin: 0 auto; margin-top: 10px;'><body>" +  				 
    				  " <div style='padding: 0 0 30px 680px;  margin: auto;'>" + 
    			      "	<img src='cid:mailIcon' height='200px' width='200px'/></div>" + 
    			      "	<table style = \"border-top: 4px solid black; border-bottom: 4px solid black; max-width: 679px; margin: auto; width: 100%; font-family: Roboto,'나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum;\">" +  
    			      "	<tr>" + 
    			      "	<td colspan =4>" + 
    			      "	<img src='cid:logoImg' height='30px' width='70px'/></td>" + 
    			      "	</tr>" +  
    			      "	<tr>" + 
    			      "	<td width='39'></td>" + 
    			      "	<td colspan ='2' width = '600' style = 'font-size: 28px; color: #666; padding: 25px 0;'>" + 
    			      	member.getUserid() +"님, <br/>환영합니다!<br/><br/>" + 
    			      "	<span style = 'color : #444; font-weight: bold;'>회원가입이 곧 완료</span>됩니다." + 
    			      "	</td>" + 
    			      "	</tr>" + 
    			      "	<tr>" + 
    			      "	<td width='39'></td>" + 
    			      " <td width='600' style = 'font-size: 18px; color: #666; word-break: keep-all; padding: 25px 0; text-decoration: underline; '>" + 
    			      "	<a href='http://localhost:9090/mycoupang/emailConfirm?userid="+member.getUserid()+"&code="+member.getCode()+"' target='_blenk'>인증하고 마이쿠팡의 모든 서비스 이용하러 가기 </a>" + 
    				  "	</td>" + 
    			      " <tr>" + 
    			      "	</table></form>" + 
    			      " </body><html>";
    	
    	multi.addBodyPart(getContents(html));
    	
    	multi.addBodyPart(getImage("C:\\img\\mycoupangLOGO.JPG", "logoImg"));
    	multi.addBodyPart(getImage("C:\\img\\mailIcon.png", "mailIcon"));
  	
    	//메일 전송
    	javaMailSender.send(msg);
    	
	}
	
	public void findpwMail(MemberVO member) throws MessagingException, IOException {
		
		
		//메일 내용을 담기 위한 객체
		MimeMessage msg = javaMailSender.createMimeMessage();
    	
    	//메일 제목 설정
    	String mailTitle = "[마이쿠팡] 비밀번호 재설정을 위한 안내메일입니다.";
    	msg.setSubject(mailTitle);    	    	

    	//수신 메일 주소
    	Address addr2 = new InternetAddress(member.getEmail());
    	msg.addRecipient(Message.RecipientType.TO, addr2);
    	System.out.println("mail" +member.getEmail());
    	//메일 본문 설정
    	msg.setContent(new MimeMultipart());
    	
    	Multipart multi = (Multipart) msg.getContent();
    	
    	String html = " <html><head></head><body><div style='margin: 0 auto; margin-top: 10px;'><body>" +  				 
					  " <div style='padding-top: 30px;  margin: auto;'>" + 			     
				      "	<table style = \"border-top: 4px solid black; border-bottom: 4px solid black; max-width: 679px; margin: auto; width: 100%; font-family: Roboto,'나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum;\">" +  
				      "	<tr>" + 
				      "	<td colspan =4>" + 
				      "	<img src='cid:logoImg' height='60px' width='140px'/></td>" + 
				      "	</tr>" +  
				      "	<tr>" + 
				      "	<td width='39'></td>" + 
				      "	<td colspan ='2' width = '600' style = 'font-size: 18px; color: #666; padding: 25px 0;'>" + "비밀번호 재설정을 위한 안내메일입니다.<br/><br/>" + 
				      "	안녕하세요. 마이쿠팡입니다.<br/><span style = 'color : #444; font-weight: bold;'>요청하신 비밀번호 재설정</span>을 위한 본인 확인메일입니다.<br/>" + 
				      " <span style = 'color : #444; font-weight: bold;'>비밀번호 재설정</span>을 하시려면 아래의 문구를 클릭해주세요.<br/><br/>" + 
				      " <span style='color: #4ec2f2; font-weight: bold;'>해당 링크는 발송후 1시간 동안만 유효합니다.</span>" +
				      "	</td>" + 
				      "	</tr>" + 
				      "	<tr>" + 
				      "	<td width='39'></td>" + 
				      " <td width='600' style = 'font-size: 22px; color: #666; word-break: keep-all; padding: 25px 0; text-decoration: underline; '>" + 
				      "	<a href='http://localhost:9090/mycoupang/postRequest?email="+member.getEmail()+"'' target='_blenk'>비밀번호 재설정하기</a>" + 
					  "	</td>" + 
				      " <tr>" + 
				      "	</table>" + 
				      " </body><html>";
    
    	multi.addBodyPart(getContents(html));
    	multi.addBodyPart(getImage("C:\\img\\mycoupangLOGO.JPG", "logoImg"));
    	
    	
    	//메일 전송   	
    	javaMailSender.send(msg);
	}
	

	
	// 이미지를 로컬로 부터 읽어와서 BodyPart클래스로 (바운더리 변환)
	private BodyPart getImage(String filename, String contextId) throws MessagingException {
		
		// 파일을 읽어와서 BodyPart 클래스로 받음
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
		
		// html 형식으로 설정
		bodyPart.setContent(html, "text/html; charset=utf-8");
		return bodyPart;
	}


		
}
