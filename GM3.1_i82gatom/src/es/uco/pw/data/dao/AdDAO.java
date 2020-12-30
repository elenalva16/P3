package es.uco.pw.data.dao;

import com.mysql.jdbc.Connection;

import java.sql.*;

import com.connection.Conexion;
import es.uco.pw.properties.Propiedades;
import es.uco.pw.display.javabean.AdBean;

/**
 * <b><u>AnuncioDAO.java</u></b>
 * 
 * <p>Representa la clase AnuncioDAO del proyecto.</p>
 * 
 * @author Maria Garcia Torres
 * @author Elena Alvarez Sanchez
 * @version 1.0
 */
public class AdDAO {
	
	Propiedades prop = new Propiedades();
	AdBean adBean = new AdBean();
	
	/**
	 * Constructor de la clase AdDAO
	 * @param prop Es un objeto properties que contiene las lineas del fichero sql.properties
	 */
	public AdDAO(java.util.Properties prop) {
	  this.prop.setPropiedades(prop);
	}
	

	/**
	 * Método para insertar una fila en la base de datos
	 * 
	 * @param 
	 * @return Devuelve 'true' si la insercción se ha hecho correctamente y 'false' si ha habido algun problema. 
	 */
	  public boolean save(String titulo, String cuerpo, String fecha, String tipo, String propietario, String inicio, String fin){
		int status=0;
		Integer id = 0;
		try{
			Connection con=(Connection) Conexion.getConnection();
			
			//Obtenemos la línea del sql.properties con la cuál accederemos a la base de datos
			Integer num = 11;
			String guardarAnuncio = prop.propiedades(num);
			PreparedStatement ps=con.prepareStatement(guardarAnuncio);
			// El orden de los parámetros debe coincidir con las '?' del código SQL
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(prop.propiedades(14));
			while (rs.next()) {
				id = rs.getInt("MAX(ID)") + 1;
		    }
			ps.setInt(1, id);
			ps.setString(2,titulo);
			ps.setString(3,cuerpo);
			ps.setString(4,fecha);
			ps.setString(5,propietario);
			ps.setString(6, tipo);
			ps.setString(7, inicio);
			ps.setString(8, fin);
			status = ps.executeUpdate();
			
			if(status == 1) {
				return true;
			}
		}catch(Exception e){
			System.err.println("Ha habido problemas al guardar el anuncio"); 
			System.out.println(e);
		}		
		return false;
	}
	  
  /**
	 * Método para insertar una fila en la base de datos
	 * 
	 * @param 
	 * @return Devuelve 'true' si la insercción se ha hecho correctamente y 'false' si ha habido algun problema. 
	 */
	  public boolean saveTematico(int tag){
		int status=0;
		Integer id = 0;
		try{
			Connection con=(Connection) Conexion.getConnection();
			
			//Obtenemos la línea del sql.properties con la cuál accederemos a la base de datos
			int num = 29;
			String guardarAnuncio = prop.propiedades(num);
			PreparedStatement ps=con.prepareStatement(guardarAnuncio);
			// El orden de los parámetros debe coincidir con las '?' del código SQL
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(prop.propiedades(14));
			while (rs.next()) 
			{
					id = rs.getInt("MAX(ID)");
		    }
			ps.setInt(1, id);
			ps.setInt(2,tag);
			status = ps.executeUpdate();
			
			if(status == 1) {
				return true;
			}
		}catch(Exception e){
			System.err.println("Ha habido problemas al guardar el anuncio"); 
			System.out.println(e);
		}		
		return false;
	}
	  
	  /**
		 * Método para insertar una fila en la base de datos
		 * 
		 * @param 
		 * @return Devuelve 'true' si la insercción se ha hecho correctamente y 'false' si ha habido algun problema. 
		 */
		  public boolean saveIndividualizado(String destinatario){
			int status=0;
			Integer id = 0;
			try{
				Connection con=(Connection) Conexion.getConnection();
				
				//Obtenemos la línea del sql.properties con la cuál accederemos a la base de datos
				int num = 27;
				String guardarAnuncio = prop.propiedades(num);
				PreparedStatement ps=con.prepareStatement(guardarAnuncio);
				// El orden de los parámetros debe coincidir con las '?' del código SQL
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(prop.propiedades(14));
				while (rs.next()) {
					id = rs.getInt("MAX(ID)")+1;
			    }
				ps.setInt(1, id);
				ps.setString(2,destinatario);
				status = ps.executeUpdate();
				
				if(status == 1) {
					return true;
				}
			}catch(Exception e){
				System.err.println("Ha habido problemas al guardar el anuncio"); 
				System.out.println(e);
			}		
			return false;
		}
}
	