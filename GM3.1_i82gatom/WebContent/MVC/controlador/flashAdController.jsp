<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean  id="adBean" scope="request" class="es.uco.pw.display.javabean.AdBean"></jsp:useBean>
    
<%@ page import ="es.uco.pw.data.dao.AdDAO" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page errorPage="/include/errorCrearAnuncio.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crear anuncio flash</title>
</head>
<body>
<%
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		
		java.util.Properties prop = new java.util.Properties();
		
		String propertyFileName = request.getServletContext().getInitParameter("fichero");
		java.io.InputStream stream = request.getServletContext().getResourceAsStream(propertyFileName);
		
		prop.load(stream);
		AdDAO co = new AdDAO(prop);
		
		String titulo = (String)session.getAttribute("titleAd");
		String cuerpo = (String)session.getAttribute("bodyAd");
		
		String fecha = format.format(calendar.getTime());
		String propietario = (String)session.getAttribute("emailUsuario");
		
		String inicio = request.getParameter("beginningDate");
		String fin = request.getParameter("endDate");
		
		if (co.save(titulo, cuerpo, fecha, "Flash", propietario, inicio, fin)) {
			System.out.println("Se Han introducido correctamente los datos en la base de datos");
%>
			<h1>Su anuncio se encuentra editado.</h1>
			<p>VUELVE A LA PÁGINA DE INICIO</p>
			<a href="/GM3.1_i82gatom/index.jsp"> <input type = "submit" value="Volver al menú inicial" /> </a>

<%		} 
		
		else {
			System.out.println("Error al crear anuncio flash");
			throw new RuntimeException("Error al introducir los datos");
			
		}
%>
</body>
</html>