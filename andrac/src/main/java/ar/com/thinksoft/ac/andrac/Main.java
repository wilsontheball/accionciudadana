package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * La clase se encarga de manejar la pantalla Home.
 * 
 * @since 19-07-2011
 * @author Paul
 * 
 */
public class Main extends Activity {

	private static String TAG = "andrac";

	/**
	 * Se encarga de la creacion de la ventana. Hace que ventana de Login
	 * aparezca primero.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		setContentView(R.layout.main);

		// Muestra la ventana Login
		this.mostrarLogin();
	}

	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// super.onActivityResult(requestCode, resultCode, data);
	// // TODO
	// }

	/**
	 * Muestra la ventana de Login.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 */
	private void mostrarLogin() {
		Intent intent = new Intent(this, Login.class);
		startActivity(intent);
		// TODO
		// startActivityForResult(new Intent(this, Login.class), 0);
	}

	/**
	 * Muestra la ventana para iniciar un reclamo.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 */
	public void iniciarReclamo(View v) {
		// TODO falta hacer la ventana

	}

	/**
	 * Muestra la ventana con listado de reclamos realizados por el usuario.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 */
	public void mostrarReclamos(View v) {
		// TODO
	}

	/**
	 * Muestra la ventana con datos del perfil de usuario.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 */
	public void mostrarPerfil(View v) {
		// TODO falta hacer la ventana

	}
	
	public String getRepo(){
		return "Hola";
	}
}
