<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/styles.css"/>
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"
integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY="
crossorigin=""/>
<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"
integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo="
crossorigin=""></script>
<title>Agregar Localizacion</title>
</head>
<body>
	<h2>Agregar Localizacion</h2>
	
	<form:form action = "/guardar" method = "POST" modelAttribute = "localizacion">
		<div>
			<form:label path = "nombre">Nombre:</form:label>
			<form:input type = "text" path = "nombre"/>
			<form:errors path = "nombre"/>
			<!--probando api--> 
			
			
            <form:label path = "direccion">Direccion:</form:label>
			<form:input type = "text" path = "direccion"/>
			<form:errors path = "direccion"/>
			<span>Ubicala en el mapa: </span>
			<div id="map" style="width: 500px; height: 400px;"></div>
		
			<input type = "submit" value = "Agregar"/>
			<script>
				var map = L.map('map').setView([-36.82, -73.03], 13);
				L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
				maxZoom: 19,
				attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
				}).addTo(map);
		
				function onMapClick(e) {
					var marker = L.marker(mouseEventToLatLng(e).addTo(map));
				}
		
				map.on('click', function(ev) {
					var marker = L.marker(ev.latlng).addTo(map);
				});
				
			</script>
		</div>
	</form:form>
</body>
</html>