<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>~BIENVENIDO/A.~</title>
</head>
<body>


<%if (customerBean == null || customerBean.getEmailUser()=="") { %>
	
<h2>¡BIENVENIDO! INDIQUE LA ACCIÓN QUE QUIERE REALIZAR.</h2>

<p>Soy un usuario -> <a href="/GM3.1_i82gatom/login.jsp"><input type = "submit" value="Iniciar Sesión" /></a></p>

<p>¿No tiene una cuenta? -> <a href="/GM3.1_i82gatom/register.jsp"><input type = "submit" value="Registrate" /></a></p>

<% } else { 

response.sendRedirect("/GM3.1_i82gatom/logout.jsp");

 } %>

</body>
</html>