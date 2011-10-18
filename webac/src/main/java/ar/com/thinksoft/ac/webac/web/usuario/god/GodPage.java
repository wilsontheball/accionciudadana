package ar.com.thinksoft.ac.webac.web.usuario.god;

import ar.com.thinksoft.ac.webac.repository.Repository;
import ar.com.thinksoft.ac.webac.usuario.Usuario;
import ar.com.thinksoft.ac.webac.usuario.UsuarioFactory;
import ar.com.thinksoft.ac.webac.web.HomePage.HomePage;
import ar.com.thinksoft.ac.webac.web.base.BasePage;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class GodPage extends BasePage {

	@SuppressWarnings("serial")
	public GodPage() {

		ObjectSet<Usuario> result = Repository.getInstance().query(
				new Predicate<Usuario>() {

					@Override
					public boolean match(Usuario usuario) {
						return usuario.getNombreUsuario().equals("god");
					}
				});

		if (result.isEmpty()) {
			Repository.getInstance().store(
					new UsuarioFactory().construidGodMode());
		}
		
		setResponsePage(HomePage.class);

	}
}
