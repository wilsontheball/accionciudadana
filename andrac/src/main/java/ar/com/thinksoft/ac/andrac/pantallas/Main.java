package ar.com.thinksoft.ac.andrac.pantallas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import ar.com.thinksoft.ac.andrac.R;
import ar.com.thinksoft.ac.andrac.contexto.Aplicacion;
import ar.com.thinksoft.ac.andrac.contexto.Repositorio;
import ar.com.thinksoft.ac.andrac.servicios.ReceptorRest;
import ar.com.thinksoft.ac.andrac.servicios.ReceptorResultados;
import ar.com.thinksoft.ac.andrac.servicios.ServicioRest;
import ar.com.thinksoft.ac.intac.utils.classes.FuncionRest;

/**
 * La clase se encarga de manejar la pantalla Home.
 * 
 * @since 01-10-2011
 * @author Paul
 */
public class Main extends Activity implements ReceptorRest {

	private static final int DIALOGO_LOGIN = 0;
	private static final int ERROR_CONEXION = 1;
	private final int INICIAR_RECLAMO = 0;
	private final int LISTA_RECLAMOS = 1;
	private final int RECLAMOS_GUARDADOS = 2;
	private final int PERFIL_USUARIO = 3;
	private final int REGISTRAR_USUARIO = 4;

	private String[] ventanas = { "Iniciar Reclamo", "Lista Reclamos",
			"Reclamos Guardados", "Perfil Usuario", "Registrar Usuario" };

	private Intent servicioRest;
	private ReceptorResultados receptor;
	private ProgressDialog procesando = null;

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
	// @Override
	// protected void onStart() {
	// super.onStart();
	// // Muestra la ventana Login una sola vez
	// if (this.getAplicacion().getResultadoLogin() ==
	// Activity.RESULT_FIRST_USER) {
	// this.mostrarLogin();
	// }
	// }

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
			this.mostrarVentanaReclamo();
			break;
		case LISTA_RECLAMOS:
			this.iniciarServicioRest(FuncionRest.GETRECLAMOS);
			break;
		case RECLAMOS_GUARDADOS:
			this.mostrarVentanaReclamosGuardados();
			break;
		case PERFIL_USUARIO:
			this.mostrarLogin();
			// this.iniciarServicioRest(FuncionRest.GETPERFIL);
			break;
		case REGISTRAR_USUARIO:
			this.mostrarVentanaRegistro();
			break;
		default:
			break;
		}
	}

	/**
	 * Inicia una conexion al servidor REST.
	 * 
	 * @since 28-09-2011
	 * @author Paul
	 */
	private void iniciarServicioRest(String funcion) {

		try {
			// Crea un servicio.
			this.servicioRest = new Intent(Intent.ACTION_SYNC, null, this,
					ServicioRest.class);
			this.servicioRest.putExtra(ServicioRest.REC, this.getReceptor());
			this.servicioRest.putExtra(ServicioRest.FUN, funcion);
			this.startService(servicioRest);

		} catch (Exception e) {
			// TODO: mostrar error!!!!
			Toast.makeText(this, "Fallo iniciar servicio", Toast.LENGTH_SHORT)
					.show();
			Log.e(this.getClass().getName(), "Fallo iniciar servicio");
		}

	}

	/**
	 * Termina una conexion al servidor REST. Cuando no hay conexion activa no
	 * hace nada.
	 * 
	 * @since 28-09-2011
	 * @author Paul
	 */
	private void cancelarServicioRest() {
		this.stopService(this.servicioRest);
	}

	/**
	 * Muestra una ventana dialogo "Procesando". Al hacer clic en el boton
	 * finaliza servicio que corre en este momento.
	 * 
	 * @since 29-09-2011
	 * @author Paul
	 * @param mensaje
	 *            Texto que se va a mostrar en el dialogo.
	 */
	private void mostrarProcesando(String funcion) {
		this.procesando = new ProgressDialog(Main.this);

		// TODO Definir mensajes para todas las funciones
		String mensaje = "no tiene mensaje!!";
		if (FuncionRest.GETRECLAMOS.equals(funcion)) {
			mensaje = getString(R.string.obteniendo_reclamos);
		} else if (FuncionRest.GETPERFIL.equals(funcion)) {
			mensaje = getString(R.string.obteniendo_perfil);
		} else {
			Log.d(this.getClass().getName(), "Funcion sin mensaje: " + funcion);
			this.cancelarServicioRest();
			return;
		}
		this.procesando.setMessage(mensaje);
		this.procesando.setButton(getString(R.string.cancelar),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// TODO Debe parar servicio que corre
						cancelarServicioRest();
						dialog.cancel();
					}
				});
		this.procesando.setCancelable(false);
		this.procesando.show();
	}

	/**
	 * Cierra el dialogo en el caso que exista.
	 * 
	 * @since 28-09-2011
	 * @author Paul
	 */
	private void cerrarProcesando() {
		if (this.procesando != null) {
			this.procesando.dismiss();
			this.procesando = null;
		}
	}

	/**
	 * Devuelve contexto de aplicacion.
	 * 
	 * @since 28-09-2011
	 * @author Paul
	 * @return Contexto de aplicacion.
	 */
	private Aplicacion getAplicacion() {
		return (Aplicacion) this.getApplication();
	}

	/**
	 * Devuelve el Repositorio
	 * 
	 * @since 22-07-2011
	 * @author Paul
	 * @return repositorio
	 */
	private Repositorio getRepo() {
		return ((Aplicacion) this.getApplication()).getRepositorio();
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

	/**
	 * Atiende los resultados de los servicios REST.
	 * 
	 * @since 28-09-2011
	 * @author Paul
	 */
	public void onReceiveResult(int resultCode, Bundle funcionData) {

		String funcion = funcionData.getString(ServicioRest.FUN);
		Log.d(this.getClass().getName(), "Resultado: " + funcion + "["
				+ resultCode + "]");

		switch (resultCode) {
		case ServicioRest.RUN:
			// Servicio Arranco: Muestra dialogo procesando.
			mostrarProcesando(funcion);
			break;
		case ServicioRest.FIN:
			// Servicio Finalizo: Cierra dialogo procesando.
			this.cerrarProcesando();
			if (FuncionRest.GETRECLAMOS.equals(funcion)) {
				this.mostrarVentanaReclamos();
			} else if (FuncionRest.GETPERFIL.equals(funcion)) {
				this.mostrarVentanaPerfil();
			}

			break;
		case ServicioRest.ERROR:
			// Servicio Fallo: Cierra dialogo procesando.
			this.cerrarProcesando();
			// TODO handle the error;
			break;
		}

	}

	/**
	 * Crea ventanas de dialogo. (Se hace de esta forma en Android 2.2)
	 * 
	 * @since 23-08-2011
	 * @author Paul
	 */

	// TODO Terminar creacion de dialogos!!!!!!!!!!!!!!!!!!
	@Override
	protected Dialog onCreateDialog(int tipo) {
		Dialog unDialog = null;
		switch (tipo) {
		case DIALOGO_LOGIN:
			unDialog = new AlertDialog.Builder(Main.this)
			// .setIcon(R.drawable.icono)
			// .setTitle(R.string.login_titulo)
			// .setMessage(mensageAlerta)
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									finish();
								}
							}).create();
			break;
		case ERROR_CONEXION:
			unDialog = new AlertDialog.Builder(Main.this)
					.setIcon(R.drawable.alert_dialog_icon)
					.setTitle(R.string.error_conexion)
					.setMessage(R.string.mensaje_error_conexion)
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									/* Solo cierra el dialogo */
								}
							}).create();
			break;
		}
		return unDialog;
	}

	/**
	 * Devuelve receptor.
	 * 
	 * @since 28-09-2011
	 * @author Paul
	 * @return Objeto receptor.
	 */
	private ReceptorResultados getReceptor() {
		if (this.receptor == null) {
			this.receptor = new ReceptorResultados(new Handler());
			this.receptor.setReceiver(this);
		}
		return receptor;
	}

	/**
	 * Muestra la ventana de reclamo.
	 * 
	 * @since 29-09-2011
	 * @author Paul
	 */
	private void mostrarVentanaReclamo() {
		this.startActivity(new Intent(this, IniciarReclamo.class));
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
	 * Muestra la ventana de reclamos guardados en el celular.
	 * 
	 * @since 01-10-2011
	 * @author Paul
	 */
	private void mostrarVentanaReclamosGuardados() {
		this.startActivity(new Intent(this, ListaReclamosGuardados.class));
	}

	/**
	 * Muestra la ventana de perfil de usuario.
	 * 
	 * @since 29-09-2011
	 * @author Paul
	 */
	private void mostrarVentanaPerfil() {
		this.startActivity(new Intent(this, PerfilUsuario.class));
	}

	/**
	 * Muestra la ventana de registro de usuario.
	 * 
	 * @since 29-09-2011
	 * @author Paul
	 */
	private void mostrarVentanaRegistro() {
		this.startActivity(new Intent(this, Registro.class));
	}

}
