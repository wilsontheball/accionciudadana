package ar.com.thinksoft.ac.webac.usuario;

import java.util.ArrayList;
import java.util.List;

public enum EnumTiposUsuario {
	
	Administrador("Administrador"),Operador("Operador"), Ciudadano("Ciudadano");
	
	private String tipo;
	private static List<String> listaTiposUsuario = new ArrayList<String>();
	
	private EnumTiposUsuario(String tipo) {
    	this.tipo = tipo;
    }
    
    public static List<String> getlistaTiposUsuarios(){
    	listaTiposUsuario.clear();
    	listaTiposUsuario.add(EnumTiposUsuario.Administrador.getTipoUsuario());
    	listaTiposUsuario.add(EnumTiposUsuario.Operador.getTipoUsuario());
    	listaTiposUsuario.add(EnumTiposUsuario.Ciudadano.getTipoUsuario());
    	
    	return listaTiposUsuario;
    }

    public String getTipoUsuario() {
    	return tipo;
    }

};
