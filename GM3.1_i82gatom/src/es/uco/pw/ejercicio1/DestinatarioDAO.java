package es.uco.pw.ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * <b><u>DestinatarioDAO.java</u></b>
 * 
 * <p>Representa la clase DestinatarioDAO del proyecto.</p>
 * 
 * @author Maria Garcia Torres
 * @author Elena Alvarez Sanchez
 * @version 1.0
 */
public class DestinatarioDAO {
	
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
	 * @param guardarDestinatario		Consulta MySQL para guardar un destinatario de un anuncio
	 * @param ad						Envoltorio anuncio que contiene la informacion del anuncio  
	 * @param destinatario				Envoltorio usuario que contiene el correo del usuario que se quiere almacenar
	 * @return status					Valor que indica cuantas filas de la tabla han sido modificadas
	 */
	public static int saveRecipient(String guardarDestinatario, AdBean ad, UserBean destinatario)
	{
		int status=0;
		try{
			Connection con=getConnection();
			// PreparedStament será más rápido (si es uso recurrente) y permite invocación a parámetros
			PreparedStatement ps=con.prepareStatement(guardarDestinatario);
			// El orden de los parámetros debe coincidir con las '?' del código SQL
			ps.setInt(1,ad.getID());
			ps.setString(2,destinatario.getEmail());
			status = ps.executeUpdate();
		// Importante capturar las excepciones. Si nuestra aplicaciones tiene más opciones de fallo,
		// podemos capturar directamente SQLException
		}catch(Exception e){System.out.println(e);}
		// El invocante siempre debería tener información del resultado de la sentencia SQL
		return status;
	}
	
	/**
	 * 
	 * @param buscarEmail				Consulta MySQL para buscar por el destinatario de un anuncio
	 * @param user						Envoltorio usuario que contiene el correo del usuario que se quiere buscar
	 * @return resul					ID de los anuncios en los que user es el destinatario
	 */
	public static ArrayList<Integer> queryByEmail (String buscarEmail, UserBean user) {
		Statement stmt = null; 
		ArrayList<Integer> resul = new ArrayList<Integer>();
		try {
			Connection con=getConnection();
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(buscarEmail + "'" + user.getEmail() + "'");
		    while (rs.next()) {
		    	resul.add(rs.getInt("IdAnuncio"));
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
