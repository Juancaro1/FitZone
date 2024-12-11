<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro</title>
</head>
<body>
	<h2>Registro</h2>
	<form:form action = "/registrarUsuario" method = "POST" modelAttribute = "usuario">
		<div>
			<form:label path = "nombre">Nombre:</form:label>
			<form:input type = "text" path = "nombre"/>
			<form:errors path = "nombre"/>
			
			<form:label path = "apellido">Apellido:</form:label>
			<form:input type = "text" path = "apellido"/>
			<form:errors path = "apellido"/>
			
			<form:label path = "email">Correo:</form:label>
			<form:input type = "text" path = "email"/>
			<form:errors cssClass = "email" path = "email"/>

            <form:label path = "apellido">Apellido:</form:label>
			<form:input type = "text" path = "apellido"/>
			<form:errors path = "apellido"/>
			
			<form:label path = "clave">Contraseña:</form:label>
			<form:input type = "password" path = "clave"/>
			<form:errors path = "clave"/>

            <form:label path="genero">Genero:</form:label> 
            <form:select path="genero" multiple="false">
                <option value="Hombre">Hombre</option>
                <option value="Mujer">Mujer</option>
                <option value="Otro">Otro</option>
            </form:select> 
            <form:errors class="error" path="genero"/>
			
			<form:label path = "confirmarClave">Confirmar Contraseña:</form:label>
			<form:input type = "password" path = "confirmarClave"/>
			<form:errors path = "confirmarClave"/>
			
			<input type = "submit" value = "Registrarse"/>
		</div>
	</form:form>
</body>
</html>