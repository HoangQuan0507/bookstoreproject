package helper;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.swing.JOptionPane;

public class MailHelper {
	public static boolean send(String from , String to, String subject , String content) {
		try {
			Properties properties = new Properties();
			 properties.put("mail.smtp.auth", "true");
			 properties.put("mail.smtp.starttls.enable", "true");
			 properties.put("mail.smtp.host", "smtp.gmail.com");
			 properties.put("mail.smtp.port", "587");
			 Session session = Session.getDefaultInstance(properties, new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					
					return new PasswordAuthentication("vanthjen.vnl@gmail.com", "conmeoma18");
				}
				 
			});
			 
			 // khai bao Mail
			 Message message = new MimeMessage(session);
			 message.setFrom(new InternetAddress(from));
			 message.setRecipient(RecipientType.TO, new InternetAddress(to));
			 message.setSubject(subject);
			 message.setContent(content, "text/html");
			 message.setSentDate(new Date());
			 
			 // gui mail
			 Transport.send(message);
			 return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
