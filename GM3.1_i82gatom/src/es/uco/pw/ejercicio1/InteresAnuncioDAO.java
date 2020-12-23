package es.uco.pw.ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * <b><u>InteresAnuncioDAO.java</u></b>
 * 
 * <p>Representa la clase InteresAnuncioDAO del proyecto.</p>
 * 
 * @author Maria Garcia Torres
 * @author Elena Alvarez Sanchez
 * @version 1.0
 */
public class InteresAnuncioDAO {
	
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
	 * @param guardarTagsAnuncio		Consulta MySQL para guardar los intereses de un anuncio
	 * @param ad						Envoltorio anuncio que almacena el ID 
	 * @param tag						ID del interes que se quiere guardar
	 * @return status					Valor que indica cuantas filas de la tabla han sido modificadas
	 */
	public static int saveTags(String guardarTagsAnuncio, AdBean ad, Integer tag)
	{
		int status=0;
		try{
			Connection con=getConnection();
			// PreparedStament será más rápido (si es uso recurrente) y permite invocación a parámetros
			PreparedStatement ps=con.prepareStatement(guardarTagsAnuncio);
			// El orden de los parámetros debe coincidir con las '?' del código SQL
			ps.setInt(1,ad.getID());
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
	 * @param buscarTagAnuncio		Consulta MySQL para buscar un anuncio segun su interes
	 * @param IdTag					ID del interes segun el que se quiere buscar
	 * @return resul				ID del anuncio cuyo interes es IdTag
	 */
	public static ArrayList<Integer> queryByTag (String buscarTagAnuncio, Integer IdTag) {
		Statement stmt = null; 
		ArrayList<Integer> resul = new ArrayList<Integer>();
		try {
			Connection con=getConnection();
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(buscarTagAnuncio + "'" + IdTag + "'");
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
	
	/**
	 * 
	 * @param borrarTagsAnuncio		Consulta MySQL para borrar los intereses de un anuncio
	 * @param ad					Envoltorio anuncio que almacena el ID
	 * @return status				Valor que indica cuantas filas de la tabla han sido modificadas
	 */
	public static int deleteTagsAd(String borrarTagsAnuncio, AdBean ad)
	{
		int status=0;
		try{
			Connection con=getConnection();
			PreparedStatement ps=con.prepareStatement(borrarTagsAnuncio);
			ps.setInt(1,ad.getID());
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}

		return status;
	}
	
}
