<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean  id="customerBean" scope="session" 
class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<%@ page import ="es.uco.pw.display.javabean.CustomerBean" %>

<%@ page import ="es.uco.pw.data.dao.UserDAO" %>

<%@ page errorPage="/include/errorEmail.jsp" %>   
 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<%

		int num;
		String email = request.getParameter("emailUser");
		String clave = request.getParameter("password");
		
		session.setAttribute("emailUsuario", email);
		
		String fichero = request.getServletContext().getInitParameter("fichero");
		java.io.InputStream myIO = application.getResourceAsStream(fichero);
		
		java.util.Properties prop = new java.util.Properties();
		
		prop.load(myIO);
		UserDAO co = new UserDAO(prop);
		boolean hola = co.autenticacion(email, clave);
		
		if (co.autenticacion(email, clave) == true) {

			num = 1;
			customerBean.setContador(num);
			
			response.sendRedirect("/GM3.1_i82gatom/MVC/vista/loginview.jsp");

 		}
		else{
				System.err.println("Error en el email, nos quedamos en la misma página");

				if (customerBean.getContador() == 3) {
					num = 1;
					customerBean.setContador(num);
					
					 response.sendRedirect(application.getInitParameter("url")); 
				}
				else { 
					num =  customerBean.getContador();
					num = num + 1;
					customerBean.setContador(num);
					response.sendRedirect("/GM3.1_i82gatom/login.jsp");
				 }
		}
		
%>
</body>
</html>