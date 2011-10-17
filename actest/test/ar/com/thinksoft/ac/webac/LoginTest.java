package ar.com.thinksoft.ac.webac;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;

import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.login.Login;
import ar.com.thinksoft.ac.webac.repository.Repository;
import ar.com.thinksoft.ac.webac.usuario.Usuario;

public class LoginTest {
	
	@Before
	public void setUp(){
		Repository.getInstance().truncate();
		IUsuario usuario = new Usuario();
		usuario.setContrasenia("adriel1");
		usuario.setNombreUsuario("adriel");
		Repository.getInstance().store(usuario);
	}
	
	@Ignore
	public void testLoginSuccesful(){
		Login login = new Login("adriel","adriel1");
		IUsuario usuario = login.login();
		assertEquals("adriel",usuario.getNombreUsuario());
		assertEquals("adriel1", usuario.getContrasenia());
	}
	
	@Ignore
	//@Test(expected=UserNotFoundException.class)
	public void testUsuarioNoEncontrado(){
		Login login = new Login("mati","adriel1");
		@SuppressWarnings("unused")
		IUsuario usuario = login.login();
	}

}
