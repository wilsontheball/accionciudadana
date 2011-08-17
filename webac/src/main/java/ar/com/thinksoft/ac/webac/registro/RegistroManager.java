package ar.com.thinksoft.ac.webac.registro;

import java.util.ArrayList;
import java.util.List;

import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.registro.validaciones.ExisteUsuario;
import ar.com.thinksoft.ac.webac.registro.validaciones.ValidacionRegistro;
import ar.com.thinksoft.ac.webac.repository.Repository;

public class RegistroManager {

	public List<ValidacionRegistro> validaciones() {
		List<ValidacionRegistro> validaciones = new ArrayList<ValidacionRegistro>();
		validaciones.add(new ExisteUsuario());
		return validaciones;
	}

	public void registrar(IUsuario usuario) {

		
		for (ValidacionRegistro validacion : this.validaciones()) {
			validacion.validate(usuario);
		}		
		
		Repository.getInstance().store(usuario);

	}

}
