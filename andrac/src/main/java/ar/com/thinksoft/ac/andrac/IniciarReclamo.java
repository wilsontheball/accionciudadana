package ar.com.thinksoft.ac.andrac;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class IniciarReclamo extends Activity {
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.iniciar_reclamo);
		
		final String[] items = new String[] { "Coordenada GPS", "Dirección" };
		Spinner spinner = (Spinner) findViewById(R.id.tipo_incidente);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
				String selectedItem = items[position];

			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}


		});
		
		((EditText) findViewById(R.id.calle)).setEnabled(true);
		

	}
}
