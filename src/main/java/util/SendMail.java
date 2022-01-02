package util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	// 매개변수 : 발신자, 수신자, 제목, 내용
	public static void sendMail(String from, String to, String subject, String content) {
		try {
			// 메일서버 정보 설정
			Properties prop = System.getProperties();
			prop.put("mail.smtp.host", "smtp.naver.com");
			prop.put("mail.smtp.port", "465");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.ssl.enable", "true");
			prop.put("mail.smtp.ssl.trust", "smtp.naver.com"); // connect안되는 경우 추가
			prop.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 에러나는 경우 TLS버전
			
			// 세션객체 생성
			Session session = Session.getDefaultInstance(prop, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("withsky999", "als9tjdgml99(");
				}
			});
			session.setDebug(true);
			
			// MimeMessage 객체 생성
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(from));
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mimeMessage.setSubject(subject);
			// text로 발송
			//mimeMessage.setText(content);
			// html로 발송
			mimeMessage.setContent(content, "text/html; charset=utf-8");
			
			// 메일 전송
			Transport.send(mimeMessage);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		// 문자3자 +  숫자3
		// abc123
		String tempPwd = "";
		for (int i=0; i<3; i++) {
			tempPwd += (char)((Math.random()*26)+65);
		}
		for (int i=0; i<3; i++) {
			tempPwd += (int)((Math.random()*9));
		}
		
		System.out.println(tempPwd);
		sendMail("withsky999@naver.com", "withsky999@gmail.com", "[더조은]임시비밀번호", "임시비밀번호는 <span style='color:red;'>"+tempPwd+"</span>입니다.");
	}
}
