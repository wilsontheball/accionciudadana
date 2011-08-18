package ar.com.thinksoft.ac.estadosReclamo;

import ar.com.thinksoft.ac.intac.EnumEstadosReclamo;
import ar.com.thinksoft.ac.intac.IEstadoReclamo;

/**
 * @author Matias
 */
public class EstadoSuspendido implements IEstadoReclamo{

	public String getDescripcionEstado() {
		return EnumEstadosReclamo.suspendido.getEstado();
	}

}
