<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Desconectando</title>
</head>
<body>

<%
//Se comprueba primero que el usuario no está logado
if (customerBean.getEmailUser()=="" || customerBean == null) { %>
<h3>El usuario ha sido desconectado</h3> <br/>
<% } else { %>
<h3>Desconectando usuario ... </h3><br/>
<%
request.getSession().removeAttribute("customerBean");
} %>
<%int num = 1;
customerBean.setContador(num);
%>
<br/>
 <a href="index.jsp"><input type = "submit" value="Volver al menú inicial" /></a>
</body>
</html>