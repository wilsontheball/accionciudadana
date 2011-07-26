package ar.com.thinksoft.ac.webac.usuario;

import java.util.ArrayList;
import java.util.List;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.intac.IUsuario;

public class Usuario implements IUsuario {

	private List<IPermiso> permisos;

	public Usuario() {
		this.permisos = new ArrayList<IPermiso>();
	}

	
	public boolean tenesPermisosPara(IPermiso permiso) {

		for (IPermiso permisoPropio : this.getPermisos()) {
			if (permisoPropio.equals(permiso))
				return true;
		}

		return false;
	}

	
	
	public void addPermiso(IPermiso permiso) {
		this.permisos.add(permiso);
	}	
	
	
	
	// ************************* GETTERS & SETTERS ***********************

	private List<IPermiso> getPermisos() {
		return this.permisos;
	}


	@Override
	public int cantidadPermisos() {
		return this.getPermisos().size();
	}



}
