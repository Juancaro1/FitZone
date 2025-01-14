
package com.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.modelos.Localizacion;
import com.proyecto.modelos.Resena;
import com.proyecto.servicios.ServicioLocalizaciones;
import com.proyecto.servicios.ServicioResena;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorResena {

    @Autowired
    private ServicioResena servicioResena;

    @Autowired
    private ServicioLocalizaciones servicioLocalizaciones;

    @PostMapping("/procesa/crear")// para procesar el crear resena
    public String procesaCrearResena(@ModelAttribute Resena resena, HttpSession sesion){
        Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if (idUsuario == null) {
            return "redirect:/login";
        }
    Localizacion localizacion = this.servicioLocalizaciones.obtenerLocalizacionPorId(resena.getLocalizacion().getId());
    
    resena.setLocalizacion(localizacion);

    this.servicioResena.crearResena(resena, idUsuario);

    return "redirect:/localizaciones/detalle/" + localizacion.getId();
    }
}
