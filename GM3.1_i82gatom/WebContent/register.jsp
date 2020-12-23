<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="RegisterBean" scope="session" class="es.uco.pw.display.javabean.RegisterBean"></jsp:useBean>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>~REGISTRARSE~</title>
</head>
<body>
<h3>~RELLENE EL FORMULARIO~</h3>
<%
		if(RegisterBean.getContador() == 1){
			%>
			<jsp:include page="/include/comprobarEmail.jsp"></jsp:include>
			<%
			int num=0;
			RegisterBean.setContador(num);
		}

%>
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
	<form method = "post" action = "/GM3.1_i82gatom/MVC/controlador/registerController.jsp">
		 
				<label>Nombre</label><br>
				<label><input type="text" name = "nombreUser" required></label><br>
				
				<br>
				
				<label>Apellidos</label><br>
				<label><input type="text" name = "apellidosUser" required></label><br>
				
				<br>
				
				<label>Fecha de nacimiento</label><br>
				<label><input type="date" name = "FechanacimientoUser" min="1900-01-01"  required></label><br>
				<label>Formato correcto: yyyy-mm-dd (año-mes-día)</label><br>
				<br>
				
				
				<label>Email</label><br>
				<label><input type="email" name = "emailUser" required></label><br>
				
				<br>
				
				<label>Clave</label><br>
				<div class="form-row">		
					<div class="col">
						<input class="form-control" type="password" name="password" id="password" required>
					</div>
									 
					<div class="col">
						<button class="btn btn-primary" type="button" onclick="mostrarContrasena()">Mostrar Contraseña</button>
					</div>
						
				</div>
				
				<br>

		<input type = "submit" value="Registrarse" />
	</form>

</body>
</html>