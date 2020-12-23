package es.uco.pw.display.javabean;

public class CustomerBean implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private String emailUser = "";
	private String password = "";
	int contador;
	
	/**
	 * getter 
 	 * @return Devuelve el email del usuario 
	 */
	public String getEmailUser() { return emailUser; }

	/**
	 * setter 
	 * 
	 * @param emailUser Email del usuario
	 */
	public void setEmailUser(String emailUser) { this.emailUser = emailUser; }
	
	/**
	 * getter
	 * 
	 * @return Devuelve la clave del usuario
	 */
	public String getPassword () { return password; }
	
	/**
	 * setter 
	 * 
	 * @param password Clave del usuario 
	 */
	public void setPassword (String password) { this.password = password; }

	/**
	 * getter 
	 * 
	 * @return Devuevle el contador de veces que se ha iniciado sesión
	 */
	public int getContador () { return contador; }
	
	/**
	 * setter 
	 * 
	 * @param contador Número de veces que se ha iniciado sesión
	 */
	public void setContador (int contador) { this.contador = contador; }
	
}
