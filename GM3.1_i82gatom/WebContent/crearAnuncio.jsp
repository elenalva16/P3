<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="RegisterBean" scope="session" class="es.uco.pw.display.javabean.RegisterBean"></jsp:useBean>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>~CREAR UN ANUNCIO~</title>
</head>
<body>
<h3>~RELLENE EL FORMULARIO~</h3>

	<form method = "post" action = "/GM3.1_i82gatom/MVC/controlador/adController.jsp">
		 
				<label>Título</label><br>
				<label><input type="text" name = "titleAd" required></label><br>
				
				<br>
				
				<label>Cuerpo</label><br>
				<label><input type="text" name = "bodyAd" required></label><br>
				
				<br>
				
				<select name="typeAd">
					<option value="General">Anuncio General</option>
					<option value="Tematico">Anuncio Temático</option>
					<option value="Individualizado">Anuncio Individualizado</option>
					<option value="Flash">Anuncio Flash</option>
				</select>
								
				<br>
				

		<input type = "submit" value="Crear Anuncio" />
	</form>

</body>
</html>