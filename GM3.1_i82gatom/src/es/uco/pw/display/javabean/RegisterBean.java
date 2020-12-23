package es.uco.pw.display.javabean;
import java.util.Date;

public class RegisterBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String nombreUser = "";
	private String apellidosUser = "";
	private Date fechanacimientoUser;
	private String emailUser = "";
	private String password = "";
	private int contador = 1;
	
	/**
	 * getter 
	 * 
	 * @return Devuelve el nombre de la persona que se está registrando
	 */
	public String getNombreUser() { return nombreUser; }

	/**
	 * setter 
	 * 
	 * @param nombreUser Nombre de la persona que se está registrando
	 */
	public void setNombreUser(String nombreUser) { this.nombreUser = nombreUser; }
	
	/**
	 * getter 
	 * @return Devuelve los apellidos de la persona que se está registrando
	 */
	public String getApellidosUser () { return apellidosUser; }
	
	/**
	 * setter 
	 * 
	 * @param apellidosUser Apellidos de la persona que se está registrando
	 */
	public void setApellidosUser (String apellidosUser) { this.apellidosUser = apellidosUser; }
	
	/**
	 * getter 
	 * 
	 * @return Devuelve la fecha de nacimiento de la persona que se está registrando
	 */
	public Date getFechanacimientoUser () { return fechanacimientoUser; }
	
	/**
	 * setter 
	 * @param fechanacimientoUser Fecha de nacimiento de la persona que se está registrando
	 */
	public void setFechanacimientosUser (Date fechanacimientoUser) { this.fechanacimientoUser = fechanacimientoUser; }

	/**
	 * getter
	 * 
	 * @return Devuelve el email de la persona que se está registrando
	 */
	public String getEmailUser() { return emailUser; }

	/**
	 * seter 
	 * 
	 * @param emailUser Email de la persona que se está registrando
	 */
	public void setEmailUser(String emailUser) { this.emailUser = emailUser; }
	
	/**
	 * getter
	 * 
	 * @return Devuelve la clave de la persona que se está registrando
	 */
	public String getPassword () { return password; }
	
	/**
	 * setter
	 * 
	 * @param password Clave de la persona que se está registrando
	 */
	public void setPassword (String password) { this.password = password; }
	
	/**
	 * setter 
	 * 
	 * @param contador Contador para controlar si los datos han sido erróneamente introducidos y mostrar así mensjae de error
	 */
	public void setContador (int contador) { this.contador=contador; }
	
	/**
	 * getter 
	 * 
	 * @return Devuelve un número, y dependiendo de ese número se mostrarán o no un mensaje de error. 
	 */
	public int getContador() { return contador; }
	
}
