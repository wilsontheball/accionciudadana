package ar.com.thinksoft.ac.estadosReclamo;

import ar.com.thinksoft.ac.intac.EnumEstadosReclamo;
import ar.com.thinksoft.ac.intac.IEstadoReclamo;

/**
 * @author Matias
 */
public class EstadoDemorado implements IEstadoReclamo{

	public String getDescripcionEstado() {
		return EnumEstadosReclamo.demorado.getEstado();
	}

}
