<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Anuncio individualizado</title>
</head>
<body>
<h3>Introduzca los datos del anuncio</h3>

	<form method="post" action="/GM3.1_i82gatom/MVC/controlador/individualAdController.jsp">
				
				<label>Email del destinatario</label><br>
				<label><input type="text" name = "desAd" required></label><br>
				
				<br>

		<input type = "submit" value="Crear Anuncio Individualizado" />
	</form>

</body>
</html>