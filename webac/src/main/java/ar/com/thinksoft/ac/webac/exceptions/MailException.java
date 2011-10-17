package ar.com.thinksoft.ac.webac.exceptions;

@SuppressWarnings("serial")
public class MailException extends Exception{
	
	public MailException(String mensaje){
		super(mensaje);
	}
}
