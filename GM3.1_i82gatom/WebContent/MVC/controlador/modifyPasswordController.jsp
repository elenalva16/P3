<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean  id="customerBean" scope="request" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
    
<%@ page import ="es.uco.pw.data.dao.UserDAO" %>

<%@ page errorPage="/include/errorAlModificar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificación de la contraseñae</title>
</head>
<body>
<%
		String clave = request.getParameter("password");
		String email = (String)session.getAttribute("emailUsuario");
		
		String fichero = request.getServletContext().getInitParameter("fichero");
		java.io.InputStream myIO = application.getResourceAsStream(fichero);
		
		java.util.Properties prop = new java.util.Properties();
		
		prop.load(myIO);
		UserDAO co = new UserDAO(prop);
		
		boolean hola = co.updateClave(clave, email);
		
		if (co.updateClave(clave, email) == true) {
			response.sendRedirect("/GM3.1_i82gatom/MVC/vista/correcto.jsp");
			
		}
		else {
			System.out.println("Error al actualizar los datos de la persona");
			throw new RuntimeException("Error al introducir los datos");
			
		}
%>
</body>
</html>