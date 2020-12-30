<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Anuncio flash</title>
</head>
<body>
<h3>Introduzca los datos del anuncio</h3>

	<form method="post" action="/GM3.1_i82gatom/MVC/controlador/flashAdController.jsp">
				
				<label>Fecha de inicio</label><br>
				<label><input type="date" name = "beginningDate" min="1900-01-01" required></label><br>
				<label>Formato correcto: yyyy-mm-dd (año-mes-día)</label><br>
				
				<br>
				
				<label>Fecha de finalización</label><br>
				<label><input type="date" name = "endDate" min="1900-01-01" required></label><br>
				<label>Formato correcto: yyyy-mm-dd (año-mes-día)</label><br>
				
				<br>

		<input type = "submit" value="Crear Anuncio Flash" />
	</form>

</body>
</html>