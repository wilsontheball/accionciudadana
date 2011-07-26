package ar.com.thinksoft.ac.actest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ar.com.thinksoft.ac.webac.usuario.UsuarioFactory;

public class PermisosTest {
	
	@Test
	public void testCrearCiudadanoConPermisos(){
		assertTrue(new UsuarioFactory().construirCiudadano().cantidadPermisos()>0);
	}
	
	@Test
	public void testCrearAdministrativoConPermisos(){
		//si anda uno de estos anda cualquiera de los 2
		assertTrue(new UsuarioFactory().construirOperador().cantidadPermisos()>0);
	}
	
}
