<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar fecha nacimiento</title>
</head>
<body>
INTRODUZCA SU FECHA DE NACIMIENTO PARA QUE SEA CAMBIADA
<br/>
<br/>
	<form method="post" action="/GM3.1_i82gatom/MVC/controlador/modifyDateController.jsp">

				<label>Fecha de nacimiento</label><br>
				<label><input type="date" name = "fechaNacimientoUser" min="1900-01-01" required></label><br>
				<label>Formato correcto: yyyy-mm-dd (año-mes-día)</label><br>
		<br/>
		<input type = "submit" value="Confirmar datos" />
	</form>
</body>
</html>