package ar.com.thinksoft.ac.wilsond.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.wilsond.configuracion.ConfiguracionWilsonD;
import ar.com.thinksoft.ac.wilsond.log.LogManager;

public class MailWilsonD {
	
	private static MailWilsonD instance;
	private Session session;
	

	public static MailWilsonD getInstance(){
		if(instance == null){
			instance = new MailWilsonD();
		}
		return instance;
	}
	
	public MailWilsonD(){
		try {
			ConfiguracionWilsonD.getInstance().cargarConfiguracion();
		} catch (Exception e1) {
			LogManager.getInstance(MailWilsonD.class).error("Error en la configuracion del mail. Detalle: " + e1.getMessage());
		}
		
		// Setup mail server
		Properties props = new Properties();
		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", ConfiguracionWilsonD.getInstance().getSmtp());
		// TLS si está disponible
		props.setProperty("mail.smtp.starttls.enable", ConfiguracionWilsonD.getInstance().getTLS().toString());
		// Puerto de gmail para envio de correos
		props.setProperty("mail.smtp.port",ConfiguracionWilsonD.getInstance().getPuerto());
		// Si requiere o no usuario y password para conectarse.
		props.setProperty("mail.smtp.auth", ConfiguracionWilsonD.getInstance().getAuth().toString());
		// Nombre del usuario
		props.setProperty("mail.smtp.user", "Accion Ciudadana");
		
		// Get session
		session = Session.getDefaultInstance(props);
		
	}
	
	public void enviarMail(String mailDestino, String asuntoMail, String cuerpoMail){
		
		// Define message
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(ConfiguracionWilsonD.getInstance().getDesdeMail()));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailDestino));
			message.setSubject(asuntoMail);
			message.setText(cuerpoMail);
			
			Transport transport = session.getTransport("smtp");
			transport.connect(ConfiguracionWilsonD.getInstance().getUser(),ConfiguracionWilsonD.getInstance().getPassword());
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();  
			
		} catch (MessagingException e) {
			LogManager.getInstance(MailWilsonD.class).error("Error al enviar mail. Detalle: " + e.getMessage());
		}
		
	}
	
	public String armarTextoCambioEstados(String estado, IReclamo reclamo){
		String texto =  "Su reclamo con detalle: \n\n"+
						"Dirección: " + reclamo.getCalleIncidente() + " " + reclamo.getAlturaIncidente() + "\n" +
						"Tipo de incidente: " + reclamo.getTipoIncidente() + "\n" +
						"Barrio: " + reclamo.getBarrioIncidente() + "\n" + 
						"Comuna: " + reclamo.getComunaIncidente() + "\n" +
						"Fecha del reclamo: " + reclamo.getFechaReclamo() + "\n";
		
		if(reclamo.getObservaciones()!=null && reclamo.getObservaciones() != "")
			texto = texto + "Observaciones: " + reclamo.getObservaciones() + "\n\n";
		
		texto = texto + "se encuentra actualmente en estado: " + estado + ".\nLo mantendremos informado acerca del mismo.\nMuchas gracias.\n\nAcción Ciudadana";
		 
		return texto;
	}

	public String armarTextoBienvenida(IUsuario user) {
		return user.getNombreUsuario()+ ":\n\n" + "Le damos la bienvenida a Acción Ciudadana.\n"+
		"Ya puede iniciar reclamos y saber el estado de los mismos a través del tiempo.\n"+
		"Puede acceder con su usuario desde la aplicación Android o desde nuestra web: www.accion-ciudadana.com.ar.\n"+
		"No dude en contactarse con nosotros para cualquier consulta.\n\nAcción Ciudadana";
	}

}
