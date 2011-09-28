package ar.com.thinksoft.ac.andrac.pantallas;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import ar.com.thinksoft.ac.andrac.R;
import ar.com.thinksoft.ac.andrac.contexto.Aplicacion;

/**
 * La clase se encarga de manejar la pantalla Home.
 * 
 * @since 25-09-2011
 * @author Paul
 */
public class Main extends Activity {

	private final int INICIAR_RECLAMO = 0;
	private final int LISTA_RECLAMOS = 1;
	private final int PERFIL_USUARIO = 2;

	private ProgressDialog procesando = null;
	private boolean obtenerReclamosCancelado = false;

	private String[] ventanas = { "Iniciar Reclamo", "Lista Reclamos",
			"Perfil Usuario" };

	/**
	 * Se encarga de la creacion de la ventana.
	 * 
	 * @since 28-08-2011
	 * @author Paul
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// TODO Hacer que se muestre la info de ayuda por cada item
		// Carga el listado con las funcionalidades.
		ListView listado = (ListView) findViewById(R.id.list);
		listado.setAdapter(new ArrayAdapter<String>(Main.this,
				android.R.layout.simple_list_item_1, ventanas));
		listado.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int posicion, long id) {
				mostrarVentana(posicion);
			}
		});
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
		if (this.getAplicacion().getResultadoLogin() == Activity.RESULT_FIRST_USER) {
			this.mostrarLogin();
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
	 * Captura la respuesta de la ventana Login.
	 * 
	 * @since 06-09-2011
	 * @author Paul
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			this.getAplicacion().setResultadoLogin(Activity.RESULT_OK);
			Toast.makeText(this, R.string.login_exito, Toast.LENGTH_LONG)
					.show();
		} else if (resultCode == Activity.RESULT_CANCELED) {
			this.salir(null);
		} else {
			Toast.makeText(this, R.string.advertencia, Toast.LENGTH_LONG)
					.show();
		}
	}

	/**
	 * Detecta el evento del boton fisico que cancela la aplicacion. Muestra
	 * mensaja de cierre y cierra la sesion abierta. Cierra la aplicacion.
	 * 
	 * @since 28-08-2011
	 * @author Paul
	 * @param keyCode
	 *            Codigo del boton presionado.
	 * @param event
	 *            Evento del boton presionado.
	 * @return Siempre true.
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Toast.makeText(this, R.string.cerrar_aplicacion, Toast.LENGTH_LONG)
					.show();
			this.getAplicacion().setResultadoLogin(Activity.RESULT_FIRST_USER);
			this.finish();
		}
		return true;
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
	 * Muestra una ventana segun la opcion seleccionada en la lista.
	 * 
	 * @since 28-08-2011
	 * @author Paul
	 * @param posicion
	 */
	private void mostrarVentana(int posicion) {
		switch (posicion) {
		case INICIAR_RECLAMO:
			this.startActivity(new Intent(this, IniciarReclamo.class));
			break;
		case LISTA_RECLAMOS:
			this.mostrarObteniendoReclamos();
			break;
		case PERFIL_USUARIO:
			this.startActivity(new Intent(this, PerfilUsuario.class));
			break;
		default:
			break;
		}
	}

	/**
	 * Muestra la ventana de reclamos de usuario.
	 * 
	 * @since 25-09-2011
	 * @author Paul
	 */
	private void mostrarVentanaReclamos() {
		this.startActivity(new Intent(this, ListaReclamos.class));
	}

	/**
	 * Muestra un dialogo procesando.
	 * 
	 * @since 25-09-2011
	 * @author Paul
	 */
	private void mostrarObteniendoReclamos() {
		this.procesando = new ProgressDialog(Main.this);
		this.procesando.setMessage(getString(R.string.obteniendo_reclamos));
		this.procesando.setButton(getString(R.string.cancelar),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// TODO Debe parar obtension de reclamos
						obtenerReclamosCancelado = true;
						dialog.cancel();
					}
				});
		this.procesando
				.setOnDismissListener(new DialogInterface.OnDismissListener() {

					public void onDismiss(DialogInterface dialog) {
						// TODO Auto-generated method stub
						if (obtenerReclamosCancelado == true) {
							mostrarVentanaReclamos();
						}

					}

				});
		this.procesando.setCancelable(false);
		this.procesando.show();
	}

	private Aplicacion getAplicacion() {
		return (Aplicacion) this.getApplication();
	}

	/**
	 * Cierra la ventana. Llamado por el boton Salir.
	 * 
	 * @since 28-08-2011
	 * @author Paul
	 * @param v
	 */
	public void salir(View v) {
		Toast.makeText(this, R.string.cerrar_aplicacion, Toast.LENGTH_LONG)
				.show();
		this.getAplicacion().setResultadoLogin(Activity.RESULT_FIRST_USER);
		this.finish();
	}

}
