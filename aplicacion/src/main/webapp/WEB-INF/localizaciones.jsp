<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ubicacion</title>
</head>
<body>
	<c:forEach var = "localizacion" items = "${localizaciones}">
		<div>
			<a href = "/localizaciones/detalle/${localizacion.id}">Detalle localizaciones</a>
				<li><h3>${localizacion.titulo}</h3></li>
				<li><p>Año:</p><b>${localizacion.direccion}</b></li>
				<a href = "/localizaciones/editar/${localizacion.id}">editar localizacion</a>
			</ul>
		</div>
	</c:forEach>
</body>
</html>