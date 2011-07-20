package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * La clase se encarga de manejar la pantalla de Registro.
 * @since 19-07-2011
 * @author Paul
 *
 */
public class Registro extends Activity {
	/**
	 * Se encarga de la creacion de la ventana.
	 * @since 19-07-2011
	 * @author Paul
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registro);
	}

	/**
	 * Registra al usuario y cierra la ventana. Responde al boton Aceptar.
	 * @since 19-07-2011
	 * @author Paul
	 * @param v
	 */
	public void registrar(View v) {
//		TODO
		this.salir(v);
	}
	
	/**
	 * Cierra la ventana. Responde al boton Salir.
	 * @since 19-07-2011
	 * @author Paul
	 * @param v
	 */
	public void salir (View v)
	{
		this.finish();
	}
}
