var map = L.map('map').setView([-36.82, -73.03], 13);

// Cargar el mapa de OpenStreetMap
L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(map);

// Variable para almacenar el marcador actual
var currentMarker = null;

// Función que realiza la solicitud a la API de Nominatim
function getAddress(lat, lon) {
    var url = `https://nominatim.openstreetmap.org/reverse?lat=${lat}&lon=${lon}&format=json&addressdetails=1`;

    // Realizar la solicitud fetch a Nominatim
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error("Error en la solicitud de la API de Nominatim");
            }
            return response.json();
        })
        .then(data => {
            // Obtener la dirección
            const road = data.address.road || "No disponible";
            const city = data.address.city || "No disponible";
            const country = data.address.country || "No disponible";
            
            // Si ya hay un marcador, lo eliminamos
            if (currentMarker) {
                map.removeLayer(currentMarker);
            }

            // Crear un nuevo marcador en la ubicación seleccionada
            currentMarker = L.marker([lat, lon]).addTo(map);
            
            // Mostrar la dirección en el mapa o en el navegador
            var popupContent = `<p><strong>Calle:</strong> ${road}</p>
                                <p><strong>Ciudad:</strong> ${city}</p>
                                <p><strong>País:</strong> ${country}</p>`;
            currentMarker.bindPopup(popupContent).openPopup();

            // Actualizar el campo de dirección en el formulario con la dirección obtenida
            document.getElementById('direccion').value = `${road}, ${city}, ${country}`;
        })
        .catch(error => console.error("Error al obtener la dirección: " + error));
}

// Función que se llama al hacer clic en el mapa
map.on('click', function(ev) {
    var lat = ev.latlng.lat;
    var lon = ev.latlng.lng;
    
    // Llamar a la función para obtener la dirección y actualizar el marcador
    getAddress(lat, lon);
});

// Función para eliminar el marcador al hacer clic sobre él
map.on('popupclose', function() {
    if (currentMarker) {
        map.removeLayer(currentMarker);
    }
});
