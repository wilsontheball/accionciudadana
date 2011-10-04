package ar.com.thinksoft.ac.webac.registro;

import ar.com.thinksoft.ac.webac.repository.Repository;
import ar.com.thinksoft.ac.webac.usuario.Usuario;

public class RegistroManager {

	public void registrar(Usuario usuario) {

		Repository.getInstance().store(usuario);

	}

}
