package ar.com.thinksoft.ac.wilsond.usuario;

import java.util.List;

import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.intac.utils.classes.UsuarioMovil;
import ar.com.thinksoft.ac.wilsond.Repositorio.Repositorio;

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
	 * @throws Exception 
	 */
	public UsuarioMovil getPerfil(String nombreUsuario) throws Exception {
		
		return toUsuarioAndrac(getUsuarioFromDB(nombreUsuario));

	}
	
	public boolean validarUsuario(String nombreUsuario, String password){
		
		List<IUsuario> listaUsuarios = Repositorio.getInstancia().query(IUsuario.class);
		
		for(IUsuario usuario:listaUsuarios){
			if(nombreUsuario.equals(usuario.getNombreUsuario()) && password.equals(usuario.getContrasenia()))
				return true;
		}
		
		return false;
	}

	private UsuarioMovil toUsuarioAndrac(IUsuario userBD) {
		UsuarioMovil usr = new UsuarioAndrac();
		usr.setApellido(userBD.getApellido());
		usr.setContrasenia(userBD.getContrasenia());
		usr.setDni(userBD.getDni());
		usr.setMail(userBD.getMail());
		usr.setNombre(userBD.getNombre());
		usr.setNombreUsuario(userBD.getNombreUsuario());
		usr.setTelefono(userBD.getTelefono());
		return usr;
	}

	private IUsuario getUsuarioFromDB(String nombreUsuario) throws Exception {
		
		List<IUsuario> listaUsuarios = Repositorio.getInstancia().query(IUsuario.class);
		
		for(IUsuario usuario:listaUsuarios){
			if(nombreUsuario.equals(usuario.getNombreUsuario()))
				return usuario;
		}
		
		throw new Exception("El usuario no fue encontrado");
	}

}
