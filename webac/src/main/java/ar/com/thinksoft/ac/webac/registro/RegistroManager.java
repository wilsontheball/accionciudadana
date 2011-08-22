package ar.com.thinksoft.ac.webac.registro;

import java.util.ArrayList;
import java.util.List;

import ar.com.thinksoft.ac.intac.IUsuario;
import ar.com.thinksoft.ac.webac.repository.Repository;

public class RegistroManager {

	public void registrar(IUsuario usuario) {

		Repository.getInstance().store(usuario);

	}

}
