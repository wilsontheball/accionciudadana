/**
 * 
 */
package ar.com.thinksoft.ac.andrac.pantallas;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import ar.com.thinksoft.ac.andrac.R;
import ar.com.thinksoft.ac.andrac.servicios.ServicioRest;

/**
 * @since 01-11-11
 * @author Marian
 */
public class SplashScreen extends Activity {

	private EsperaSplash esperaSplash;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);

		Intent intent = new Intent(this, Main.class);

		// Espero un tiempo y sigo con el Main
		this.esperaSplash = new EsperaSplash(this, intent);
		new Thread(this.esperaSplash).start();
	}

	/**
	 * 
	 * @author Marian
	 * 
	 */
	private final class EsperaSplash implements Runnable {

		private static final int TiempoEspera = 1250;
		private boolean activo = true;
		private Activity activity;
		private Intent intent;

		public boolean isActivo() {
			return activo;
		}

		public void setActivo(boolean activo) {
			this.activo = activo;
		}

		EsperaSplash(Activity ac, Intent intent) {
			this.activity = ac;
			this.intent = intent;
		}

		public void run() {

			try {
				Thread.sleep(TiempoEspera);
			} catch (InterruptedException e) {
				Log.e(this.getClass().getName(),
						"Fallo la espera del splash, se inicia la aplicacion igual");
			}

			if (this.isActivo()) {

				this.iniciarActivity();
			}
		}

		public void iniciarActivity() {
			this.activity.startActivity(intent);
			this.activity.finish();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Log.d(this.getClass().getName(), "Se cancelo la espera del splash");
			this.esperaSplash.setActivo(false);
			this.esperaSplash.iniciarActivity();
		}

		return true;
	}
}