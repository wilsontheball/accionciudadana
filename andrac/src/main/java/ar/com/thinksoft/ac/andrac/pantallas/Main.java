package ar.com.thinksoft.ac.andrac.pantallas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
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
import ar.com.thinksoft.ac.andrac.servicios.ServicioRest;
import ar.com.thinksoft.ac.intac.utils.classes.FuncionRest;

/**
 * La clase se encarga de manejar la pantalla Home.
 * 
 * @since 10-10-2011
 * @author Paul
 */
public class Main extends Activity {

	private static final int ERROR_CONEXION = -1;
	private final int INICIAR_RECLAMO = 0;
	private final int LISTA_RECLAMOS = 1;
	private final int RECLAMOS_GUARDADOS = 2;
	private final int PERFIL_USUARIO = 3;
	private final int REGISTRAR_USUARIO = 4;

	// Nombres de los items de la lista.
	private String[] ventanas = { "Iniciar Reclamo", "Reclamos Enviados",
			"Reclamos Guardados", "Perfil Usuario", "Registrar Usuario" };

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
	 * @since 10-10-2011
	 * @author Paul
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			Log.d(this.getClass().getName(),
					"Se ejecuto: " + data.getStringExtra(ServicioRest.FUN));
			if (FuncionRest.GETPERFIL.equals(data
					.getStringExtra(ServicioRest.FUN))) {
				this.mostrarVentanaPerfil();
			} else if (FuncionRest.GETRECLAMOS.equals(data
					.getStringExtra(ServicioRest.FUN))) {
				this.mostrarVentanaReclamos();
			}
		} else if (resultCode == Activity.RESULT_CANCELED) {
			Log.e(this.getClass().getName(),
					"No pudo ejecutar: "
							+ data.getStringExtra(ServicioRest.FUN));
			this.mostrarDialogo(ERROR_CONEXION);
		} else if (resultCode == Activity.RESULT_FIRST_USER) {
			Log.d(this.getClass().getName(), "Usuario cancelo ejecucion: "
					+ data.getStringExtra(ServicioRest.FUN));
		} else {
			Log.e(this.getClass().getName(),
					"Resultado de ejecucion no esperado");
		}
	}

	/**
	 * Detecta el evento del boton fisico que cancela la aplicacion. Cierra la
	 * aplicacion. Otros botones delega para arriba.
	 * 
	 * @since 10-10-2011
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
			this.salir();
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	/**
	 * Crea ventanas de dialogo. (Se hace de esta forma en Android 2.2)
	 * 
	 * @since 10-10-2011
	 * @author Paul
	 */
	@Override
	protected Dialog onCreateDialog(int tipo) {
		Dialog unDialog = null;
		switch (tipo) {

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
	 * Muestra la ventana de Login esperando resultado de ejecucion.
	 * 
	 * @since 19-07-2011
	 * @author Paul
	 */
	private void ejecutarFuncion(String funcion) {
		Intent proceso = new Intent(this, Login.class);
		proceso.putExtra(ServicioRest.FUN, funcion);
		this.startActivityForResult(proceso, 0);
	}

	/**
	 * Muestra una ventana de dialogo.
	 * 
	 * @since 10-10-2011
	 * @author Paul
	 */
	private void mostrarDialogo(int idice) {
		this.showDialog(idice);
	}

	/**
	 * Muestra una ventana segun la opcion seleccionada en la lista.
	 * 
	 * @since 04-10-2011
	 * @author Paul
	 * @param posicion
	 */
	private void mostrarVentana(int posicion) {
		switch (posicion) {
		case INICIAR_RECLAMO:
			this.mostrarVentanaIniciarReclamo();
			break;
		case LISTA_RECLAMOS:
			this.ejecutarFuncion(FuncionRest.GETRECLAMOS);
			// this.iniciarServicioRest(FuncionRest.GETRECLAMOS);
			break;
		case RECLAMOS_GUARDADOS:
			this.mostrarVentanaReclamosGuardados();
			break;
		case PERFIL_USUARIO:
			this.ejecutarFuncion(FuncionRest.GETPERFIL);
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
	 * Cierra la ventana. Llamado por el boton Salir.
	 * 
	 * @since 10-10-2011
	 * @author Paul
	 * @param v
	 */
	public void salir(View v) {
		this.salir();
	}

	/**
	 * Cierra la ventana, borra usuario, muestra mensaje.
	 * 
	 * @since 10-10-2011
	 * @author Paul
	 * @param v
	 */
	private void salir() {
		Toast.makeText(this, R.string.cerrar_aplicacion, Toast.LENGTH_LONG)
				.show();

		// Limpia nick y pass.
		this.getRepo().setNick(null);
		this.getRepo().setPass(null);

		this.finish();
	}

	/**
	 * Devuelve el repositorio.
	 * 
	 * @since 22-07-2011
	 * @author Paul
	 * @return aplicacion
	 */
	private Repositorio getRepo() {
		return ((Aplicacion) this.getApplication()).getRepositorio();
	}

	/**
	 * Muestra la ventana de reclamo.
	 * 
	 * @since 29-09-2011
	 * @author Paul
	 */
	private void mostrarVentanaIniciarReclamo() {
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
