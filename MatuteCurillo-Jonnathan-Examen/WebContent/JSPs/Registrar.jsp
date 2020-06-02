
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro</title>
</head>

<body>
	
						<h1>Formulario de Registro</h1>
						<a href="/MatuteCurillo-Jonnathan-Examen"></a>
						

			
				<form action="/MatuteCurillo-Jonnathan-Examen/ControladorRegistrar"
					method="post">
					<label for="identificacion">Cedula:</label> <input type="text"
						id="identificacion" name="cedula" value="${cedula}"
						placeholder="Ingrese cedula" maxlength="10" class="login-field" />
					<label for="nombre">Nombre:</label><input type="text" id="nombre"
						name="nombre" value="${nombre}" placeholder="Ingrese nombres"
						maxlength="48" class="login-field" />
					
					<br /> <input class="btn btn-primary btn-large btn-block"
						type="submit" name="Ingresar" value="Registrarse" />

				</form>

	<footer>
			<p>EXAMEN</p>
			
	</footer>
</body>
</html>
