<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Anuncio temático</title>
</head>
<body>
<h3>Introduzca los datos del anuncio</h3>

	<form method="post" action="/GM3.1_i82gatom/MVC/controlador/thematicAdController.jsp">
				
				<p>Seleccione los tags de su anuncio</p>
				<select name="tagAd">
					<option value="1">Ciencia</option>
					<option value="2">Tecnología</option>
					<option value="3">Gurus</option>
					<option value="4">Moda</option>
					<option value="5">Libros</option>
					<option value="6">Películas</option>
					<option value="7">Música</option>
				</select>
				
				<br>

		<input type = "submit" value="Crear Anuncio Temático" />
	</form>

</body>
</html>