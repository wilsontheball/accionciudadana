package ar.com.thinksoft.ac.actest;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.WicketApplication;
import ar.com.thinksoft.ac.webac.usuario.Usuario;
import ar.com.thinksoft.ac.webac.usuario.UsuarioFactory;

public class PermisosTest {
	
	private WicketTester tester;
	
	@Before
	public void setUp(){
		 this.tester = new WicketTester(new WicketApplication());
	}
	
	@Test
	public void testCrearCiudadanoConPermisos(){
	}
	
	@Test
	public void testCrearAdministrativoConPermisos(){
		//si anda uno de estos anda cualquiera de los 2
	}
	
	@Test
	public void testCiudadanoTienePermisoParaHome(){
		IUsuario usuario = new UsuarioFactory().construirCiudadano();
	}
	
	@Test
	public void testUsuarioIndefinidoNoTienePermisoParaHome(){
		IUsuario usuario = new Usuario();
	}
	
	/**
	 * Convierte una pagina wicket a una permitible creada por nosotros
	 * @param clazz
	 * @return
	 */
	
}
