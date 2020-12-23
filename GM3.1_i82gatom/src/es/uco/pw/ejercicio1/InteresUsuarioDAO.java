package es.uco.pw.ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Hashtable;
import java.util.Properties;

/**
 * <b><u>InteresUsuarioDAO.java</u></b>
 * 
 * <p>Representa la clase InteresUsuarioDAO del proyecto.</p>
 * 
 * @author Maria Garcia Torres
 * @author Elena Alvarez Sanchez
 * @version 1.0
 */
public class InteresUsuarioDAO {
	
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
	 * @param guardarTags		Consulta MySQL para guardar un interes en la base de datos
	 * @param user				Envoltorio usuario que contiene el correo del usuario
	 * @param tag				ID del tag que se quiere guardar
	 * @return status			Valor que indica cuantas filas de la tabla han sido modificadas
	 */
	public static int saveTags(String guardarTags, UserBean user, Integer tag)
	{
		int status=0;
		try{
			Connection con=getConnection();
			// PreparedStament será más rápido (si es uso recurrente) y permite invocación a parámetros
			PreparedStatement ps=con.prepareStatement(guardarTags);
			// El orden de los parámetros debe coincidir con las '?' del código SQL
			ps.setString(1,user.getEmail());
			ps.setInt(2,tag);
			
			status = ps.executeUpdate();
			
		// Importante capturar las excepciones. Si nuestra aplicaciones tiene más opciones de fallo,
		// podemos capturar directamente SQLException
		}catch(Exception e){System.out.println(e);}
		// El invocante siempre debería tener información del resultado de la sentencia SQL
		return status;
	}
	
	/**
	 * 
	 * @param buscarTag			Cadena de MySQL para buscar un usuario en la base de datos por su interes
	 * @param ID				ID del tag que se quiere guardar
	 * @return resul			Hashtable con las filas de la tabla que cumplen la condición
	 */
	public static Hashtable<String,String> queryByTag (String buscarTag, Integer ID) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(buscarTag + "'" + ID + "'");
		    while (rs.next()) {
		    	String email = rs.getString("Email");
		        
		    	String id = Integer.toString(ID);
		    	
		        resul = new Hashtable<String,String>();
		        resul.put("Email", email);
		        resul.put("idTag", id);       
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
	 * @param borrarTags		Cadena de MySQL para borrar un interes de un usuario
	 * @param user				Envoltorio usuario donde se almacena el usuario
	 * @return status			Valor que indica cuantas filas de la tabla han sido modificadas
	 */
	public static int deleteTags(String borrarTags, UserBean user)
	{
		int status=0;
		try{
			Connection con=getConnection();
			PreparedStatement ps=con.prepareStatement(borrarTags);
			ps.setString(1,user.getEmail());
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}

		return status;
	}

}
