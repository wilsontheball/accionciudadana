package ar.com.thinksoft.ac.webac.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.com.thinksoft.ac.webac.web.configuracion.Configuracion;

public class MailManager {
	
	private static MailManager instance;
	
	public static MailManager getInstance(){
		if(instance == null){
			instance = new MailManager();
		}
		return instance;
	}
	
	public void enviarMail(String mailDestino, String asunto){
		Authenticator auth = null;
		Configuracion.getInstance().cargarConfiguracion();
		
		// Get system properties
		Properties props = System.getProperties();
		
		// Setup mail server
		props.put("mail.smtp.host", Configuracion.getInstance().getSmtp());
		props.put("mail.smtp.port", Configuracion.getInstance().getPuerto());
		props.put("mail.smtp.starttls.enable", Configuracion.getInstance().getTLS().toString());
		props.put("mail.smtp.auth", Configuracion.getInstance().getAuth().toString());
		props.put("mail.smtp.user", Configuracion.getInstance().getUser());
		props.put("mail.smtp.socketFactory.port", Configuracion.getInstance().getPuerto());
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		//Si necesito credenciales...
		if(Configuracion.getInstance().getAuth())
			auth = new PassAuthenticator();
		
		// Get session
		Session session = Session.getDefaultInstance(props, auth);
		
		// Define message
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(Configuracion.getInstance().getDesdeMail()));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailDestino));
			message.setSubject("Hello JavaMail");
			message.setText("Welcome to JavaMail");
			
			Transport transport = session.getTransport("smtps");
			transport.connect (	Configuracion.getInstance().getSmtp(), Integer.valueOf(Configuracion.getInstance().getPuerto()),
								Configuracion.getInstance().getUser(), Configuracion.getInstance().getPassword());
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
