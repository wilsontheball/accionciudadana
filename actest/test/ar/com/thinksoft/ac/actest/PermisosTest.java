package ar.com.thinksoft.ac.actest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.Page;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import ar.com.thinksoft.ac.intac.IPermitible;
import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.HomePage;
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
		assertTrue(new UsuarioFactory().construirCiudadano().cantidadPermisos()>0);
	}
	
	@Test
	public void testCrearAdministrativoConPermisos(){
		//si anda uno de estos anda cualquiera de los 2
		assertTrue(new UsuarioFactory().construirOperador().cantidadPermisos()>0);
	}
	
	@Test
	public void testCiudadanoTienePermisoParaHome(){
		IUsuario usuario = new UsuarioFactory().construirCiudadano();
		IPermitible page = (IPermitible) tester.startPage(HomePage.class);
		assertTrue(page.puedeAcceder(usuario));
	}
	
	@Test
	public void testUsuarioIndefinidoNoTienePermisoParaHome(){
		IUsuario usuario = new Usuario();
		IPermitible page = this.startPage(HomePage.class);
		assertFalse(page.puedeAcceder(usuario));
	}
	
	/**
	 * Convierte una pagina wicket a una permitible creada por nosotros
	 * @param clazz
	 * @return
	 */
	private IPermitible startPage(Class<? extends Page> clazz){
		return (IPermitible) tester.startPage(clazz);
	}
	
}
