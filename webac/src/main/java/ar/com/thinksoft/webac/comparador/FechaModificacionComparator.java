package ar.com.thinksoft.webac.comparador;

import java.util.Comparator;

import ar.com.thinksoft.ac.intac.IReclamo;

public class FechaModificacionComparator implements Comparator<IReclamo> {

		public int compare(IReclamo o1, IReclamo o2) { 
		        IReclamo reclamo1 = o1; 
		        IReclamo reclamo2 = o2; 
		        return reclamo1.getFechaUltimaModificacionReclamo().compareTo(reclamo2.getFechaUltimaModificacionReclamo()); 

		}
}
