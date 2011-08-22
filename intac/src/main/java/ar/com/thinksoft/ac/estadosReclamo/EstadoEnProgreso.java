package ar.com.thinksoft.ac.estadosReclamo;

import ar.com.thinksoft.ac.intac.EnumEstadosReclamo;
import ar.com.thinksoft.ac.intac.IEstadoReclamo;

/**
 * @author Matias
 */
public class EstadoEnProgreso implements IEstadoReclamo{

	public String getDescripcionEstado() {
		return EnumEstadosReclamo.enProgreso.getEstado();
	}

}
