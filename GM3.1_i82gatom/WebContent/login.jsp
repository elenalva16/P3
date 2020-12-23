<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session"  
class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>~INICIO DE SESION~</title>
</head>
<body>
<%
int num;
if (customerBean == null || customerBean.getEmailUser()=="") {
	 num = 1;
}
if(customerBean.getContador() < 1){
	customerBean.setContador(1);
}
num = customerBean.getContador();

%>
<% if(num >= 2){ %>
	<jsp:include page="/include/errorEmail.jsp"></jsp:include>
<% } %>

<script>
  function mostrarContrasena(){
      var tipo = document.getElementById("password");
      if(tipo.type == "password"){
          tipo.type = "text";
      }else{
          tipo.type = "password";
      }
  }
</script>
	<form method="post" action="/GM3.1_i82gatom/MVC/controlador/loginController.jsp">
	<label>Email: </label><br>
	<input type="text" name="emailUser" required><br>
	<br/>
	<label>Contraseña: </label><br>
	<div class="form-row">		
		<div class="col">
			<input class="form-control" type="password" name="password" id="password" required>
		</div>
						 
		<div class="col">
			<button class="btn btn-primary" type="button" onclick="mostrarContrasena()">Mostrar Contraseña</button>
		</div>
						
	</div>

	<br/>
	<input type="submit" value="Acceder">
	</form>
 

<p> Número de intentos de incio de sesión (tendrá soló 3 intentos): <%= num %></p>


</body>
</html>