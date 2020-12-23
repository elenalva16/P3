package es.uco.pw.ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.sql.Date;
import java.util.Properties;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
	
	/**
	 * <p>Opciones para poder iniciar sesion en el programa. </p>
	 * <p>Si no se consigue iniciar sesion, el usuario no podra acceder al menu de los anuncios.</p>
	 */
	static void MenuInicio () {
		System.out.println("Hola! Usted se encuentra en la zona de 'Inicio de sesion/Registrarse'");
		System.out.println("Escriba '1' si usted ya ha iniciado sesion");
		System.out.println("Escriba '2' si usted aun no se ha registrado");
		System.out.println("Escriba '3' para salir del programa");
		System.out.println("-----------------------------------------------------------");
		System.out.print("Opcion-> ");	
	}
	
	/**
	 * <p>Opciones para el menu de anuncios. </p>
	 */
	static void MenuOpciones() {
		 System.out.println("");
			System.out.println("    MENU DE OPCIONES DEL TABLON    ");
			System.out.println("-----------------------------------");
			System.out.println("1.-  Anuncio Especifico (General, Individualizado, Tematico, Flash)"); 
			System.out.println("2.-  Crea anuncio");
			System.out.println("3.-  Inscribirse a temas de su interes");
			System.out.println("4.-  Editar anuncio (los cambios que realice seran guardados automaticamente)");
			System.out.println("5.-  Publicar un anuncio");
			System.out.println("6.-  Archivar un anuncio"); 
			System.out.println("7.-  Buscar anuncio por fecha concreta");
			System.out.println("8.-  Buscar por tema de interes");
			System.out.println("9.-  Buscar por usuario destinatario");
			System.out.println("10.- Buscar por usuario propietario");
			System.out.println("11.- Ver anuncios archivados, editados o en espera");
			System.out.println("12.- Salir");
			System.out.println("");
			System.out.print("Su opcion-> ");
	 }

	public static void main(String[] args) throws ParseException {
		
		Scanner teclado = new Scanner(System.in);
		int salida = 0, opcion=0, salir=0, menu = 0;
		UserBean usuario = null;
		
		//Cargar fichero tags
		Properties prop = new Properties();
		String properties = "tags.properties";
		String tags = null;
		
		try 
		{
			BufferedReader datos = new BufferedReader(new FileReader(new File(properties)));
			prop.load(datos);

			tags = prop.getProperty("tags");
		}
		catch (FileNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }
		
		//Cargar fichero SQL
		prop = new Properties();
		properties = "sql.properties";
		
		String cambiarEstadoEspera = null;
		String cambiarEstadoArchivado = null;
		String buscarEmail = null;
		String guardarContacto = null;
		String guardarTags = null;
		String mostrarAnuncioGeneral = null;
		String mostrarAnuncioTematico = null;
		String mostrarAnuncioIndividualizado = null;
		String mostrarAnuncioFlash = null;
		String ordenarPropietario = null;
		String ordenarFecha = null;
		String ultimoID = null;
		String guardarAnuncio = null;
		String guardarDestinatario = null;
		String guardarTagsAnuncio = null;
		String buscarTag = null;
		String buscarID = null;
		String modificarAnuncio = null;
		String borrarTagsAd = null;
		String cambiarEstado = null;
		String buscarAnuncioFecha = null;
		String buscarTagAnuncio = null;
		String buscarEmailAnuncio = null;
		String buscarPropietario = null;
		String mostrarOtrosAnuncios = null;
		
		try 
		{
			BufferedReader datos = new BufferedReader(new FileReader(new File(properties)));
			prop.load(datos);

			cambiarEstadoEspera = prop.getProperty("changeStateWait");
			cambiarEstadoArchivado = prop.getProperty("changeStateArchive");
			buscarEmail = prop.getProperty("queryByEmail");
			guardarContacto = prop.getProperty("saveContact");
			guardarTags = prop.getProperty("saveTags");
			mostrarAnuncioGeneral = prop.getProperty("mostrarAnuncioGeneral");
			mostrarAnuncioTematico = prop.getProperty("mostrarAnuncioTematico");
			mostrarAnuncioIndividualizado = prop.getProperty("mostrarAnuncioIndividualizado");
			mostrarAnuncioFlash = prop.getProperty("mostrarAnuncioFlash");
			ordenarPropietario = prop.getProperty("ordenarPropietario");
			ordenarFecha = prop.getProperty("ordenarFecha");
			ultimoID = prop.getProperty("getLastID");
			guardarAnuncio = prop.getProperty("saveAd");
			guardarDestinatario = prop.getProperty("saveRecipient");
			guardarTagsAnuncio = prop.getProperty("saveTagsAd");
			buscarTag = prop.getProperty("queryByTag");
			buscarID = prop.getProperty("queryByID");
			modificarAnuncio = prop.getProperty("updateAd");
			borrarTagsAd = prop.getProperty("deleteTagsAd");
			cambiarEstado = prop.getProperty("stateAd");
			buscarAnuncioFecha = prop.getProperty("queryByDateAd");
			buscarTagAnuncio = prop.getProperty("queryByTagAd");
			buscarEmailAnuncio = prop.getProperty("queryByEmailAd");
			buscarPropietario = prop.getProperty("queryByOwner");
			mostrarOtrosAnuncios = prop.getProperty("mostrarOtrosAnuncios");
		}
		catch (FileNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }
		
		AnuncioDAO.cambiarEstadoEspera(cambiarEstadoEspera, cambiarEstadoArchivado);

		while(salida == 0) {
			
			MenuInicio();
			opcion=teclado.nextInt();
			teclado.nextLine();
			
			while((opcion<=0) || (opcion>3)){
				System.out.println("Escribe '1', '2' o '3'. Gracias.");
				opcion=teclado.nextInt();
				teclado.nextLine();
			}
			
			if(opcion == 1) {
				UserBean user = new UserBean();
				System.out.println("Introduce tus datos para iniciar sesion:");
				System.out.print("Email-> ");
				user.setEmail(teclado.nextLine());
				if(ContactoDAO.queryByMail(buscarEmail, user) != null) 
				{
					System.out.println("Ha iniciado sesion correctamente. \n");
					salida = 1;
					usuario=user;
				}
				else { System.out.println("El email introducido no se encuentra en la base de datos.\n"); }
			}
			
			if (opcion == 2) {
				UserBean user = new UserBean();
				System.out.println("Introduce tus datos para que podamos registrarte");
				System.out.println("Nombre: ");
				user.setNombre(teclado.nextLine());
				System.out.println("Apellidos: ");
				user.setApellidos(teclado.nextLine());
				
				System.out.println("Fecha de nacimiento (formato yyyy-MM-dd): ");
				String fecha = teclado.nextLine();
				user.setFechaN(Date.valueOf(fecha));
				
				System.out.println("Email: ");
				user.setEmail(teclado.nextLine());
				
				if(ContactoDAO.queryByMail(buscarEmail, user) != null) { System.out.println("Email ya registrado."); }
				else
				{
					ContactoDAO.saveContact(guardarContacto,user);
					
					System.out.println("Los tags disponibles son: ");
					
					ArrayList<String> definedTags = new ArrayList<String>();
					
					int i = 1;
					for(String subString: tags.split(",")) 
					{ 
						System.out.printf("%d: %s", i, subString);
						System.out.println("");
						definedTags.add(subString);
						i++;
					}
					
					System.out.println("Introduzca el ID de los tags en los que este interesado. Cuando termine, escriba 0");
					int linea = teclado.nextInt();
					
					while(linea != 0)
					{
						if(linea > 0 && linea < 8) 
						{ 
							InteresUsuarioDAO.saveTags(guardarTags, user, linea);
						}
						linea = teclado.nextInt();
					}
					
					System.out.println("YA HA SIDO REGISTRADO. YA PUEDE INICIAR SESION! (Introduce 1 para inciar sesion)\n");
				}
			}
			
			if (opcion == 3) { System.out.print("EL PROGRAMA SE HA CERRADO CORRECTAMENTE.\n"); System.exit(0); }
		}

		while (salir == 0) {
			MenuOpciones();
			menu=teclado.nextInt();
			
			if(menu == 1) {

				System.out.println("Escoja el tablon que desea mostrar");
				System.out.println("1.- Anuncio General");
				System.out.println("2.- Anuncio Tematico");
				System.out.println("3.- Anuncio Individualizado");
				System.out.println("4.- Anuncio Flash");
				System.out.println("5.- Para salir");
				opcion=teclado.nextInt();
				
				System.out.println("Elija una ordenacion");
				System.out.println("1.- Ordenacion por usuario propietario");
				System.out.println("2.- Odenacion por fecha ");
				System.out.print("Su opcion->");
				int orden=teclado.nextInt();
				
				while((orden<1) || (orden>2)) { 
					System.out.println("Introduzca una opcion correcta"); 
					opcion=teclado.nextInt(); 
				}
				
				while(opcion!=5) {
					if((opcion >=1) && (opcion <=4)) {
						if (opcion == 1) { 
							AnuncioDAO.mostrarAnuncioGeneral(mostrarAnuncioGeneral, ordenarPropietario, ordenarFecha, orden); 
						}
						else {
							if (opcion == 2) { 
								 AnuncioDAO.mostrarAnuncioTematico(mostrarAnuncioTematico, ordenarPropietario, ordenarFecha, orden, usuario);
							}
							else { 
								if (opcion == 3 ) { 
									AnuncioDAO.mostrarAnuncioIndividualizado(mostrarAnuncioIndividualizado, ordenarPropietario, ordenarFecha, orden, usuario);
								}
								else { 
									AnuncioDAO.mostrarAnuncioFlash(mostrarAnuncioFlash, ordenarPropietario, ordenarFecha, orden);
								}
							}
						}
					}
					else { System.out.println("Introduce una opcion valida."); }
					System.out.println("");
					System.out.println("Introduza otra opcion o teclee '5' para salir (se encuentra en la eleccion de los tipos de anuncios)");
					opcion=teclado.nextInt();
				}
				
			}
			else {
				if (menu == 2) {
					AdBean ad = new AdBean();
					UserBean destinatario = new UserBean();
					String correo;
					
					AnuncioDAO.getLastID(ultimoID);
					Integer id = AnuncioDAO.getLastID(ultimoID) + 1; 
					ad.setID(id);
					
					System.out.println("Introduzca el titulo del anuncio-> ");
					teclado.nextLine();
					ad.setTitulo(teclado.nextLine());
					ad.setPropietario(usuario.getEmail());
					
					System.out.println("Si desea que su anuncio tenga un destinatario, pulse 1. Si no pulse otro número");
					if(teclado.nextInt()==1)
					{
						System.out.println("Introduzca el email de los destinatarios. Cuando termine escriba stop");
						correo = teclado.nextLine();
						while(!correo.equalsIgnoreCase("stop"))
						{
							destinatario.setEmail(correo);
							DestinatarioDAO.saveRecipient(guardarDestinatario, ad, destinatario);
							correo=teclado.nextLine();							
						}
						ad.setTipo("Individualizado");
					}
					
					System.out.println("Introduzca el cuerpo del anuncio -> ");
					ad.setCuerpo(teclado.next());
					
					System.out.println("Introduzca el estado del anuncio. El estado puede ser: Publicado, En espera, Guardado o Archivado.");
					teclado.nextLine();
					String estado = teclado.nextLine();
					Integer correcto = 0;
					while(correcto == 0)
					{
						if(estado.equals("En espera"))
						{
							ad.setEstado(estado);
							System.out.println("¿Que dia desea que se publique su anuncio? Formato yyyy-MM-dd");
							String fechaInicio = teclado.nextLine();
							ad.setInicio(Date.valueOf(fechaInicio));
							System.out.println("¿Que dia desea que se archive su anuncio? Formato yyyy-MM-dd");
							String fechaFin = teclado.nextLine();
							ad.setFin(Date.valueOf(fechaFin));
							
							Calendar calendar = Calendar.getInstance();
							java.sql.Date sqlFecha = new java.sql.Date(calendar.getTime().getTime());
							ad.setFecha(sqlFecha);
							
							ad.setTipo("Flash");
							correcto = 1;
						}
						else if(estado.equals("Publicado") || estado.equals("Guardado") || estado.equals("Archivado"))
						{
							Calendar calendar = Calendar.getInstance();
							java.sql.Date sqlFecha = new java.sql.Date(calendar.getTime().getTime());
							ad.setEstado(estado);
							ad.setFecha(sqlFecha);
							correcto = 1;
						}
						else
						{
							System.out.println("Introduzca un estado valido");
							estado = teclado.nextLine();
						}
					}
					
					while(ad.getTipo()==null)
					{
						System.out.println("Indique que tipo de anuncio es: General o Tematico");
						String tipo = teclado.nextLine();
						if(tipo.equals("General"))
						{
							ad.setTipo(tipo);
						}
						else if(tipo.equals("Tematico"))
						{
							ad.setTipo(tipo);
							System.out.println("Los temas disponibles son: ");
							
							ArrayList<String> definedTags = new ArrayList<String>();
							
							int i = 1;
							for(String subString: tags.split(",")) 
							{ 
								System.out.printf("%d: %s", i, subString);
								System.out.println("");
								definedTags.add(subString);
								i++;
							}
							
							System.out.println("Introduzca el ID de los temas del anuncio. Cuando termine, escriba 0");
							int linea = teclado.nextInt();
							
							while(linea != 0)
							{
								if(linea > 0 && linea < 8) 
								{ 
									InteresAnuncioDAO.saveTags(guardarTagsAnuncio, ad, linea);
								}
								linea = teclado.nextInt();
							}
						}
						else
						{
							System.out.println("Introduzca un tipo valido");
						}
					}
					AnuncioDAO.saveAd(guardarAnuncio, ad);
				}
				else {
					if (menu == 3 ) {
						System.out.println("Los tags disponibles son: ");
						
						ArrayList<String> definedTags = new ArrayList<String>();
						
						int i = 1;
						for(String subString: tags.split(",")) 
						{ 
							System.out.printf("%d: %s", i, subString);
							System.out.println("");
							definedTags.add(subString);
							i++;
						}
						
						System.out.println("Introduzca el ID de los tags en los que este interesado. Cuando termine, escriba 0");
						int linea = teclado.nextInt();
						Hashtable<String,String> resul = null;
						
						while(linea != 0)
						{
							if(linea > 0 && linea < 8) 
							{ 
								resul = InteresUsuarioDAO.queryByTag(buscarTag, linea);
								if (resul!=null)
								{
									if(resul.get("Email").equals(usuario.getEmail()))
									{
											System.out.println("Ya esta suscrito a ese tema");
									}
								}
								else
								{
									InteresUsuarioDAO.saveTags(guardarTags, usuario, linea);
									System.out.println("Suscrito correctamente");
								}
							}
							linea = teclado.nextInt();
						}
					}
					else {
						if(menu == 4) 
						{
							String correo = null;
							UserBean destinatario = new UserBean();
							AdBean ad = new AdBean();
							
							System.out.println("Introduzca el ID del anuncio que desea editar");
							ad.setID(teclado.nextInt());
							ad = AnuncioDAO.queryByID(buscarID, ad);
							
							if(!ad.getPropietario().equals(usuario.getEmail()))
							{
								System.out.println("Debe ser propietario de un anuncio para editarlo");
							}
							else
							{
								System.out.println("¿Desea editar el titulo? Escriba 1(Si)/0(No)");
								if(teclado.nextInt()==1)
								{
									System.out.println("Introduzca el nuevo titulo del anuncio-> ");
									teclado.nextLine();
									ad.setTitulo(teclado.nextLine());
								}
								
								System.out.println("Si desea que su anuncio tenga un destinatario, pulse 1. Si no pulse otro número");
								if(teclado.nextInt()==1)
								{
									InteresAnuncioDAO.deleteTagsAd(borrarTagsAd, ad);
									System.out.println("Introduzca el email de los destinatarios. Cuando termine escriba stop");
									correo = teclado.nextLine();
									while(!correo.equalsIgnoreCase("stop"))
									{
										destinatario.setEmail(correo);
										DestinatarioDAO.saveRecipient(guardarDestinatario, ad, destinatario);
										correo=teclado.nextLine();							
									}
									ad.setTipo("Individualizado");
								}
								
								System.out.println("¿Desea editar el cuerpo? Escriba 1(Si)/0(No)");
								if(teclado.nextInt()==1)
								{
									System.out.println("Introduzca el nuevo cuerpo del anuncio -> ");
									ad.setCuerpo(teclado.next());
								}
								
								System.out.println("¿Desea editar el estado del anuncio? Escriba 1(Si)/0(No)");
								teclado.nextLine();
								if(teclado.nextInt()==1)
								{
									System.out.println("Introduzca el estado del anuncio. El estado puede ser: Publicado, En espera, Guardado o Archivado.");
									teclado.nextLine();
									String estado = teclado.nextLine();
									Integer correcto = 0;
									while(correcto == 0)
									{
										if(estado.equals("En espera"))
										{
											InteresAnuncioDAO.deleteTagsAd(borrarTagsAd, ad);
											ad.setEstado(estado);
											System.out.println("¿Que dia desea que se publique su anuncio? Formato yyyy-MM-dd");
											String fechaInicio = teclado.nextLine();
											ad.setInicio(Date.valueOf(fechaInicio));
											System.out.println("¿Que dia desea que se archive su anuncio? Formato yyyy-MM-dd");
											String fechaFin = teclado.nextLine();
											ad.setFin(Date.valueOf(fechaFin));
											
											Calendar calendar = Calendar.getInstance();
											java.sql.Date sqlFecha = new java.sql.Date(calendar.getTime().getTime());
											ad.setFecha(sqlFecha);
											
											ad.setTipo("Flash");
											correcto = 1;
										}
										else if(estado.equals("Publicado") || estado.equals("Guardado") || estado.equals("Archivado"))
										{
											Calendar calendar = Calendar.getInstance();
											java.sql.Date sqlFecha = new java.sql.Date(calendar.getTime().getTime());
											ad.setEstado(estado);
											ad.setFecha(sqlFecha);
											correcto = 1;
										}
										else
										{
											System.out.println("Introduzca un estado valido");
											estado = teclado.nextLine();
										}
									}
								}
							
								System.out.println("¿Desea editar el tipo de anuncio? Escriba 1(Si)/0(No)");
								if(teclado.nextInt()==1)
								{
									System.out.println("Indique que tipo de anuncio es: General o Tematico");
									teclado.nextLine();
									String tipo = teclado.nextLine();
									if(tipo.equals("General"))
									{
										ad.setTipo(tipo);
										InteresAnuncioDAO.deleteTagsAd(borrarTagsAd, ad);
									}
									else if(tipo.equals("Tematico"))
									{
										ad.setTipo(tipo);
										System.out.println("Los temas disponibles son: ");
										
										ArrayList<String> definedTags = new ArrayList<String>();
										
										int i = 1;
										for(String subString: tags.split(",")) 
										{ 
											System.out.printf("%d: %s", i, subString);
											System.out.println("");
											definedTags.add(subString);
											i++;
										}
										
										System.out.println("Introduzca el ID de los temas del anuncio. Cuando termine, escriba 0");
										int linea = teclado.nextInt();
										
										while(linea != 0)
										{
											if(linea > 0 && linea < 8) 
											{ 
												InteresAnuncioDAO.saveTags(guardarTagsAnuncio, ad, linea);
											}
											linea = teclado.nextInt();
										}
									}
									else
									{
										System.out.println("Introduzca un tipo valido");
									}
								}
								AnuncioDAO.updateAd(modificarAnuncio, ad);
								System.out.println("Anuncio modificado");
							}
						}
						else {
							if (menu == 5) {
								AdBean ad = new AdBean();
								System.out.println("Introduzca el ID del anuncio que desea publicar");
								ad.setID(teclado.nextInt());
								ad = AnuncioDAO.queryByID(buscarID, ad);
								if(!ad.getPropietario().equals(usuario.getEmail()))
								{
									System.out.println("Debe ser propietario de un anuncio para publicarlo");
								}
								else
								{
									if(ad.getEstado().equals("Publicado"))
									{
										System.out.println("Este anuncio ya esta publicado");
									}
									else
									{
										ad.setEstado("Publicado");
										AnuncioDAO.changeStateAd(cambiarEstado, ad);
										System.out.println("Anuncio publicado");
									}
								}
							}
							else {
								if (menu == 6) {
									AdBean ad = new AdBean();
									System.out.println("Introduzca el ID del anuncio que desea archivar");
									ad.setID(teclado.nextInt());
									ad = AnuncioDAO.queryByID(buscarID, ad);
									if(!ad.getPropietario().equals(usuario.getEmail()))
									{
										System.out.println("Debe ser propietario de un anuncio para archivarlo");
									}
									else
									{
										if(ad.getEstado().equals("Archivado"))
										{
											System.out.println("Este anuncio ya esta archivado");
										}
										else
										{
											ad.setEstado("Archivado");
											AnuncioDAO.changeStateAd(cambiarEstado, ad);
											System.out.println("Anuncio archivado");
										}
									}
								}
								else {
									if (menu == 7) {
										AdBean ad = new AdBean();
										System.out.println("Introduzca la fecha que quiere buscar (formato yyyy-MM-dd)");
										teclado.nextLine();
										String fecha = teclado.nextLine();
										ad.setFecha(Date.valueOf(fecha));
										if(AnuncioDAO.queryByDate(buscarAnuncioFecha, ad)==null)
										{
											System.out.println("No hay anuncios publicados el " + fecha);
										}
									}
									else {
										if(menu == 8) {
											AdBean ad = new AdBean();
											int i = 1;
											for(String subString: tags.split(",")) 
											{ 
												System.out.printf("%d: %s", i, subString);
												System.out.println("");
												i++;
											}
											
											System.out.println("Introduzca el ID del tag que quiere buscar");
											Integer tag = teclado.nextInt();
											
											for(int j = 0; j < InteresAnuncioDAO.queryByTag(buscarTagAnuncio, tag).size(); j++)
											{
												ad.setID(InteresAnuncioDAO.queryByTag(buscarTagAnuncio, tag).get(j));
												AdBean resul = AnuncioDAO.queryByID(buscarID, ad);
												if (resul==null)
													System.out.println("No hay anuncios");
												else 
												{
													System.out.println("ID: " + resul.getID() + " , Titulo: " + resul.getTitulo() + " , Cuerpo: " + resul.getCuerpo() + 
															" , Fecha: " + resul.getFecha() + " , Propietario: " + resul.getPropietario() + " , Estado: " + resul.getEstado() + 
															" , Tipo:  " + resul.getTipo());
													if(resul.getTipo().equals("Flash"))
													{
														System.out.println("Inicio: " + resul.getInicio() + " , Fin: " + resul.getFin());
													}
												}
											}
										}
										else {
											if(menu == 9) {
												UserBean user = new UserBean();
												AdBean resul = new AdBean();
												AdBean ad = new AdBean();
												System.out.println("Introduzca el email del usuario destinatario que quiere buscar");
												teclado.nextLine();
												user.setEmail(teclado.nextLine());
												
												for(int i = 0; i < DestinatarioDAO.queryByEmail(buscarEmailAnuncio, user).size(); i++)
												{
													ad.setID(DestinatarioDAO.queryByEmail(buscarEmailAnuncio, user).get(i));
													resul = AnuncioDAO.queryByID(buscarID, ad);
													if (resul==null)
														System.out.println("No hay anuncios");
													else 
													{
														System.out.println("ID: " + resul.getID() + " , Titulo: " + resul.getTitulo() + " , Cuerpo: " + resul.getCuerpo() + 
																" , Fecha: " + resul.getFecha() + " , Propietario: " + resul.getPropietario() + " , Estado: " + resul.getEstado() + 
																" , Tipo:  " + resul.getTipo() + " , Destinatario: " + user.getEmail());
													}
												}
											}
											else {
												if(menu == 10) {
													UserBean user = new UserBean();
													System.out.println("Introduzca el email del usuario propietario que quiere buscar");
													teclado.nextLine();
													user.setEmail(teclado.nextLine());
													if(AnuncioDAO.queryByOwner(buscarPropietario, user)==null)
													{
														System.out.println("No hay anuncios publicados por el usuario " + user.getEmail());
													}
												}
												else {
													if (menu == 11) { 
														if(AnuncioDAO.mostrarOtrosAnuncios(mostrarOtrosAnuncios)==null)
														{
															System.out.println("No hay anuncios");
														}
													}
													else 
													{ 
														teclado.close();
														salir = 1; 
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
