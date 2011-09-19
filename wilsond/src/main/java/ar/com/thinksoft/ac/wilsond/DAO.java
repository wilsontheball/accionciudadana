package ar.com.thinksoft.ac.wilsond;

import java.util.ArrayList;
import java.util.List;

public class DAO {

	public List<Data> list() {

		ArrayList<Data> lista = new ArrayList<Data>();
		lista.add(new Data("nombre1", 1));
		lista.add(new Data("nombre2", 2));
		lista.add(new Data("nombre3", 3));
		return lista;
	}

}
