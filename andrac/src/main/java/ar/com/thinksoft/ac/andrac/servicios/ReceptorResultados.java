package ar.com.thinksoft.ac.andrac.servicios;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class ReceptorResultados extends ResultReceiver {

	private ReceptorRest receptor;

	public ReceptorResultados(Handler handler) {
		super(handler);
	}

	public void setReceiver(ReceptorRest receptor) {
		this.receptor = receptor;
	}

	@Override
	protected void onReceiveResult(int resultCode, Bundle resultData) {
		if (this.receptor != null) {
			this.receptor.onReceiveResult(resultCode, resultData);
		}
	}

}
