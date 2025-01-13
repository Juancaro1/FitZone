elemento = document.getElementById("coordenadas");

const cordx = parseFloat(elemento.getAttribute("data-cordx"));
const cordy = parseFloat(elemento.getAttribute("data-cordy"));


var map = L.map('map').setView([cordx, cordy], 12);


L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(map);


L.marker([cordx, cordy]).addTo(map);
