package ar.com.thinksoft.ac.webac.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.com.thinksoft.ac.intac.IReclamo;
import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.exceptions.ConfiguracionException;
import ar.com.thinksoft.ac.webac.exceptions.MailException;
import ar.com.thinksoft.ac.webac.logging.LogFwk;
import ar.com.thinksoft.ac.webac.usuario.Usuario;
import ar.com.thinksoft.ac.webac.web.configuracion.Configuracion;

public class MailManager {
	
	private static MailManager instance;
	private Session session;
	
	public static MailManager getInstance() throws MailException{
		if(instance == null){
			instance = new MailManager();
		}
		return instance;
	}
	
	public MailManager() throws MailException{
		try {
		Configuracion.getInstance().cargarConfiguracion();
		
		// Setup mail server
		Properties props = new Properties();
		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", Configuracion.getInstance().getSmtp());
		// TLS si esta disponible
		props.setProperty("mail.smtp.starttls.enable", Configuracion.getInstance().getTLS().toString());
		// Puerto de gmail para envio de correos
		props.setProperty("mail.smtp.port",Configuracion.getInstance().getPuerto());
		// Si requiere o no usuario y password para conectarse.
		props.setProperty("mail.smtp.auth", Configuracion.getInstance().getAuth().toString());
		// Nombre del usuario
		props.setProperty("mail.smtp.user", "Accion Ciudadana");
		
		// Get session
		session = Session.getDefaultInstance(props);
		
		} catch (ConfiguracionException e1) {
			LogFwk.getInstance(MailManager.class).error("Error en la configuracion del mail. Detalle: " + e1.getMessage());
			throw new MailException("Error en la configuracion del mail. Detalle: " + e1.getMessage());
		}
	}
	
	public void enviarMail(String mailDestino, String asuntoMail, String cuerpoMail) throws MailException{

		// Define message
			MimeMessage message = new MimeMessage(session);
			try {
				message.setFrom(new InternetAddress(Configuracion.getInstance().getDesdeMail()));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailDestino));
				message.setSubject(asuntoMail);
				message.setText(cuerpoMail);
				
				Transport transport = session.getTransport("smtp");
				transport.connect(Configuracion.getInstance().getUser(),Configuracion.getInstance().getPassword());
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();  
				
			} catch (MessagingException e) {
				LogFwk.getInstance(MailManager.class).error("Error al enviar mail. Detalle: " + e.getMessage());
				throw new MailException("Error durante el envio de mail. Detalle: " + e.getMessage());
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
	
	public String armarTextoCambioPrioridad(String prioridad, IReclamo reclamo){
		String texto = 	"Su reclamo con detalle: \n\n"+
						"Dirección: " + reclamo.getCalleIncidente() + " " + reclamo.getAlturaIncidente() + "\n" +
						"Tipo de incidente: " + reclamo.getTipoIncidente() + "\n" +
						"Barrio: " + reclamo.getBarrioIncidente() + "\n" + 
						"Comuna: " + reclamo.getComunaIncidente() + "\n" +
						"Fecha del reclamo: " + reclamo.getFechaReclamo() + "\n";
		
		if(reclamo.getObservaciones()!=null && reclamo.getObservaciones() != "")
			texto = texto + "Observaciones: " + reclamo.getObservaciones() + "\n\n";
		
		texto = texto + "se encuentra actualmente con prioridad: " + prioridad + ".\nLo mantendremos informado acerca del mismo.\nMuchas gracias.\n\nAcción Ciudadana";
	
		return texto;
	}
	
	public String armarTextoBienvenida(IUsuario user) {
		return user.getNombreUsuario()+ ":\n\n" + "Le damos la bienvenida a Acción Ciudadana.\n"+
		"Ya puede iniciar reclamos y saber el estado de los mismos a través del tiempo.\n"+
		"Puede acceder con su usuario desde la aplicación Android o desde nuestra web: www.accion-ciudadana.com.ar.\n"+
		"No dude en contactarse con nosotros para cualquier consulta.\n\nAcción Ciudadana";
	}

	public String armarTextoEliminacion(Usuario usuario) {
		return usuario.getNombreUsuario()+ ":\n\n" + "Su cuenta ha sido eliminada de Acción Ciudadana.\n"+
		"No dude en contactarse con nosotros para cualquier consulta.\n\nAcción Ciudadana";
	}

}
