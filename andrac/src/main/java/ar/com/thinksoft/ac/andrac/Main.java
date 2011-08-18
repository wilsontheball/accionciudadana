package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * La clase se encarga de manejar la pantalla Home.
 * 
 * @since 10-08-2011
 * @author Paul
 */
public class Main extends Activity {

	// Flag que asegura que Login se muestre una sola vez
	private int resultadoLogin = Activity.RESULT_FIRST_USER;

	/**
	 * Se encarga de la creacion de la ventana.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
	}

	/**
	 * Hace que ventana de Login aparezca primero.
	 * 
	 * @since 17-08-2011
	 * @author Paul
	 */
	@Override
	protected void onStart() {
		super.onStart();
		// Muestra la ventana Login una sola vez
		if (this.resultadoLogin == Activity.RESULT_FIRST_USER) {
			this.mostrarLogin();
			this.resultadoLogin = Activity.RESULT_CANCELED;
		}
	}

	/**
	 * Captura la respuesta de la ventana Login
	 * 
	 * @author Paul
	 * @since 22-07-2011
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		this.resultadoLogin = resultCode;
		if (resultadoLogin == Activity.RESULT_CANCELED) {
			Toast.makeText(this, R.string.cerrar_aplicacion, Toast.LENGTH_LONG)
					.show();
			this.finish();
		} else {
			Toast.makeText(this, R.string.login_exito, Toast.LENGTH_LONG)
					.show();
		}
	}

	/**
	 * Muestra la ventana de Login esperando resultado de ejecucion.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 */
	private void mostrarLogin() {
		this.startActivityForResult(new Intent(this, Login.class), 0);
	}

	/**
	 * Muestra la ventana para iniciar un reclamo.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 */
	public void iniciarReclamo(View v) {
		this.startActivity(new Intent(v.getContext(), IniciarReclamo.class));
	}

	/**
	 * Muestra la ventana con listado de reclamos realizados por el usuario.
	 * 
	 * @since 06-08-2011
	 * @author Paul
	 */
	public void mostrarReclamos(View v) {
		this.startActivity(new Intent(v.getContext(), ListaReclamos.class));
	}

	/**
	 * Muestra la ventana con datos del perfil de usuario.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 */
	public void mostrarPerfil(View v) {
		// TODO falta hacer la ventana,
	}
}
