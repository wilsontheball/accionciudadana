package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * La clase se encarga de manejar la pantalla Home.
 * @since 19-07-2011
 * @author Paul
 *
 */
public class Main extends Activity {

	private static String TAG = "andrac";

	/**
	 * Se encarga de la creacion de la ventana. Hace que ventana de Login aparezca primero.
	 * @since 19-07-2011
	 * @author Paul
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		setContentView(R.layout.main);

//		Muestra la ventana Login
		this.mostrarLogin();

		Button botonLogin = (Button) findViewById(R.id.boton_ok);

		// this is the action listener
		botonLogin.setOnClickListener(new OnClickListener() {

			public void onClick(View viewParam) {
				Intent intent = new Intent(viewParam.getContext(), Login.class);
				startActivity(intent);
			}
		});

	}

	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// super.onActivityResult(requestCode, resultCode, data);
	// // TODO
	// }

	/**
	 * Muestra la ventana de Login.
	 * @since 19-07-2011
	 * @author Paul
	 */
	private void mostrarLogin() {
		Intent intent = new Intent(this, Login.class);
		startActivity(intent);
		// startActivityForResult(new Intent(this, Login.class), 0);
	}

}
