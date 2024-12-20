var map = L.map('map').setView([-36.82, -73.03], 13);
L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
maxZoom: 19,
attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(map);

let marcador = null;


document.getElementsByName("localizaciones").forEach(element => {
    const cordx = parseFloat(element.getAttribute("data-cordx")); // Suponiendo que las coordenadas están en atributos personalizados
    const cordy = parseFloat(element.getAttribute("data-cordy"));

    if (!isNaN(cordx) && !isNaN(cordy)) {
        var marker = L.marker([cordx, cordy]).addTo(map);
    }
    console.log("a");
});


function initMap() {
    // Obtener las direcciones desde los inputs
    const cordsx = document.getElementsByClassName("cordx");
    const cordsy = document.getElementsByClassName("cordy");

    for(var i=0; i < cordsx.length; i++){
        var marker = L.marker([cordsx[i].innerHTML, cordsy[i].innerHTML]).addTo(map);

    }
}

initMap();