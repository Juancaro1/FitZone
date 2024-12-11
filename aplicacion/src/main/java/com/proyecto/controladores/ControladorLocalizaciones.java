package com.proyecto.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.proyecto.modelos.Localizacion;
import com.proyecto.servicios.ServicioLocalizaciones;
import com.proyecto.servicios.ServiciosUsuarios;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorLocalizaciones {

	@Autowired
	private ServiciosUsuarios serviciosUsuarios;
	
	@Autowired
	private ServicioLocalizaciones servicioLocalizaciones;
	
	@GetMapping("/localizaciones")
	public String localizaciones(HttpSession sesion, Model modelo) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if (idUsuario == null) {
			return "redirect:/login";
		}
		List<Localizacion> localizaciones = this.servicioLocalizaciones.obtenerTodas();
		modelo.addAttribute("localizaciones", localizaciones);
		modelo.addAttribute("usuario", this.serviciosUsuarios.obtenerPorId(idUsuario));
		return "localizaciones.jsp";
	}
	
	@GetMapping("/localizaciones/detalle/{id}")
	public String detalleLocalizacion(HttpSession sesion, Model modelo,
			@PathVariable("id") Long id) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if (idUsuario == null) {
			return "redirect:/login";
		}
		
		Localizacion localizacion = null;
		try {
			localizacion = this.servicioLocalizaciones.obtenerLocalizacionPorId(id);
			modelo.addAttribute("usuario", this.serviciosUsuarios.obtenerPorId(idUsuario));
			modelo.addAttribute("localizacion", localizacion);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "detalleLocalizacion.jsp";
	}
	
	@GetMapping("/localizaciones/agregar")
	public String agregarLocalizacion(HttpSession sesion, Model modelo) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if (idUsuario == null) {
			return "redirect:/login";
		}
		modelo.addAttribute("localizacion", new Localizacion());
		return "agregarLocalizacion.jsp";
	}
	
	@PostMapping("/guardar")
	public String guardarLocalizacion(HttpSession sesion,
			@Valid @ModelAttribute("localizacion") Localizacion localizacion,
			BindingResult validaciones) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if (idUsuario == null) {
			return "redirect:/login";
		}
		this.servicioLocalizaciones.crearLocalizacion(localizacion);
		return "redirect:/localizaciones";
	}
	
	@GetMapping("/localizaciones/editar/{id}")
	public String editarLocalizacion(HttpSession sesion, Model modelo,
			@ModelAttribute("localizacion") Localizacion localizacion,
			@PathVariable("id") Long id) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if (idUsuario == null) {
			return "redirect:/login";
		}
		modelo.addAttribute("localizacion", this.servicioLocalizaciones.obtenerLocalizacionPorId(id));
		return "editarLocalizacion.jsp";
	}
	
	@PutMapping("/editar/{id}")
	public String procesarEditarLocalizacion(HttpSession sesion, Model modelo,
			@Valid @ModelAttribute("localizacion") Localizacion localizacion,
			BindingResult validaciones) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if (idUsuario == null) {
			return "redirect:/login";
		}
	
		if(validaciones.hasErrors()) {
			modelo.addAttribute("localizacion", localizacion);
			return "editarLocalizacion.jsp";
		}
		servicioLocalizaciones.crearLocalizacion(localizacion);
		return "redirect:/localizaciones";
	}
	
	@DeleteMapping("/eliminar/{idLocalizacion}")
	public String procesarEliminarLocalizacion(HttpSession sesion,
			@PathVariable("idLocalizacion") Long idLocalizacion) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if (idUsuario == null) {
			return "redirect:/login";
		}
		servicioLocalizaciones.eliminarLocalizacion(idLocalizacion);
		return "redirect:/localizaciones";
	}
	
}
