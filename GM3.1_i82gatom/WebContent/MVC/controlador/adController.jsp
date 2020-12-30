<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import ="es.uco.pw.data.dao.AdDAO" %>

<%@ page errorPage="/include/errorCrearAnuncio.jsp" %>

<jsp:useBean  id="AdBean" scope="session" 
class="es.uco.pw.display.javabean.AdBean"></jsp:useBean>

<!DOCTYPE html>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

		String titulo = request.getParameter("titleAd");
		String cuerpo = request.getParameter("bodyAd");
		
		session.setAttribute("titleAd",titulo);
		session.setAttribute("bodyAd",cuerpo);
		
		String fecha = format.format(calendar.getTime());
		
		String tipo = request.getParameter("typeAd");
		
		String propietario = (String)session.getAttribute("emailUsuario");
		
		String inicio = null;
		String fin = null;
		
		if(tipo.equals("Flash"))
		{
			response.sendRedirect("/GM3.1_i82gatom/crearAnuncioFlash.jsp");
		}
		
		if(tipo.equals("Tematico"))
		{
			response.sendRedirect("/GM3.1_i82gatom/crearAnuncioTematico.jsp");
		}
		
		if(tipo.equals("Individualizado"))
		{
			response.sendRedirect("/GM3.1_i82gatom/crearAnuncioIndividualizado.jsp");
		}
		
		if(tipo.equals("General"))
		{
			if (co.save(titulo, cuerpo, fecha, tipo, propietario, inicio, fin)) {
				System.out.println("Se Han introducido correctamente los datos en la base de datos");
	%>
				<h1>Su anuncio se encuentra editado.</h1>
				<p>VUELVE A LA PÁGINA DE INICIO</p>

	<%		} 
			else {
				System.out.println("Error al guardar anuncio");
				throw new RuntimeException("Error al introducir los datos");
			}
		}
	
		%>
		<a href="/GM3.1_i82gatom/index.jsp"> <input type = "submit" value="Volver al menú inicial" /> </a>
		<%
%>
</body>
</html>