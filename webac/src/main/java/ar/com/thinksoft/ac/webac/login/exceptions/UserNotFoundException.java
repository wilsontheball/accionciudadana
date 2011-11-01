package ar.com.thinksoft.ac.webac.login.exceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String usuario) {
		super("El usuario no se encuentra registrado en el sistema o ha ingresado mal la clave.");
	}

}
