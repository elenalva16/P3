package es.uco.pw.ejercicio1;

import java.sql.Date;

/**
 * <b><u>UserBean.java</u></b>
 * 
 * <p>Representa la clase envoltorio UserBean del proyecto.</p>
 * 
 * @author Maria Garcia Torres
 * @author Elena Alvarez Sanchez
 * @version 1.0
 */
public class UserBean {

	private String email;
	private String nombre;
	private String apellidos;
	private Date fechaN;
	
	/**
	 * Constructor vacio
	 */
	public UserBean()
	{
		email = null;
		nombre = null;
		apellidos = null;
		fechaN = null;
	}
	
	// Getters & setters
	/**
	 * @return email	email del contacto
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @return nombre	nombre del contacto
	 */
	public String getNombre() { 
	     return nombre; 
	}
	
	/**
	 * @return apellidos	apellidos del contacto
	 */
	public String getApellidos() { 
	     return apellidos; 
	}
	
	/**
	 * @return fechaN	fecha de nacimiento del contacto
	 */
	public Date getFechaN() {
		return fechaN;
	}
	
	/**
	 * @param mail	email del contacto
	 */
	public void setEmail(String mail) {
		email = mail;
	}
	
	/**
	 * @param nom	nombre del contacto
	 */
	public void setNombre(String nom) {
		nombre = nom;
	}
	
	/**
	 * @param apell	apellidos del contacto
	 */
	public void setApellidos(String apell) {
		apellidos = apell;
	}
	
	/**
	 * @param fecha	fecha de nacimiento del contacto
	 */
	public void setFechaN(Date fecha)
	{
		fechaN = fecha;
	}
	
}
