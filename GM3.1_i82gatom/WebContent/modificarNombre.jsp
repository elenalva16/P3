<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar nombre</title>
</head>
<body>
<h3>INTRODUZCA SU NOMBRE Y APELLIDOS PARA QUE SEA CAMBIADO</h3>

	<form method="post" action="/GM3.1_i82gatom/MVC/controlador/modifyNameController.jsp">
				<label>Nombre</label><br>
				<label><input type="text" name = "nombreUser" required></label><br>
				<br/>
				<label>Apellidos</label><br>
				<label><input type="text" name = "apellidosUser" required></label><br>
			<br/>
		<input type = "submit" value="Confirmar datos" />
	</form>
</body>
</html>