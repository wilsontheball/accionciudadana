package ar.com.thinksoft.ac.webac.mail;

import java.util.Properties;

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
	
	public void enviarMail(String mailDestino, String asuntoMail, String cuerpoMail){
		Configuracion.getInstance().cargarConfiguracion();
		
		// Setup mail server
		Properties props = new Properties();
		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", Configuracion.getInstance().getSmtp());
		// TLS si está disponible
		props.setProperty("mail.smtp.starttls.enable", Configuracion.getInstance().getTLS().toString());
		// Puerto de gmail para envio de correos
		props.setProperty("mail.smtp.port",Configuracion.getInstance().getPuerto());
		// Si requiere o no usuario y password para conectarse.
		props.setProperty("mail.smtp.auth", Configuracion.getInstance().getAuth().toString());
		// Nombre del usuario
		props.setProperty("mail.smtp.user", "Accion Ciudadana");
		
		
		// Get session
		Session session = Session.getDefaultInstance(props);
		// Define message
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(Configuracion.getInstance().getDesdeMail()));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("matias.tarriopages@gmail.com"));
			message.setSubject(asuntoMail);
			message.setText(cuerpoMail);
			
			Transport transport = session.getTransport("smtp");
			transport.connect(Configuracion.getInstance().getUser(),Configuracion.getInstance().getPassword());
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String armarTextoCambioEstados(String estado){
		return "";
	}

}
