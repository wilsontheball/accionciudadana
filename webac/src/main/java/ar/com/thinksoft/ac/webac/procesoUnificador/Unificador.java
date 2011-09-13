package ar.com.thinksoft.ac.webac.procesoUnificador;

import java.util.Timer;
import java.util.TimerTask;

public class Unificador {

    private Timer timer;
    
    public Unificador() {
    	
        timer = new Timer();
		timer.schedule(new TimerTask(){
			
			/**
			 * Este metodo corre la tarea diaria, ya que 86400 son la cantidad de segundos que tiene un dia.
			 * Falta modificar para que sea a una determinada hora... porque si se cae el servidor a las 4 de la
			 * tarde y se levanta nuevamente, va a ejecutar la tarea al otro dia a las 4 de la tarde. Quiza por el
			 * negocio no nos convenga, ya que se volveria lento el sistema (en el unico caso que haya muchos reclamos
			 * a unificar).
			 */
			@Override
			public void run() {
				
				/*
	        	 * Aca va el metodo unificador
	        	 */
	        	        	
	        	timer.cancel(); 
				
			}
		},
		0, 86400);
    }
}  
