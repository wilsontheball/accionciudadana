package ar.com.thinksoft.ac.estadosReclamo;

import ar.com.thinksoft.ac.intac.EnumEstadosReclamo;
import ar.com.thinksoft.ac.intac.IEstadoReclamo;

/**
 * @author Matias
 */
public class EstadoAsociado implements IEstadoReclamo{

	public String getDescripcionEstado() {
		return EnumEstadosReclamo.asociado.getEstado();
	}

}
