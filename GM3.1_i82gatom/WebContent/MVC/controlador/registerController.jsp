<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import ="es.uco.pw.data.dao.UserDAO" %>

<%@ page errorPage="/include/errorRegistro.jsp" %>

<jsp:useBean  id="RegisterBean" scope="session" 
class="es.uco.pw.display.javabean.RegisterBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		String nombre = request.getParameter("nombreUser");
		String apellidos = request.getParameter("apellidosUser");
		String fechanacimiento = request.getParameter("FechanacimientoUser");
		String email = request.getParameter("emailUser");
		String clave = request.getParameter("password");

		String fichero = request.getServletContext().getInitParameter("fichero");
		java.io.InputStream myIO = application.getResourceAsStream(fichero);
		
		java.util.Properties prop = new java.util.Properties();
		
		prop.load(myIO);
		UserDAO co = new UserDAO(prop);

		if(co.comprobarEmail(email)==true){ 
			
			//Si devulve true es porque ya existe en la base de datos. 
			//Se volverán a introducir de nuevos los datos. 
			int num = 1;
			RegisterBean.setContador(num);
			response.sendRedirect("/GM3.1_i82gatom/register.jsp");
		
		}		
		if (co.save(email, nombre, apellidos, fechanacimiento, clave)) {
			System.out.println("Se Han introducido correctamente los datos en la base de datos");
%>
			<h1>¡Ha sido registrado correctamente!</h1>
			<p>VUELVE A LA PÁGINA INCIAL PARA INICIAR SESIÓN</p>
			<a href="/GM3.1_i82gatom/index.jsp"> <input type = "submit" value="Volver al menú inicial" /> </a>

<%		} 
		else {
			System.out.println("Error en el email");
			throw new RuntimeException("Error al introducir los datos");
		}
%>
</body>
</html>