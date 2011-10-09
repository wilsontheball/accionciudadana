package ar.com.thinksoft.ac.andrac.contexto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ar.com.thinksoft.ac.andrac.dominio.Imagen;
import ar.com.thinksoft.ac.andrac.dominio.Reclamo;
import ar.com.thinksoft.ac.andrac.dominio.Usuario;
import ar.com.thinksoft.ac.intac.utils.classes.ImagenMovil;

/**
 * Buzon intermedio para intercambiar datos entre los procesos.
 * 
 * @since 08-10-2011
 * @author Paul
 */
public class Repositorio {

	// Almacenan temporalmente Usuario y Pass en una sesion.
	private String nick = null;
	private String pass = null;

	// Almacen intermedio para la foto. De esta forma no hace falta un archivo.
	private byte[] imagenBytes = null;

	// Almacen intermedio para perfil descargado.
	private Usuario perfilUsuario = null;

	// Almacen intermedio para los reclamos descargados.
	private List<Reclamo> reclamos = null;

	// XXX Almacen intermedio para los reclamos guardados???.
	private List<Reclamo> reclamosGuardados = null;

	// Almacen intermedio para reclamo a enviar.
	private Reclamo reclamoAEnviar = null;

	// Almacen intermedio para usuario a registrar.
	private Usuario usuarioARegistrar = null;

	/**
	 * 
	 * @since 22-07-2011
	 * @author Paul
	 * @param nick
	 *            nombre del usuario
	 * @param pass
	 *            contrasenia del usuario
	 * @return Usuario o null si la operacion fallo
	 */
	public Usuario getUsuario(String nick, String pass) {
		// TODO no esta implementada la conexion a la BD
		return null;
	}

	/**
	 * Registra a un nuevo usuario en el sistema.
	 * 
	 * @since 08-10-2011
	 * @author Paul
	 * 
	 * @param nombre
	 * @param apellido
	 * @param nick
	 * @param dni
	 * @param mail
	 * @param telefono
	 * @param password
	 * @return <Code>true</Code> si el usuario se registro correctamente o
	 *         <Code>false</Code> si el usuario no se pudo registrar.
	 */
	public boolean registrarUsuario(String nombre, String apellido,
			String nick, String dni, String mail, String telefono,
			String password) {
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setNombreUsuario(nick);
		usuario.setDni(dni);
		usuario.setMail(mail);
		usuario.setTelefono(telefono);
		usuario.setContrasenia(password);
		this.setUsuarioARegistrar(usuario);
		return true;
	}

	/**
	 * Obtiene los reclamos realizados del usuario listos para mostrar.
	 * 
	 * @since 25-09-2011
	 * @author Paul
	 * @return Array de reclamos listo para mostrar.
	 */
	public Reclamo[] getReclamosUsuario() {
		Reclamo[] arrayReclamos = new Reclamo[this.reclamos.size()];

		// Se hace asi por que falla el metodo de conversion de lista.
		int i = 0;
		for (Reclamo rec : this.reclamos) {
			arrayReclamos[i++] = rec;
		}
		return arrayReclamos;
	}

	/**
	 * Guarda los reclamos realizados del usuario tal como llegaron del
	 * servidor.
	 * 
	 * @since 28-09-2011
	 * @author Paul
	 * @param reclamos
	 *            Array de reclamos tal como llego de servidor.
	 */
	public void setReclamosUsuario(List<Reclamo> reclamos) {
		this.reclamos = reclamos;
	}

	/**
	 * Obtiene los reclamos guardados en el celular.
	 * 
	 * @since 04-10-2011
	 * @author Hernan
	 * @return Array de reclamos listo para mostrar.
	 */
	public Reclamo[] getReclamosGuardados() {

		// TODO Falta implementar como levantar los reclamos guardados!!!!!!!!
		Reclamo[] arrayReclamosGuardados = new Reclamo[this.reclamosGuardados
				.size()];
		// Se hace asi por que falla el metodo de conversion de lista.
		int i = 0;
		for (Reclamo rec : this.reclamosGuardados) {
			arrayReclamosGuardados[i++] = rec;
		}
		return arrayReclamosGuardados;
	}

	public void setReclamosGuardados(List<Reclamo> reclamos) {
		this.reclamosGuardados = reclamos;
	}

	public void publicarReclamoDireccion(String tipo, String barrio,
			String calle, String altura, String observacion) {

		Imagen imagen = new Imagen(this.getImagen(), ImagenMovil.TIPO_JPG);
		String fecha = this.getFechaConFormato();
		Reclamo reclamo = new Reclamo(calle, altura, null, null, tipo, fecha,
				fecha, this.getNick(), observacion, barrio, imagen);
		this.setReclamoAEnviar(reclamo);
	}

	public boolean publicarReclamoGPS(String tipo, String barrio,
			double latitud, double longitud, String observacion) {

		Imagen imagen = new Imagen(this.getImagen(), ImagenMovil.TIPO_JPG);
		String fecha = this.getFechaConFormato();
		Reclamo reclamo = new Reclamo(null, null, latitud + "", longitud + "",
				tipo, fecha, fecha, this.getNick(), observacion, barrio, imagen);
		this.setReclamoAEnviar(reclamo);
		return true;
	}

	/* **************** Getters y Setters ****************** */

	private void setReclamoAEnviar(Reclamo reclamoAEnviar) {
		this.reclamoAEnviar = reclamoAEnviar;
	}

	public Reclamo getReclamoAEnviar() {
		return this.reclamoAEnviar;
	}

	public void setImagen(byte[] imagen) {
		this.imagenBytes = imagen;
	}

	public byte[] getImagen() {
		return this.imagenBytes;
	}

	public Usuario getPerfilUsuario() {
		return this.perfilUsuario;
	}

	public void setPerfilUsuario(Usuario perfil) {
		this.perfilUsuario = perfil;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String usuario) {
		this.nick = usuario;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass() {
		// Aseguro que no devuelva null;
		if (this.pass == null) {
			return "";
		} else {
			return this.pass;
		}
	}

	public String getFechaConFormato() {
		Date date = new java.util.Date();
		SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"dd/MM/yyyy");
		return formatter.format(date);
	}

	public String getHoraConFormato() {
		Date date = new java.util.Date();
		SimpleDateFormat formatter = new java.text.SimpleDateFormat("hh-mm-ss");
		return formatter.format(date);
	}

	public Usuario getUsuarioARegistrar() {
		return usuarioARegistrar;
	}

	public void setUsuarioARegistrar(Usuario usuarioARegistrar) {
		this.usuarioARegistrar = usuarioARegistrar;
	}
}
