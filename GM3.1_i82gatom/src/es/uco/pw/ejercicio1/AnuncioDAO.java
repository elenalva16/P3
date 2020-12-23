package es.uco.pw.ejercicio1;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Properties;
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

/**
 * <b><u>AnuncioDAO.java</u></b>
 * 
 * <p>Representa la clase AnuncioDAO del proyecto.</p>
 * 
 * @author Maria Garcia Torres
 * @author Elena Alvarez Sanchez
 * @version 1.0
 */
public class AnuncioDAO {
	
	private static AnuncioDAO instance = null;
	
	private AnuncioDAO() {}
	
	public static AnuncioDAO getInstance() {
		if(instance == null) { instance = new AnuncioDAO(); }
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
	 * @param cambiarEstadoEspera			Consulta MySQL para cambiar el estado de los anuncios flash en espera
	 * @param cambiarEstadoArchivado		Consulta MySQL para cambiar el estado de los anuncios flash publicados
	 * @return status						Valor que indica cuantas filas de la tabla han sido modificadas
	 */
	public static int cambiarEstadoEspera(String cambiarEstadoEspera, String cambiarEstadoArchivado)
	{
		int status=0;
		try{
			Connection con=getConnection();
			PreparedStatement ps=con.prepareStatement(cambiarEstadoEspera);
			status=ps.executeUpdate();
			ps=con.prepareStatement(cambiarEstadoArchivado);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	
	/**
	 * 
	 * @param guardarAnuncio				Consulta MySQL para guardar un anuncio en la base de datos
	 * @param ad							Envoltorio anuncio que se va a guardar en la base de datos
	 * @return status						Valor que indica cuantas filas de la tabla han sido modificadas
	 */
	public static int saveAd(String guardarAnuncio, AdBean ad){
		int status=0;
		try{
			Connection con=getConnection();
			// PreparedStament será más rápido (si es uso recurrente) y permite invocación a parámetros
			PreparedStatement ps=con.prepareStatement(guardarAnuncio);
			// El orden de los parámetros debe coincidir con las '?' del código SQL
			ps.setInt(1,ad.getID());
			ps.setString(2,ad.getTitulo());
			ps.setString(3,ad.getCuerpo());
			ps.setDate(4,ad.getFecha());
			ps.setString(5,ad.getPropietario());
			ps.setString(6,ad.getEstado());
			ps.setString(7, ad.getTipo());
			ps.setDate(8, ad.getInicio());
			ps.setDate(9,ad.getFin());
			status = ps.executeUpdate();
		// Importante capturar las excepciones. Si nuestra aplicaciones tiene más opciones de fallo,
		// podemos capturar directamente SQLException
		}catch(Exception e){System.out.println(e);}
		// El invocante siempre debería tener información del resultado de la sentencia SQL
		return status;
	}
	
	/**
	 * 
	 * @param mostrarAnuncioGeneral			Consulta MySQL para mostrar los anuncios generales publicados
	 * @param ordenarPropietario			Consulta MySQL para ordenar segun usuario propietario
	 * @param ordenarFecha					Consulta MySQL para ordenar segun fecha
	 * @param orden							Valor que indica el orden en el que se debe ordenar
	 * @return resul						Hashtable con las filas de la tabla que cumplen la condición
	 */
	public static Hashtable<String,String> mostrarAnuncioGeneral (String mostrarAnuncioGeneral, String ordenarPropietario, String ordenarFecha, Integer orden) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
			ResultSet rs;
			if(orden == 1)
			{
				rs = stmt.executeQuery(mostrarAnuncioGeneral + ordenarPropietario);
				
			}
			else
			{
				rs = stmt.executeQuery(mostrarAnuncioGeneral + ordenarFecha);
			}
		    while (rs.next()) {
		    	String ID = rs.getString("ID");
		        String titulo = rs.getString("Titulo");
		        String cuerpo = rs.getString("Cuerpo");
		        String propietario = rs.getString("Propietario");
		        String estado = rs.getString("Estado");
		        String tipo = rs.getString("Tipo");
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		        String fecha = sdf.format(rs.getDate("Fecha"));
		        
		        resul = new Hashtable<String,String>();
		        resul.put("ID", ID);
		        resul.put("Titulo", titulo);
		        resul.put("Cuerpo", cuerpo);
		        resul.put("Fecha", fecha);
		        resul.put("Propietario", propietario);
		        resul.put("Estado", estado);
		        resul.put("Tipo", tipo);
		        System.out.println("ID: " + ID + " , Titulo: " + titulo + " , Cuerpo: " + cuerpo + 
		        					" , Fecha: " + fecha  + " , Propietario: " + propietario + " , Estado: " + estado + " , Tipo: " + tipo);
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
	 * @param mostrarAnuncioTematico		Consulta MySQL para mostrar los anuncios tematicos publicados
	 * @param ordenarPropietario			Consulta MySQL para ordenar segun usuario propietario
	 * @param ordenarFecha					Consulta MySQL para ordenar segun fecha
	 * @param orden							Valor que indica el orden en el que se debe ordenar
	 * @return resul						Hashtable con las filas de la tabla que cumplen la condición
	 */
	public static Hashtable<String,String> mostrarAnuncioTematico (String mostrarAnuncioTematico, String ordenarPropietario, String ordenarFecha, Integer orden, UserBean user) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
			ResultSet rs;
			if(orden == 1)
			{
				rs = stmt.executeQuery(mostrarAnuncioTematico + "'" + user.getEmail() + "'" + ordenarPropietario);
			}
			else
			{
				rs = stmt.executeQuery(mostrarAnuncioTematico + "'" + user.getEmail() + "'" + ordenarFecha);
			}
		    while (rs.next()) {
		    	String ID = rs.getString("ID");
		        String titulo = rs.getString("Titulo");
		        String cuerpo = rs.getString("Cuerpo");
		        String propietario = rs.getString("Propietario");
		        String estado = rs.getString("Estado");
		        String tipo = rs.getString("Tipo");
		        String tag = rs.getString("IdTag");
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		        String fecha = sdf.format(rs.getDate("Fecha"));
		        
		        resul = new Hashtable<String,String>();
		        resul.put("ID", ID);
		        resul.put("Titulo", titulo);
		        resul.put("Cuerpo", cuerpo);
		        resul.put("Fecha", fecha);
		        resul.put("Propietario", propietario);
		        resul.put("Estado", estado);
		        resul.put("Tipo", tipo);
		        resul.put("IdTag", tag);
		        System.out.println("ID: " + ID + " , Titulo: " + titulo + " , Cuerpo: " + cuerpo + 
		        					" , Fecha: " + fecha  + " , Propietario: " + propietario + " , Estado: " + estado + " , Tipo: " + tipo + ", Tag: " + tag);
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
	 * @param mostrarAnuncioIndividualizado		Consulta MySQL para mostrar los anuncios individualizados publicados
	 * @param ordenarPropietario				Consulta MySQL para ordenar segun usuario propietario
	 * @param ordenarFecha						Consulta MySQL para ordenar segun fecha
	 * @param orden								Valor que indica el orden en el que se debe ordenar
	 * @param user								Envoltorio usuario donde se almacena el correo del destinatario del anuncio
	 * @return resul							Hashtable con las filas de la tabla que cumplen la condición
	 */
	public static Hashtable<String,String> mostrarAnuncioIndividualizado (String mostrarAnuncioIndividualizado, String ordenarPropietario, String ordenarFecha, Integer orden, UserBean user) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
			ResultSet rs;
			if(orden == 1)
			{
				rs = stmt.executeQuery(mostrarAnuncioIndividualizado + "'" + user.getEmail() + "'" + ordenarPropietario);
			}
			else
			{
				rs = stmt.executeQuery(mostrarAnuncioIndividualizado + "'" + user.getEmail() + "'" + ordenarFecha);
			}
		    while (rs.next()) {
		    	String ID = rs.getString("ID");
		        String titulo = rs.getString("Titulo");
		        String cuerpo = rs.getString("Cuerpo");
		        String propietario = rs.getString("Propietario");
		        String estado = rs.getString("Estado");
		        String tipo = rs.getString("Tipo");
		        String destinatario = rs.getString("Email");
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		        String fecha = sdf.format(rs.getDate("Fecha"));
		        
		        resul = new Hashtable<String,String>();
		        resul.put("ID", ID);
		        resul.put("Titulo", titulo);
		        resul.put("Cuerpo", cuerpo);
		        resul.put("Fecha", fecha);
		        resul.put("Propietario", propietario);
		        resul.put("Estado", estado);
		        resul.put("Tipo", tipo);
		        resul.put("Email", destinatario);
		        System.out.println("ID: " + ID + " , Titulo: " + titulo + " , Cuerpo: " + cuerpo + 
		        					" , Fecha: " + fecha  + " , Propietario: " + propietario + " , Estado: " + estado + " , Tipo: " + tipo + ", Destinatario: " + destinatario);
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
	 * @param mostrarAnuncioFlash			Consulta MySQL para mostrar los anuncios flash publicados
	 * @param ordenarPropietario			Consulta MySQL para ordenar segun usuario propietario
	 * @param ordenarFecha					Consulta MySQL para ordenar segun fecha
	 * @param orden							Valor que indica el orden en el que se debe ordenar
	 * @return resul						Hashtable con las filas de la tabla que cumplen la condición
	 */
	public static Hashtable<String,String> mostrarAnuncioFlash (String mostrarAnuncioFlash, String ordenarPropietario, String ordenarFecha, Integer orden) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
			ResultSet rs;
			if(orden == 1)
			{
				rs = stmt.executeQuery(mostrarAnuncioFlash + ordenarPropietario);
				
			}
			else
			{
				rs = stmt.executeQuery(mostrarAnuncioFlash + ordenarFecha);
			}
		    while (rs.next()) {
		    	String ID = rs.getString("ID");
		        String titulo = rs.getString("Titulo");
		        String cuerpo = rs.getString("Cuerpo");
		        String propietario = rs.getString("Propietario");
		        String estado = rs.getString("Estado");
		        String tipo = rs.getString("Tipo");
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		        String fecha = sdf.format(rs.getDate("Fecha"));
		        String inicio = sdf.format(rs.getDate("Inicio"));
		        String fin = sdf.format(rs.getDate("Fin"));
		        
		        resul = new Hashtable<String,String>();
		        resul.put("ID", ID);
		        resul.put("Titulo", titulo);
		        resul.put("Cuerpo", cuerpo);
		        resul.put("Fecha", fecha);
		        resul.put("Propietario", propietario);
		        resul.put("Estado", estado);
		        resul.put("Tipo", tipo);
		        resul.put("Inicio", inicio);
		        resul.put("Fin", fin);
		        System.out.println("ID: " + ID + " , Titulo: " + titulo + " , Cuerpo: " + cuerpo + 
		        					" , Fecha: " + fecha  + " , Propietario: " + propietario + " , Estado: " + estado + " , Tipo: " + tipo + " , Inicio: " + inicio +
		        					" , Fin: " + fin);
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
	 * @param buscarID			Consulta MySQL para buscar un anuncio por su ID
	 * @param ad				Envoltorio anuncio que contiene el ID buscado
	 * @return resul			Envoltorio anuncio que contiene la informacion del anuncio identificado por el ID buscado
	 */
	public static AdBean queryByID (String buscarID, AdBean ad) {
		Statement stmt = null; 
		AdBean resul = new AdBean();
		try {
			Connection con=getConnection();
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(buscarID + "'" + ad.getID() + "'");
		    while (rs.next()) {
		    	resul.setID(ad.getID());
		        resul.setTitulo(rs.getString("Titulo"));
		        resul.setCuerpo(rs.getString("Cuerpo"));
		        resul.setPropietario(rs.getString("Propietario")); 
		        resul.setEstado(rs.getString("Estado"));
		        resul.setTipo(rs.getString("Tipo"));
		        resul.setFecha(rs.getDate("Fecha"));
		        if(rs.getString("Tipo").equals("Flash"))
		        {
		        	resul.setInicio(rs.getDate("Inicio"));
		        	resul.setFin(rs.getDate("Fin"));
		        }
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
	 * @param buscarID			Consulta MySQL para buscar un anuncio por su propietario
	 * @param user				Envoltorio usuario que contiene el correo buscado
	 * @return resul			Hashtable con las filas de la tabla que cumplen la condición
	 */
	public static Hashtable<String,String> queryByOwner (String buscarPropietario, UserBean user) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(buscarPropietario + "'" + user.getEmail() + "'");
		    while (rs.next()) {
		    	Integer ID = rs.getInt("ID");
		    	String titulo = rs.getString("Titulo");
		    	String cuerpo = rs.getString("Cuerpo");
		    	String propietario = rs.getString("Propietario");
		    	String estado = rs.getString("Estado");
		    	String tipo = rs.getString("Tipo");
		    	
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		        String fecha = sdf.format(rs.getDate("Fecha"));
		        
		        if(rs.getString("Tipo").equals("Flash"))
		        {
		        	String inicio = sdf.format(rs.getDate("Inicio"));
		        	String fin = sdf.format(rs.getDate("Fin"));
		        	System.out.println("Inicio: " + inicio + " , Fin: " + fin + " , ");
		        }
		        System.out.println("ID: " + ID + " , Titulo: " + titulo + " , Cuerpo: " + cuerpo + 
    					" , Fecha: " + fecha  + " , Propietario: " + propietario + " , Estado: " + estado + " , Tipo: " + tipo);
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
	 * @param getLastID			Consulta MySQL que busca el ultimo ID introducido
	 * @return id				Ultimo ID introducido
	 */
	public static Integer getLastID (String getLastID) {
		Statement stmt = null; 
		Integer id = 0;
		try {
			Connection con=getConnection();
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(getLastID);
		    while (rs.next()) {
		    	id = rs.getInt("MAX(ID)");
		    }
		    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
		    if (stmt != null) 
		    	stmt.close(); 
		} catch (Exception e) {
			System.out.println(e);
		} 
		return id;
	} 
	
	/**
	 * 
	 * @param modificarAnuncio		Cadena de MySQL para modificar un anuncio en la base de datos
	 * @param ad					Envoltorio anuncio que contiene la informacion del anuncio a modificar
	 * @return status				Valor que indica cuantas filas de la tabla han sido modificadas
	 */
	public static int updateAd(String modificarAnuncio, AdBean ad)
	{
		int status=0;
		try{
			Connection con=getConnection();
			PreparedStatement ps=con.prepareStatement(modificarAnuncio);
			ps.setString(1,ad.getTitulo());
			ps.setString(2,ad.getCuerpo());
			ps.setDate(3,ad.getFecha());
			ps.setString(4,ad.getEstado());
			ps.setString(5,ad.getTipo());
			ps.setDate(6,ad.getInicio());
			ps.setDate(7,ad.getFin());
			// En este caso, 'id' va después, conforme al orden de la sentencia SQL
			ps.setInt(8,ad.getID());
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	
	/**
	 * 
	 * @param cambiarEstado			Cadena de MySQL para cambiar el estado de un anuncio en la base de datos
	 * @param ad					Envoltorio anuncio que contiene el ID del anuncio y el nuevo estado
	 * @return status				Valor que indica cuantas filas de la tabla han sido modificadas
	 */
	public static int changeStateAd(String cambiarEstado, AdBean ad)
	{
		int status=0;
		try{
			Connection con=getConnection();
			PreparedStatement ps=con.prepareStatement(cambiarEstado);
			ps.setString(1,ad.getEstado());
			// En este caso, 'id' va después, conforme al orden de la sentencia SQL
			ps.setInt(2,ad.getID());
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	
	/**
	 * 
	 * @param buscarAnuncioFecha		Consulta MySQL para buscar un anuncio por su fecha
	 * @param ad						Envoltorio anuncio que contiene la fecha del anuncio buscado
	 * @return resul					Hashtable con las filas de la tabla que cumplen la condición
	 */
	public static Hashtable<String,String> queryByDate (String buscarAnuncioFecha, AdBean ad) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(buscarAnuncioFecha + "'" + ad.getFecha() + "'");
		    while (rs.next()) {
		    	String id = rs.getString("ID");
		    	String titulo = rs.getString("Titulo");
		    	String cuerpo = rs.getString("Cuerpo");
		    	
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		        String fecha = sdf.format(rs.getDate("Fecha"));
		    	
		    	String propietario = rs.getString("Propietario");
		    	String estado = rs.getString("Estado");
		    	String tipo = rs.getString("Tipo");
		    	String inicio = null;
		    	String fin = null;
		    	if(tipo.equals("Flash"))
		    	{
		    		inicio = sdf.format(rs.getDate("Inicio"));
		    		fin = sdf.format(rs.getDate("Fin"));
		    	}
		    	
		    	resul = new Hashtable<String,String>();
		    	resul.put("ID", id);
		        resul.put("Titulo", titulo);
		        resul.put("Cuerpo", cuerpo);
		        resul.put("Fecha", fecha);
		        resul.put("Propietario", propietario); 
		        resul.put("Estado", estado);
		        resul.put("Tipo", tipo);
		        if(tipo.equals("Flash"))
		        {
		        	resul.put("Inicio", inicio);
		        	resul.put("Fin",fin);
		        	System.out.println("Inicio: " + inicio + " , Fin: " + fin + " , ");
		        }
		        System.out.println("ID: " + id + " , Titulo: " + titulo + " , Cuerpo: " + cuerpo + " , Fecha: " + fecha + " , Propietario: " + propietario
		        					+ " , Estado: " + estado + " , Tipo: " + tipo);
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
	 * @param mostrarOtrosAnuncios		Consulta MySQL que muestra los anuncios no publicados
	 * @return resul					Hashtable con las filas de la tabla que cumplen la condición
	 */
	public static Hashtable<String,String> mostrarOtrosAnuncios (String mostrarOtrosAnuncios) {
		Statement stmt = null; 
		Hashtable<String,String> resul = null;
		try {
			Connection con=getConnection();
			// En consultas, se hace uso de un Statement 
			stmt = con.createStatement();
			ResultSet rs= stmt.executeQuery(mostrarOtrosAnuncios);
		    while (rs.next()) {
		    	String ID = rs.getString("ID");
		        String titulo = rs.getString("Titulo");
		        String cuerpo = rs.getString("Cuerpo");
		        String propietario = rs.getString("Propietario");
		        String estado = rs.getString("Estado");
		        String tipo = rs.getString("Tipo");
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		        String fecha = sdf.format(rs.getDate("Fecha"));
		        
		        resul = new Hashtable<String,String>();
		        resul.put("ID", ID);
		        resul.put("Titulo", titulo);
		        resul.put("Cuerpo", cuerpo);
		        resul.put("Fecha", fecha);
		        resul.put("Propietario", propietario);
		        resul.put("Estado", estado);
		        resul.put("Tipo", tipo);
		        if(tipo.equals("Flash"))
		        {
		        	String inicio = sdf.format(rs.getDate("Inicio"));
		        	String fin = sdf.format(rs.getDate("Fin"));
		        	resul.put("Inicio", inicio);
		        	resul.put("Fin", fin);
		        	System.out.println("Inicio: " + inicio + " , Fin: " + fin + " , ");
		        }
		        System.out.println("ID: " + ID + " , Titulo: " + titulo + " , Cuerpo: " + cuerpo + 
		        					" , Fecha: " + fecha  + " , Propietario: " + propietario + " , Estado: " + estado + " , Tipo: " + tipo);
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
