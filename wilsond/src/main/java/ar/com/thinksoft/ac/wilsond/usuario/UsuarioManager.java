package ar.com.thinksoft.ac.wilsond.usuario;

import ar.com.thinksoft.ac.intac.utils.classes.UsuarioMovil;

public class UsuarioManager {
	
private static UsuarioManager instance;
	
	public UsuarioManager(){
	}
	
	public static UsuarioManager getInstance(){
		if(instance == null){
			instance = new UsuarioManager();
		}
		return instance;
	}
	
	/**
	 * Obtiene datos de perfil de un usuario.
	 * 
	 * @since 05-10-2011
	 * @author Paul
	 * @param nombreUsuario
	 * @return Perfil de usuario.
	 */
	public UsuarioMovil getPerfil(String nombreUsuario) {
		UsuarioMovil usr = new ar.com.thinksoft.ac.wilsond.usuario.Usuario();

		// XXX por ahora siempre devuelve FRUTA!!!!!!!!!!!!!!!!
		usr.setApellido("Fulano");
		usr.setContrasenia("123");
		usr.setDni("10000000");
		usr.setMail("pepito@hot.com");
		usr.setNombre("Pepe");
		usr.setNombreUsuario("pepe");
		usr.setTelefono("456123");
		// XXX hasta aqui FRUTA!!!!!!!!!!!!!!!!
		
		return usr;

	}

}
