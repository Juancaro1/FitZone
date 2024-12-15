<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro</title>
</head>
<body>
	<h2>Registro</h2>
	<form:form action="/register" method="POST" modelAttribute="usuario">
		<nav>
			<ul>
				<li><a href="/login">Iniciar Sesión</a></ul>
			</ul>
		</nav>
		
		<form:label path="nombre">Nombre:</form:label>
		<form:input path="nombre" type="text" />
		<form:errors path="nombre" />
		
		<form:label path="apellido">Apellido:</form:label>
		<form:input path="apellido" type="text" />
		<form:errors path="apellido" />
		
		<form:label path="email">Email:</form:label>
		<form:input path="email" type="text" />
		<form:errors path="email" />

		<form:label path="genero">Genero:</form:label> 
		<form:select path="genero" multiple="false">
			<option value="Hombre">Hombre</option>
			<option value="Mujer">Mujer</option>
			<option value="Otro">Prefiero no decirlo</option>
		</form:select> 
		<form:errors path="genero"/>
		
		<form:label path="clave">Contraseña:</form:label>
		<form:input path="clave" type="password" />
		<form:errors path="clave" />
		
		<form:label path="confirmarClave">Confirmar Contraseña:</form:label>
		<form:input path="confirmarClave" type="password" />
		<form:errors path="confirmarClave" />
		
		<input type="submit" value="Registrase" />
	</form:form>
</body>
</html>