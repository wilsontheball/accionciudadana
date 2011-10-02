package ar.com.thinksoft.ac.andrac.pantallas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import ar.com.thinksoft.ac.andrac.R;

public class ListaReclamosGuardados extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reclamos_guardados);
	}

	/**
	 * Cierra la ventana. Llamado por el boton Atras.
	 * 
	 * @since 01-10-2011
	 * @author Hernan
	 * @param v
	 */
	public void salir(View v) {
		this.finish();
	}
}
