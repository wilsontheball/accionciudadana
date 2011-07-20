package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * La clase se encarga de manejar la pantalla de Autentificacion
 * @since 19-07-2011
 * @author Paul
 *
 */
public class Login extends Activity {

	private static String TAG = "andrac";
	private static Activity hilo = null;
	private int resultado = Activity.RESULT_OK;

	/**
	 * Se encarga de la creacion de la ventana. Le asigna Layout.
	 * @since 19-07-2011
	 * @author Paul
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
//TODO
		/*
		
		Button botonLogin = (Button) findViewById(R.id.boton_ok);

		// this is the action listener
		botonLogin.setOnClickListener(new OnClickListener() {

			public void onClick(View viewParam) {
				// this gets the resources in the xml file and assigns it to a
				// local variable of type EditText
				EditText usernameEditText = (EditText) findViewById(R.id.campo_nick);
				EditText passwordEditText = (EditText) findViewById(R.id.campo_pass);

				// the getText() gets the current value of the text box
				// the toString() converts the value to String data type
				// then assigns it to a variable of type String
				String sUserName = usernameEditText.getText().toString();
				String sPassword = passwordEditText.getText().toString();

				// this just catches the error if the program cant locate the
				// GUI stuff
				if (usernameEditText == null || passwordEditText == null) {
					showAlert("Crap!",
							"Couldn't find the 'txt_username' or 'txt_password' "
									+ "EditView in main.xml", "Oh shit!", false);
				} else {
					// display the username and the password in string format
					showAlert("Logging in", "Username: " + sUserName
							+ "nPassword: " + sPassword, "Ok", true);
				}
			}
		}

		); // end of launch.setOnclickListener
		
		
		Button botonSalir = (Button) findViewById(R.id.boton_salir);
		botonSalir.setOnClickListener(new OnClickListener() {
			public void onClick(View viewParam) {
				hilo.finish();
			}
		}

		); // end of launch.setOnclickListener
		
		this.hilo = this;
		*/
	}

	protected void showAlert(String string, String string2, String string3,
			boolean b) {
		// TODO Auto-generated method stub

	}

	/**
	 * Se ejecuta cuando se cierra la ventana. Devuelve resultado de su ejecucion al proceso padre.
	 * @since 19-07-2011
	 * @author Paul
	 */
	@Override
	public void onDestroy() {
//		TODO
//		setResult(this.resultado);
		super.onDestroy();
		
	}
	
	/**
	 * Valida Usuario y Contraseña. Metodo llamado por el boton Aceptar.
	 * @since 19-07-2011
	 * @author Paul
	 * @param v
	 */
	public void validar (View v){
//		TODO
	}
	
	/**
	 * Muestra la ventana de Registro. Metodo llamado por el boton Registro
	 * @since 19-07-2011
	 * @author Paul
	 * @param v
	 */
	public void registro (View v){
		Intent intent = new Intent(v.getContext(), Registro.class);
		startActivity(intent);
	}
	
	/**
	 * Cierra la ventana. Asigna resultado negativo.
	 * @since 19-07-2011
	 * @author Paul
	 * @param v
	 */
	public void salir (View v)
	{
		this.finish();
	}

}
