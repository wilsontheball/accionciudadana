package ar.com.thinksoft.ac.intac;

public interface IPermitible {

	public abstract IPermiso getPermisoNecesario();
	public boolean puedeAcceder(IUsuario usuario);
	
}
