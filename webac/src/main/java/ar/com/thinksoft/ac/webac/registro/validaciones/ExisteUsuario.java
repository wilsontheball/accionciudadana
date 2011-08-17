package ar.com.thinksoft.ac.webac.registro.validaciones;

import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.predicates.registro.PredicateUsuarioExistente;
import ar.com.thinksoft.ac.webac.repository.Repository;

public class ExisteUsuario implements ValidacionRegistro {

	@Override
	public boolean validate(IUsuario usuario) {
		return Repository
				.getInstance()
				.query(new PredicateUsuarioExistente().exist(usuario
						.getNombreUsuario())).size() >= 1;
	}

}
