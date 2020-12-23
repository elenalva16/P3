package com.connection;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
 
public class Conexion {
	
	//private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	//private static final String URL = "jdbc:mysql://oraclepr.uco.es:3306/i82gatom";
	//private static final String USUARIO = "i82gatom";
	//private static final String CLAVE = "practica";
	
	/**
	 * 
	 * @return con La conexión será utilizada para llevar satisfactoriamente los cambios hechos. 
	 */
	  public static Connection getConnection(){

			Connection con=null;
			
			Context env = null;
			  
			try {
				env = (Context)new InitialContext().lookup("java:comp/env");
			
			} catch (NamingException e1) {
				e1.printStackTrace();
			}

			String CONTROLADOR = null;
			
			//Comprobación del controlador
			try {
				CONTROLADOR = (String)env.lookup("controlador");
			
			} catch (NamingException e1) {
				System.err.println("Error: Controlador (conexion.java)");
				e1.printStackTrace();
			}
			
			String URL = null;
			
			//Comprobación de la url de la base de datos
			try {
				URL = (String)env.lookup("url_conex");
			
			} catch (NamingException e1) {
				System.err.println("Error: Url (conexion.java)");
				e1.printStackTrace();
			}
			
			String USUARIO = null;
			
			//Comprobación del nombre de la base de datos
			try {
				USUARIO = (String)env.lookup("usuario");
			
			} catch (NamingException e1) {
				System.err.println("Error: Usuario (conexion.java)");
				e1.printStackTrace();
			}
			
			String CLAVE = null;
			
			//Comprobación de la contraseña de la base de datos
			try {
				CLAVE = (String)env.lookup("clave");
			
			} catch (NamingException e1) {
				System.err.println("Error: Clave (conexion.java)");
				e1.printStackTrace();
			}
			
			//Comprobación de que la base de datos está funcionando correctamente
			try {
				
			  Class.forName(CONTROLADOR);
			  con = (Connection) DriverManager.getConnection(URL, USUARIO, CLAVE);
			  //System.out.println("Conexión OK");
			  
			} catch(ClassNotFoundException e) {
			  System.err.println("Error al cargar el controlador");
			  e.printStackTrace();
			
			} catch (SQLException e) {
				System.err.println("Error en la conexión");
				e.printStackTrace();
			}
			
			//Devolvemos la conexión
			return con;
			
	 }  

}