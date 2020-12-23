package es.uco.pw.business.user;

import java.util.Date;

public class User {
	String email = "";
	String nombre = "";
	String apellidos = "";
	Date fechanacimiento;
	String clave = "";
	
	/**
	 * Constructor de la clase User
	 * 
	 * @param email Email del usuario
	 * @param name Nombre del usuario
	 * @param apellidos Apellidos del usuario
	 * @param fechanacimiento //Fecha de nacimiento del usuario
	 * @param clave //Clave del usuario
	 */
	public User (String email, String name, String apellidos, Date fechanacimiento, String clave) {
		this.email = email;
		this.nombre = name;
		this.apellidos = apellidos;
		this.fechanacimiento = fechanacimiento;
		this.clave = clave; 
	}
	
	/**
	 * Constructor de la clase User
	 */
	public User() {}

	/**
	 * setter
	 * 
	 * @return Devuelve el email del usuario
	 */
	public String getEmail() { return email; }
	
	/**
	 * getter
	 * 
	 * @param email Email del usuario
	 */
	public void setEmail(String email) { this.email = email; }
	
	/**
	 * setter
	 * 
	 * @return Devuelve el nombre del usuario
	 */	
	public String getNombre() { return nombre; }
	
	/**
	 * getter 
	 * 
	 * @param nombre Nombre del usuario
	 */
	public void setNombre(String nombre) { this.nombre = nombre; }
	
	/**
	 * getter
	 * 
	 * @return Devuelve los apellidos del usuario
	 */
	public String getApellidos() { return apellidos; }
	
	/**
	 * setter
	 * 
	 * @param apellidos Apellidos del usuario
	 */
	public void setApellidos(String apellidos) { this.apellidos = apellidos; }
	
	/**
	 * getter 
	 * 
	 * @return Devuelve la fecha de nacimiento del usuario
	 */
	public Date getFechaNacimiento() { return fechanacimiento; }
	
	/**
	 * setter
	 * 
	 * @param fechanacimiento Fecha de nacimiento del usuario
	 */
	public void setFechaNacimiento(Date fechanacimiento) { this.fechanacimiento = fechanacimiento; }
	
	/**
	 * getter 
	 * 
	 * @return Devuelve la clave del usuario
	 */
	public String getClave() { return clave; }
	
	/**
	 * setter 
	 * 
	 * @param clave Clave del usuario
	 */
	public void setClave(String clave) { this.clave = clave; }
	
	/**
	 * @return Devuelve todas las variables de este fichero, llamando a cada uno de sus getters. 
	 */
	public String toString() { return "Email: " + this.getEmail() + "; nombre: " + this.getNombre() + "; apellidos: " + this.getApellidos() + "; fechaNacimiento: " + this.getFechaNacimiento(); }
	
}
