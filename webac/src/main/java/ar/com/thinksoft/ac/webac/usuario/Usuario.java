package ar.com.thinksoft.ac.webac.usuario;

import java.util.List;

import ar.com.thinksoft.ac.intac.IPermiso;
import ar.com.thinksoft.ac.intac.IUsuario;

public class Usuario implements IUsuario {

	
	@Override
	public boolean tenesPermisosPara(IPermiso permiso) {
	
		for (IPermiso permisoPropio : this.getPermisos()) {
			if(permisoPropio.equals(permiso)) return true;
		}
		
		return false;
	}

	private List<IPermiso> getPermisos() {
		// TODO Auto-generated method stub
		return null;
	}

}
