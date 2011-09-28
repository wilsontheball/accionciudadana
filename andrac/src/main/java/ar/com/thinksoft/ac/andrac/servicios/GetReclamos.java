package ar.com.thinksoft.ac.andrac.servicios;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import ar.com.thinksoft.ac.andrac.contexto.Aplicacion;

public class GetReclamos extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getUrl() {
		return ((Aplicacion) this.getApplication()).getRepositorio()
				.getSrvUrl();
	}

}
