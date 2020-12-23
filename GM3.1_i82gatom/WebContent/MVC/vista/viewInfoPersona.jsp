<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import ="es.uco.pw.business.user.User" %>
<%@ page import ="es.uco.pw.data.dao.UserDAO" %>
<%@ page import = "java.util.LinkedList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>Información personal del usuario</h3>
<table border="1">
<tr>
<th>Nombre</th>
<th>Apellidos</th>
<th>Fecha de nacimiento</th>
<th>Email</th>
</tr>
<%
String fichero = request.getServletContext().getInitParameter("fichero");
java.io.InputStream myIO = application.getResourceAsStream(fichero);

java.util.Properties prop = new java.util.Properties();

prop.load(myIO);
UserDAO userDAO = new UserDAO(prop);
String email = (String)session.getAttribute("emailUsuario");
LinkedList<User> lista = UserDAO.infoPersona(email);
for (int i=0;i<lista.size();i++)
{
   out.println("<tr>");
   out.println("<td align=center> "+lista.get(i).getNombre()+" </td>");
   out.println("<td align=center>"+lista.get(i).getApellidos()+"</td>");
   out.println("<td align=center>"+lista.get(i).getFechaNacimiento()+"</td>");
   out.println("<td align=center>"+lista.get(i).getEmail()+"</td>");
   out.println("</tr>");
}
%>
</table>
<br/>
<a href="/GM3.1_i82gatom/MVC/vista/loginview.jsp"><input type = "submit" value="Volver" /></a>
</body>
</html>