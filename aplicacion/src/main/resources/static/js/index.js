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