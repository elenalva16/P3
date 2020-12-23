package es.uco.pw.ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Properties;

/**
 * <b><u>ContactoDAO.java</u></b>
 * 
 * <p>Representa la clase ContactoDAO del proyecto.</p>
 * 
 * @author Maria Garcia Torres
 * @author Elena Alvarez Sanchez
 * @version 1.0
 */
public class ContactoDAO {
	
	//1. Singleton
	private static ContactoDAO instance = null;
		
	//2. Private constructor
	private ContactoDAO() {}
		
	//3. Access point to instance
	public static ContactoDAO getInstance() {
		if(instance == null) { instance = new ContactoDAO(); }
		return instance;
	}
	
	/**
	 * Establece la conexión con la base de datos
	 */
	public static Connection getConnection()
	{
		// Obtener una instancia del driver de MySQL
		Connection con=null;
		
		Properties prop = new Properties();
		String properties = "config.properties";
		String url = null;
		String user = null;
		String password = null;
		
		try 
		{
			BufferedReader datos = new BufferedReader(new FileReader(new File(properties)));
			prop.load(datos);

			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
		}
		catch (FileNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }
		try {
			  Class.forName("com.mysql.jdbc.Driver");
			  // Introducir correctamente el nombre y datos de conexión - Idealmente, estos datos se 
			  // indican en un fichero de propiedades
			  con= DriverManager.getConnection(url,user,password);
		}
		catch(Exception e) 
		{
		  System.out.println(e);
		}
		return con;
	}
	
	/**
	 * 
	 * @param guardarContacto	Cadena de MySQL para guardar un contacto en la base de datos 
	 * @param user				Envoltorio usuario donde se almacena el usuario que debe guardarse en la base de datos
	 * @return status			Valor que indica cuantas filas de la tabla han sido modificadas
	 */
	public static int saveContact(String guardarContacto, UserBean user){
		int status=0;
		try{
			Connection con=getConnection();
			// PreparedStament será más rápido (si es uso recurrente) y permite invocación a parámetros
			PreparedStatement ps=con.prepareStatement(guardarContacto);
			// El orden de los parámetros debe coincidir con las '?' del código SQL
			ps.setString(1,user.getEmail());
			ps.setString(2,user.getNombre());
			ps.setString(3,user.getApellidos());
			ps.setDate(4,user.getFechaN());
			status = ps.executeUpdate();
		// Importante capturar las excepciones. Si nuestra aplicaciones tiene más opciones de fallo,
		// podemos capturar directamente SQLException
		}catch(Exception e){System.out.println(e);}
		// El invocante siempre debería tener información del resultado de la sentencia SQL
		return status;
	}
	
	/**
	 * 
	 * @param buscarEmail		Cadena de MySQL para buscar un contacto en la base de datos por su correo
	 * @param user				Envoltorio usuario donde se almacena el correo que se quiere buscar
	 * @return resul			Hashtable con las filas de la tabla que cumplen la condición
	 */
	public static Hashtable<String,String> queryByMail (String buscarEmail, UserBean user) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(buscarEmail + "'" + user.getEmail() + "'");
		    while (rs.next()) {
		    	String nombre = rs.getString("Nombre");
		        String apellidos = rs.getString("Apellidos");
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		        String fechaN = sdf.format(rs.getDate("FechaNacimiento"));
		        
		        resul = new Hashtable<String,String>();
		        resul.put("Email", user.getEmail());
		        resul.put("Nombre", nombre);
		        resul.put("Apellidos", apellidos);
		        resul.put("FechaNacimiento", fechaN);        
		        System.out.println("Email: " + user.getEmail() + " , Nombre: " + nombre + " " + apellidos + " , Fecha de nacimiento: " + fechaN);
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return resul;
	} 
	
	/**
	 * 
	 * @param borrarContacto	Cadena de MySQL para borrar un contacto en la base de datos
	 * @param user				Envoltorio usuario donde se almacena el correo cuyo contacto se quiere borrar
	 * @return status 			Valor que indica cuantas filas de la tabla han sido modificadas
	 */
	public static int deleteContact(String borrarContacto, UserBean user)
	{
		int status=0;
		try{
			Connection con=getConnection();
			PreparedStatement ps=con.prepareStatement(borrarContacto);
			ps.setString(1,user.getEmail());
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}

		return status;
	}
	
	/**
	 * 
	 * @param modificarContacto	Cadena de MySQL para modificar un contacto en la base de datos
	 * @param user				Envoltorio usuario donde se almacena el correo cuyo contacto se quiere modificar
	 * @return status			Valor que indica cuantas filas de la tabla han sido modificadas
	 */
	public static int updateContact(String modificarContacto, UserBean user)
	{
		int status=0;
		try{
			Connection con=getConnection();
			PreparedStatement ps=con.prepareStatement(modificarContacto);
			ps.setString(1,user.getNombre());
			ps.setString(2,user.getApellidos());
			ps.setDate(3,user.getFechaN());
			// En este caso, 'id' va después, conforme al orden de la sentencia SQL
			ps.setString(4,user.getEmail());
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	
	/**
	 *
	 * @param buscarNombre		Cadena de MySQL para buscar un contacto en la base de datos por su nombre
	 * @param buscarApellidos	Cadena de MySQL para buscar un contacto en la base de datos por sus apellidos
	 * @param user				Envoltorio usuario donde se almacena el correo que se quiere buscar
	 * @return resul			Hashtable con las filas de la tabla que cumplen la condición
	 */ 
	public static Hashtable<String,String> queryByName (String buscarNombre, String buscarApellidos, UserBean user) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(buscarNombre + "'" + user.getNombre() + "'" + buscarApellidos + "'" + user.getApellidos() + "'");
		    while (rs.next()) {
		    	String email = rs.getString("Email");
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		        String fechaN = sdf.format(rs.getDate("FechaNacimiento"));
		        
		        resul = new Hashtable<String,String>();
		        resul.put("Email", email);
		        resul.put("Nombre", user.getNombre());
		        resul.put("Apellidos", user.getApellidos());
		        resul.put("FechaNacimiento", fechaN);        
		        System.out.println("Email: " + email + " , Nombre: " + user.getNombre() + " " + user.getApellidos() + " , Fecha de nacimiento: " + fechaN);
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return resul;
	}
	
	/**
	 * 
	 * @param buscarEdad		Cadena de MySQL para buscar un contacto en la base de datos por su edad
	 * @param year				Año en el que deberia haber nacido el contacto que buscamos
	 * @return resul			Hashtable con las filas de la tabla que cumplen la condición
	 */
	public static Hashtable<String,String> queryByAge (String buscarEdad, int year) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(buscarEdad + year);
		    while (rs.next()) {
		    	String email = rs.getString("Email");
		    	String nombre = rs.getString("Nombre");
		        String apellidos = rs.getString("Apellidos");
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		        String fechaN = sdf.format(rs.getDate("FechaNacimiento"));
		        
		        resul = new Hashtable<String,String>();
		        resul.put("Email", email);
		        resul.put("Nombre", nombre);
		        resul.put("Apellidos", apellidos);
		        resul.put("FechaNacimiento", fechaN);        
		        System.out.println(email + "\t" + nombre +
		                               "\t" + apellidos + "\t" + fechaN);
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return resul;
	} 
	
	/**
	 * 
	 * @param consultarDatos	Cadena de MySQL para consultar todos los contactos almacenados en la base de datos
	 * @return resul 			Hashtable con las filas de la tabla que cumplen la condición
	 */
	public static Hashtable<String,String> checkData (String consultarDatos) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(consultarDatos);
		    while (rs.next()) {
		    	String email = rs.getString("Email");
		    	String nombre = rs.getString("Nombre");
		        String apellidos = rs.getString("Apellidos");
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		        String fechaN = sdf.format(rs.getDate("FechaNacimiento"));
		        
		        resul = new Hashtable<String,String>();
		        resul.put("Email", email);
		        resul.put("Nombre", nombre);
		        resul.put("Apellidos", apellidos);
		        resul.put("FechaNacimiento", fechaN);        
		        System.out.println(email + "\t" + nombre +
		                               "\t" + apellidos + "\t" + fechaN);
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return resul;
	} 

}
