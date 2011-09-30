package ar.com.thinksoft.ac.webac.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import ar.com.thinksoft.ac.webac.web.configuracion.Configuracion;

public class PassAuthenticator extends Authenticator{
	
	public PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(Configuracion.getInstance().getUser(), Configuracion.getInstance().getPassword());
	}

}
