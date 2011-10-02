package ar.com.thinksoft.ac.webac.web.usuario.alta;

import java.util.ArrayList;
import java.util.List;

public enum TipoUsuario {

	Ciudadano,Administrador,Operario;
	
	
	
	public static List<String> getValues(){
		List<String> list = new ArrayList<String>();
		for (TipoUsuario tipoUsuario : TipoUsuario.values()) {
			list.add(tipoUsuario.toString());
		}
		return list;
	}
	
	
}
