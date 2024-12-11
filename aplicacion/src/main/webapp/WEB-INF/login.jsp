<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Iniciar Sesión</title>
</head>
<body>
	<h2>Login</h2>
	<form:form action = "/validarSesion" method = "POST" modelAttribute = "loginUsuario">
		<div>
			<form:label path = "email">Correo:</form:label>
			<form:input type = "text" path = "email"/>
			<form:errors path = "email"/>
			
			<form:label path = "clave">Contraseña:</form:label>
			<form:input type = "password" path = "clave"/>
			<form:errors path = "clave"/>
			
			<input type = "submit" value = "Login"/>
		</div>
	</form:form>
</body>
</html>