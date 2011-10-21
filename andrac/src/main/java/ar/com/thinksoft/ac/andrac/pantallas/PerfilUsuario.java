package ar.com.thinksoft.ac.andrac.pantallas;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import ar.com.thinksoft.ac.andrac.R;
import ar.com.thinksoft.ac.andrac.contexto.Aplicacion;
import ar.com.thinksoft.ac.andrac.contexto.Repositorio;
import ar.com.thinksoft.ac.andrac.dominio.Usuario;

/**
 * Se encarga de presentar los datos de perfil de usuario y da la posibilidad de
 * modificarlos.
 * 
 * @since 29-09-2011
 * @author Paul
 */
public class PerfilUsuario extends Activity {

	/**
	 * Se encarga de la creacion de la ventana.
	 * 
	 * @since 29-08-2011
	 * @author Paul
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.perfil);

		// Se cargan los datos de perfil a la pantalla.
		Usuario perfil = this.getRepo().getPerfilUsuario();
		if (perfil != null) {
			this.setNombre(perfil.getNombre());
			this.setApellido(perfil.getApellido());
			this.setUsuario(perfil.getNombreUsuario());
			this.setDNI(perfil.getDni());
			this.setMail(perfil.getMail());
			this.setTelefono(perfil.getTelefono());
		}
	}

	/**
	 * Atiende los cambios de configuracion, como rotacion de pantalla, etc...
	 * Refresca la imagen de background.
	 * 
	 * @since 07-09-2011
	 * @author Paul
	 * @param newConfig
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		this.getWindow().setBackgroundDrawableResource(R.drawable.wallpaper);
	}

	/**
	 * Cierra la ventana. Llamado por el boton Cancelar.
	 * 
	 * @since 28-08-2011
	 * @author Paul
	 * @param v
	 */
	public void salir(View v) {
		this.finish();
	}

	/**
	 * Devuelve Repositorio.
	 * 
	 * @since 28-09-2011
	 * @author Paul
	 * @return Repositorio.
	 */
	private Repositorio getRepo() {
		return ((Aplicacion) this.getApplication()).getRepositorio();
	}

	/* Metodos que obtienen el contenido de los campos de la pantalla */

	private void setNombre(String nombre) {
		((EditText) findViewById(R.id.nombre)).setText(nombre);
	}

	private String getNombre() {
		return ((EditText) findViewById(R.id.nombre)).getText().toString();
	}

	private void setApellido(String apellido) {
		((EditText) findViewById(R.id.apellido)).setText(apellido);
	}

	private String getApellido() {
		return ((EditText) findViewById(R.id.apellido)).getText().toString();
	}

	private void setUsuario(String usuario) {
		((EditText) findViewById(R.id.nick)).setText(usuario);
	}

	private String getUsuario() {
		return ((EditText) findViewById(R.id.nick)).getText().toString();
	}

	private void setDNI(String dni) {
		((EditText) findViewById(R.id.dni)).setText(dni + "");
	}

	private String getDNI() {
		return ((EditText) findViewById(R.id.dni)).getText().toString();
	}

	private void setMail(String mail) {
		((EditText) findViewById(R.id.mail)).setText(mail);
	}

	private String getMail() {
		return ((EditText) findViewById(R.id.mail)).getText().toString();
	}

	private void setTelefono(String telefono) {
		((EditText) findViewById(R.id.tel)).setText(telefono + "");
	}

	private String getTelefono() {
		return ((EditText) findViewById(R.id.tel)).getText().toString();
	}
}
