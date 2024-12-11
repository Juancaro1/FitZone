<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/styles.css"/>
<title>Agregar Localizacion</title>
</head>
<body>
	<h2>Agregar Localizacion</h2>
	
	<form:form action = "/guardar" method = "POST" modelAttribute = "localizacion">
		<div>
			<form:label path = "nombre">Nombre:</form:label>
			<form:input type = "text" path = "nombre"/>
			<form:errors path = "nombre"/>
			
            <form:label path = "direccion">Direccion:</form:label>
			<form:input type = "text" path = "direccion"/>
			<form:errors path = "direccion"/>
		
			<input type = "submit" value = "Agregar"/>
		</div>
	</form:form>
</body>
</html>