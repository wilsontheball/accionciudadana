package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

/**
 * Se encarga de presentar los datos de perfil de usuario y da la posibilidad de
 * modificarlos.
 * 
 * @since 07-09-2011
 * @author Paul
 */
public class PerfilUsuario extends Activity {

	/**
	 * Se encarga de la creacion de la ventana.
	 * 
	 * @since 28-08-2011
	 * @author Paul
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.perfil);
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
}
