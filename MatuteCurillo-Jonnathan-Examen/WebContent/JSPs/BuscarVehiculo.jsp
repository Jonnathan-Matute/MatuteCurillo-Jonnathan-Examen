
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>| Buscar o listar</title>
</head>

<body>
	
					
						<a href="/MatuteCurillo-Jonnathan-Examen"></a>
						<p>Lista</p>

							<form action="busqueda"></form>
							
	
			
						<div class="container-table100">
							<form
								action="/MatuteCurillo-Jonnathan-Examen/ControladorBuscar"
								method="post" style="margin-top: 12px; margin-bottom: 12px;">
								<label for="cars" style="font-size: x-large;">Buscar
									por:</label> <select id="cars" name="opcion">
									<option value="cedula">Cedula</option>
									<option value="placa">Placa</option>

								</select> <input type="text" name="parametro" placeholder="Ingrese dato"
									id="login-name" value="${parInval}" style="margin-top: 6px;"><input
									class="boton" type="submit" name="Ingresar" value="Buscar" />
								<br>
								

							
							</form>
						

									<table>
										<p>
										<pre style="padding-bottom: 0px;">     <b>${nn}</b>${t.cedula} ${v.placa}
										</pre>
										</p>

										<thead>
											<tr>
												<th>Cedula</th>
												<th>Placa</th>
											</tr>
										</thead>

									</table>

								</div>

						
		<footer>
		
				<p>Examen</p>
				
		</footer>
	
</body>
</html>