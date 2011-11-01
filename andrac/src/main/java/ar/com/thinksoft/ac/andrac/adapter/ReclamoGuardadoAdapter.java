package ar.com.thinksoft.ac.andrac.adapter;

import java.util.StringTokenizer;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ar.com.thinksoft.ac.andrac.R;

/**
 * Se encarga de representar un item dentro del listado de reclamos guardados.
 * 
 * @since 31-10-2011
 * @author Paul
 */
public class ReclamoGuardadoAdapter extends ArrayAdapter<String> {

	// Almacena acceso a la Activity padre
	Activity context = null;
	// Almacena la coleccion de reclamos
	String[] reclamos;

	public ReclamoGuardadoAdapter(Activity context, String[] reclamos) {
		super(context, R.layout.lista_reclamos_guardados_item, reclamos);
		this.context = context;
		this.reclamos = reclamos;
	}

	/**
	 * Asigna los valores de Reclamo a un item de la ListView
	 * 
	 * @author Paul
	 * @since 31-10-2011
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup padre) {
		LayoutInflater inflater = this.context.getLayoutInflater();
		View item = inflater.inflate(R.layout.lista_reclamos_guardados_item,
				null);

		// Obtiene el control
		ImageView icono = (ImageView) item.findViewById(R.id.item_estado);

		// Muestra el icono del estado de reclamo
		icono.setImageResource(R.drawable.alert_dialog_icon);

		StringTokenizer tk = new StringTokenizer(this.reclamos[position]);

		// Mustra el tipo de incidente
		if (tk.hasMoreTokens()) {
			String tipoIncidente = tk.nextToken();
			TextView titulo = (TextView) item.findViewById(R.id.item_tipo);
			titulo.setText(tipoIncidente);
			Log.d(this.getClass().getName(), "Tipo incidente:" + tipoIncidente);
		}

		// Muestra fecha
		if (tk.hasMoreTokens()) {
			String fechaIncidente = tk.nextToken();
			TextView subTitulo = (TextView) item.findViewById(R.id.item_barrio);
			subTitulo.setText(fechaIncidente.replace('-', '/'));
		}

		return (item);
	}
}
