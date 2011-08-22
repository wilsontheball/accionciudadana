package ar.com.thinksoft.ac.webac.login.exceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String usuario) {
		super("El usuario \""+ usuario +"\" no se encuentra registrado en la aplicacion");
	}

}
