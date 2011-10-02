package ar.com.thinksoft.ac.andrac.servicios;

import android.os.Bundle;

/**
 * Interfaz para las clases que quieran recibir resuldatodos de los servicios.
 * 
 * @since 28-09-2011
 * @author Paul
 */
public interface ReceptorRest {
	public void onReceiveResult(int resultCode, Bundle resultData);
}
