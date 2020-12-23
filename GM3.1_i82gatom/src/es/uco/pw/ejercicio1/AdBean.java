package es.uco.pw.ejercicio1;

import java.sql.Date;

/**
 * <b><u>AdBean.java</u></b>
 * 
 * <p>Representa la clase envoltorio AdBean del proyecto.</p>
 * 
 * @author Maria Garcia Torres
 * @author Elena Alvarez Sanchez
 * @version 1.0
 */
public class AdBean {
	
	private Integer ID;
	private String titulo;
	private String cuerpo;
	private Date fecha;
	private String propietario;
	private String estado;
	private String tipo;
	private Date inicio;
	private Date fin;
	
	/**
	 * Constructor vacio
	 */
	public AdBean()
	{
		ID = 0;
		titulo = null;
		cuerpo = null;
		fecha = null;
		propietario = null;
		estado = null;
		tipo = null;
		inicio = null;
		fin = null;
	}
	
	//Getters & setters
	/**
	 * @return ID	ID del anuncio
	 */
	public Integer getID()
	{
		return ID;
	}
	
	/**
	 * @return Titulo	Titulo del anuncio
	 */
	public String getTitulo()
	{
		return titulo;
	}
	
	/**
	 * @return cuerpo	Cuerpo del anuncio
	 */
	public String getCuerpo()
	{
		return cuerpo;
	}
	
	/**
	 * @return fecha	Fecha de nacimiento del anucio
	 */
	public Date getFecha()
	{
		return fecha;
	}
	
	/**
	 * @return propietario	Propietario del anuncio
	 */
	public String getPropietario()
	{
		return propietario;
	}
	
	/**
	 * @return estado	estado del anuncio
	 */
	public String getEstado()
	{
		return estado;
	}
	
	/**
	 * @return tipo		Tipo del anuncio
	 */
	public String getTipo()
	{
		return tipo;
	}
	
	/**
	 * @return inicio	Fecha de inicio del anuncio
	 */
	public Date getInicio()
	{
		return inicio;
	}
	
	/**
	 * @return fin		Fecha de fin del anuncio
	 */
	public Date getFin()
	{
		return fin;
	}
	
	/**
	 * @param id	ID del anuncio
	 */
	public void setID(Integer id)
	{
		ID = id;
	}
	
	/**
	 * @param title		Titulo del anuncio
	 */
	public void setTitulo(String title)
	{
		titulo = title;
	}
	
	/**
	 * 
	 * @param body 		Cuerpo del anuncio
	 */
	public void setCuerpo(String body)
	{
		cuerpo = body;
	}
	
	/**
	 * 
	 * @param date		Fecha del anuncio
	 */
	public void setFecha(Date date)
	{
		fecha = date;
	}
	
	/**
	 * 
	 * @param owner		Propietario del anuncio
	 */
	public void setPropietario(String owner)
	{
		propietario = owner;
	}
	
	/**
	 * 
	 * @param state		Estado del anuncio
	 */
	public void setEstado(String state)
	{
		estado = state;
	}
	
	/**
	 * 
	 * @param type		Tipo de anuncio
	 */
	public void setTipo(String type)
	{
		tipo = type;
	}
	
	/**
	 * 
	 * @param beginning		Fecha de inicio del anuncio
	 */
	public void setInicio(Date beginning)
	{
		inicio = beginning;
	}
	
	/**
	 * 
	 * @param end			Fecha de final del anuncio
	 */
	public void setFin(Date end)
	{
		fin = end;
	}
}
