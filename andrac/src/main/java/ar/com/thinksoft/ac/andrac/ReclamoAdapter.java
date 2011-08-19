package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Adapta ReclamoItem a un item de la ListView
 * 
 * @author Paul
 * @since 11-08-2011
 */
public class ReclamoAdapter extends ArrayAdapter<ReclamoItem> {

	// Almacena acceso a la Activity padre
	Activity context = null;
	// Almacena la coleccion de reclamos
	ReclamoItem[] reclamos;

	ReclamoAdapter(Activity context, ReclamoItem[] reclamos) {
		super(context, R.layout.lista_reclamos_item, reclamos);
		this.context = context;
		this.reclamos = reclamos;
	}

	/**
	 * Asigna los valores de Reclamo a un item de la ListView
	 * 
	 * @author Paul
	 * @since 11-08-2011
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = this.context.getLayoutInflater();
		View item = inflater.inflate(R.layout.lista_reclamos_item, null);

		// Asigna el icono del estado de reclamo
		ImageView icono = (ImageView) item.findViewById(R.id.item_estado);
		icono.setImageResource(R.drawable.ic_popup_reminder);

		// Asigna el tipo de incidente
		TextView titulo = (TextView) item.findViewById(R.id.item_tipo);
		titulo.setText(this.reclamos[position].getTipo() + "  [" + position
				+ "]");

		// Asigna la direccion
		TextView subTitulo = (TextView) item.findViewById(R.id.item_direccion);
		subTitulo.setText(this.reclamos[position].getDireccion() + "  "
				+ this.reclamos[position].getFechaHora());

		return (item);
	}
}
