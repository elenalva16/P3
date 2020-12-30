package es.uco.pw.display.javabean;
import java.util.Date;

public class AdBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String titleAd = "";
	private String bodyAd = "";
	private Date dateAd;
	private String typeAd = "";
	private String ownerAd = "";
	private Date begAd;
	private Date endAd;
	
	private String getTitulo()
	{
		return titleAd;
	}
	
	private void setTitulo(String titleAd)
	{
		this.titleAd = titleAd;
	}
	
	private String getCuerpo()
	{
		return bodyAd;
	}
	
	private void setCuerpo(String bodyAd)
	{
		this.bodyAd = bodyAd;
	}
	
	private Date getFecha()
	{
		return dateAd;
	}
	
	private void setFecha(Date dateAd)
	{
		this.dateAd = dateAd;
	}
	
	private String getTipo()
	{
		return typeAd;
	}
	
	private void setTipo(String typeAd)
	{
		this.typeAd = typeAd;
	}
	
	private String getPropietario()
	{
		return ownerAd;
	}
	
	private void setPropietario(String ownerAd)
	{
		this.ownerAd = ownerAd;
	}
	
	private Date getInicio()
	{
		return begAd;
	}
	
	private void setInicio(Date begAd)
	{
		this.begAd = begAd;
	}
	
	private Date getFin()
	{
		return endAd;
	}
	
	private void setFin(Date endAd)
	{
		this.endAd = endAd;
	}
}