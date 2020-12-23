package es.uco.pw.properties;


import java.util.Properties;

import javax.servlet.ServletContext;

//AQUI CARGAREMOS LOS FICHEROS QUE SEAN NECESARIOS

public class Propiedades{

	Properties prop;
	
	/**
	 * setter 
	 * 
	 * @param fich Contiene las líneas del fichero sql.properties
	 */
	public void setPropiedades(Properties fich) {
		this.prop=fich;
	}
	
	static ServletContext context = null;
	
	/**
	 * En función del número que se le pase a está función se devolverá la cadena escrita de una forma u otra,
	 * y las cadenas contienen las líneas del fichero sql.properties. 
	 * 
	 * @param num Número que nos indica que operación se realizará con el fichero sql.porperties. 
	 * @return Devuelve una cadena con una de las líneas del fichero sql.properties. 
	 */
	public String propiedades (int num){		
		
		String cadena="";

		try {
			Properties propiedad = this.prop;

			if(num == 1) {
				cadena = propiedad.getProperty("saveContact");
			}
			else {
				if(num == 2) {
					cadena = propiedad.getProperty("queryByEmail");
				}
				else {
					if(num == 3) {
						cadena = propiedad.getProperty("updateNombre");
					}else {
						if(num == 4) {
							cadena = propiedad.getProperty("updateFechaNacimiento");
						}
						else {
							if(num == 5) {
							cadena = propiedad.getProperty("updateClave");
							}
							else {
								if(num == 6) {
									cadena = propiedad.getProperty("infoPersona");
								}
								else {
									if(num == 7) {
									cadena=propiedad.getProperty("comprobarEmail");
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error al cargar el fichero sql.properties");
			//e.printStackTrace();
		}
		
		return cadena;
	}
}