<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar contraseña</title>
</head>
<body>
<h3>INTRODUZCA SU NUEVA CONTRASEÑA</h3>
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
	<form method="post" action="/GM3.1_i82gatom/MVC/controlador/modifyPasswordController.jsp">
				<label>Contraseña</label>

					
						<div class="col">
							<input class="form-control" type="password" name="password" id="password" required>
						 </div>
						 
						 <div class="col">
						 	 <button class="btn btn-primary" type="button" onclick="mostrarContrasena()">Mostrar Contraseña</button>
						</div>
						

		<br/>
		<input type = "submit" value="Confirmar datos" />
	</form>

</body>
</html>