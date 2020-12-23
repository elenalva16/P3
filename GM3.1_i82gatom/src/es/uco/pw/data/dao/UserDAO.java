package es.uco.pw.data.dao;

import com.mysql.jdbc.Connection;

import java.sql.*;
import java.util.LinkedList;

import com.connection.Conexion;
import es.uco.pw.properties.Propiedades;
import es.uco.pw.display.javabean.CustomerBean;
import es.uco.pw.business.user.User;

public class UserDAO {
	
		Propiedades prop = new Propiedades();
		CustomerBean customerBean = new CustomerBean ();

		/**
		 * Constructor de la clase UserDAO
		 * @param prop Es un objeto properties que contiene las lineas del fichero sql.properties
		 */
		public UserDAO(java.util.Properties prop) {
		  this.prop.setPropiedades(prop);
		}

		/**
		 * Método para insertar una fila en la base de datos
		 * 
		 * @param email Email a insertar en la base de datos 
		 * @param nombre Nombre a insertar en la base de datos
		 * @param apellidos Apellidos a insertar en la base de datos
		 * @param fechanacimiento Fecha de nacimiento a insertar en la base de datos
		 * @param clave Clave a insertar en la base de datos
		 * @return Devuelve 'true' si la insercción se ha hecho correctamente y 'false' si ha habido algun problema. 
		 */
		  public boolean save(String email, String nombre, String apellidos, String fechanacimiento, String clave){
			int status=0;
			try{
				Connection con = null;
				//Establecer conexión con la base de datos
				con=(Connection) Conexion.getConnection();
				
				//Obtenemos la línea del sql.properties con la cuál accederemos a la base de datos
				int num = 1;
				String save = prop.propiedades(num);
				PreparedStatement ps=con.prepareStatement(save);

				//Realizamos la insercción de los datos en la línea del sql.properties, según el orden de los parámetros. 
				ps.setString(1,email);
				ps.setString(2,nombre);
				ps.setString(3,apellidos);
				ps.setString(4,fechanacimiento);
				ps.setString(5,clave);
				
				//'status' nos indicará si la insercción se ha hecho de forma correcta. 
				status = ps.executeUpdate();
				
				if(status == 1) {
					return true;
				}

			}catch(Exception e){System.err.println("Ha habido problemas al registrar al nuevo usuario"); System.err.println(e);}

			return false;
		}
		 
		/**
		 * Método para comprobar que el email introducido por el usuario, se encunetra en la base de datos. 
		 * @param email Email a buscar en la base de datos
		 * @param clave Clave a buscar en la base de datos
		 * @return Devuelve 'true' si el email y clave son únicos y se encuentran en la base de datos. 
		 */
		public boolean autenticacion (String email, String clave) {
			PreparedStatement ps = null; 
			ResultSet rs = null;
			
			try {
				Connection con = null;
				//Establecer conexión con la base de datos
				con=(Connection) Conexion.getConnection();
				int num = 2;
				String query = prop.propiedades(num);
			
				ps=con.prepareStatement(query);
				
				//Realizamos la insercción de los datos en la línea del sql.properties, según el orden de los parámetros.	    
			    ps.setString(1, email);
			    ps.setString(2, clave);
			    
			    rs = ps.executeQuery();
			    
			    //Comprobamos que el email sea único. 
			    if(rs.absolute(1)) {
			    	return true;
			    }
	  
			} catch (Exception e) { 
				System.err.println("Error: " + e); 
			} finally {
				try {
					if (ps != null) ps.close(); 
					if (rs != null) rs.close();
				} catch(Exception e) {
					System.err.println("Error1: " + e);
				}
			}
			
			return false;
		} 
		
		/**
		 * Método para actualizar nombre del usuario con sesión iniciada
		 * 
		 * @param nombre Nombre nuevo 
		 * @param apellidos Apellidos nuevos
		 * @param email Email del usuario con sesión iniciada
		 * @return Devuelve 'true' si todo a ido bien, y 'false' si ha habido algún problema. 
		 */
		public boolean updateName(String nombre, String apellidos, String email){
			int status=0;
			try{
				Connection con = null;
				con=(Connection) Conexion.getConnection();
				
				int num = 3;
				String updateName = prop.propiedades(num);
				PreparedStatement ps=con.prepareStatement(updateName);
				
				ps.setString(1,nombre);
				ps.setString(2,apellidos);
				ps.setString(3, email);
				
				status=ps.executeUpdate();
		
				if(status == 1) {
					return true;
				}
			}catch(Exception e){System.out.println("Error: Ha habido problemas al actualizar el nombre del usuario"); System.out.println(e);}
			return false;
		}
		
		/**
		 * Método para actualizar la fecha de nacimiento del usuario con sesión iniciada
		 * 
		 * @param fechaNacimiento Fecha de nacimiento nueva
		 * @param email Email del usuario con sesión iniciada
		 * @return Devuelve 'true' si todo a ido bien, y 'false' si ha habido algún problema.
		 */
		public boolean updateFechaNacimiento(String fechaNacimiento, String email){
			int status=0;
			try{
				Connection con = null;
				con=(Connection) Conexion.getConnection();
				
				int num = 4;
				String updateFecha = prop.propiedades(num);
				PreparedStatement ps=con.prepareStatement(updateFecha);
				
				ps.setString(1,fechaNacimiento);
				ps.setString(2,email);
				
				status=ps.executeUpdate();
		
				if(status == 1) {
					return true;
				}
				
			}catch(Exception e){System.out.println("Error: Ha habido problemas al actualizar la fecha de nacimiento del usuario"); System.out.println(e);}
			return false;
		}
		
		/**
		 * Método para actualizar la contraseña del usuario con sesión iniciada
		 * 
		 * @param clave Clave nueva
		 * @param email Email del usuario con sesión iniciada
		 * @return Devuelve 'true' si todo a ido bien, y 'false' si ha habido algún problema.
		 */
		public boolean updateClave(String clave, String email){
			int status=0;
			try{
				Connection con = null;
				con=(Connection) Conexion.getConnection();
				
				int num = 5;
				String updateClave = prop.propiedades(num);
				PreparedStatement ps=con.prepareStatement(updateClave);
			
				ps.setString(1,clave);
				ps.setString(2,email);
				
				status=ps.executeUpdate();
		
				if(status == 1) {
					return true;
				}
			}catch(Exception e){System.out.println("Error: Ha habido problemas al actualizar la contraseña del usuario"); System.out.println(e);}
			return false;
		}
		
		/**
		 * Método para mostrar la información de la persona que se encuentra dentro de la sesión. 
		 *  
		 * @param email Email del usuario con sesión iniciada
		 * @return Devuelve una lista con las caracterísitcas del usuario con sesión iniciada (nombre, apellidos,...)
		 */
		public static LinkedList<User> infoPersona(String email){
			Statement stmt = null; 
			 LinkedList<User> listaUser=new LinkedList<User>();
			try {
				Connection con = null;
				con=(Connection)Conexion.getConnection();
				
				
				// En consultas, se hace uso de un Statement 
				
				stmt = con.createStatement();
			    ResultSet rs = stmt.executeQuery("select Nombre, Apellidos, FechaNacimiento, Email from Usuario where Email='" + email+ "'");
				
			    while (rs.next()) {
			 
			        User user = new User();
			        user.setNombre(rs.getString("Nombre"));
			        user.setApellidos(rs.getString("Apellidos"));
			        user.setFechaNacimiento(rs.getDate("FechaNacimiento"));
			        user.setEmail(email);      
			        listaUser.add(user);
			    }
			    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
			    if (stmt != null) 
			    	stmt.close(); 
			} catch (Exception e) {
				System.err.println("Error: " + e);
			} 
			return listaUser;
		}
		
		/**
		 * Comprobar que no existe un email igual al introducido en la base de datos (Para el registro)
		 * 
		 * @param email Email del usuario con sesión iniciada
		 * @return Devuelve 'true' si todo a ido bien, y 'false' si ha habido algún problema.
		 */
		public boolean comprobarEmail (String email) {
			PreparedStatement ps = null; 
			ResultSet rs = null;
			
			try {
				//Establecer conexión con la base de datos
				Connection con=(Connection) Conexion.getConnection();
				int num = 7;
				String query = prop.propiedades(num);
			
				ps=con.prepareStatement(query);
				
				//Realizamos la insercción de los datos en la línea del sql.properties, según el orden de los parámetros.	    
			    ps.setString(1, email);
			    rs = ps.executeQuery();
			    
			    //Comprobamos que el email sea único. 
			    if(rs.absolute(1)) {
			    	return true;
			    }
	  
			} catch (Exception e) { 
				System.err.println("Error: " + e); 
			} finally {
				try {
					if (ps != null) ps.close(); 
					if (rs != null) rs.close();
				} catch(Exception e) {
					System.err.println("Error1: " + e);
				}
			}
			
			return false;
		} 
}