package ar.com.thinksoft.ac.estadosReclamo;

import ar.com.thinksoft.ac.intac.IEstadoReclamo;

/**
 * @author Matias
 */
public class EstadoCancelado implements IEstadoReclamo{

	public String getDescripcionEstado() {
		return "cancelado";
	}

}