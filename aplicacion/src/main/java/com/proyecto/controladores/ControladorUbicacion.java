package com.patricio.controladores;

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

import com.patricio.modelos.Ubicacion;
import com.patricio.servicios.ServicioUbicacion;
import com.patricio.servicios.ServicioUsuarios;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorPinturas {

	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@Autowired
	private ServicioUbicacion ServicioUbicacion;
	
	@GetMapping("/ubicaciones")
	public String ubicaciones(HttpSession sesion, Model modelo) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if (idUsuario == null) {
			return "redirect:/login";
		}
		List<Ubicacion> ubicaciones = this.servicioUbicacion.obtenerTodas();
		modelo.addAttribute("ubicaciones", ubicaciones);
		modelo.addAttribute("usuario", this.servicioUsuarios.obtenerUsuarioPorId(idUsuario));
		return "ubicaciones.jsp";
	}
	
	@GetMapping("/ubicaciones/detalle/{id}")
	public String detalleUbicacion(HttpSession sesion, Model modelo,
			@PathVariable("id") Long id) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if (idUsuario == null) {
			return "redirect:/login";
		}
		
		Ubicacion ubicacion = null;
		try {
			ubicacion = this.servicioubicacions.obtenerUbicacionPorId(id);
			modelo.addAttribute("usuario", this.servicioUsuarios.obtenerUsuarioPorId(idUsuario));
			modelo.addAttribute("ubicacion", ubicacion);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "detalleUbicacion.jsp";
	}
	
	@GetMapping("/ubicaciones/agregar")
	public String agregarUbicacion(HttpSession sesion, Model modelo) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if (idUsuario == null) {
			return "redirect:/login";
		}
		modelo.addAttribute("ubicacion", new Ubicacion());
		return "agregarUbicacion.jsp";
	}
	
	@PostMapping("/guardar")
	public String guardarUbicacion(HttpSession sesion,
			@Valid @ModelAttribute("ubicacion") Ubicacion ubicacion,
			BindingResult validaciones) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if (idUsuario == null) {
			return "redirect:/regloginister";
		}
		this.servicioUbicacion.crearUbicacion(ubicacion);
		return "redirect:/ubicaciones";
	}
	
	@GetMapping("/pinturas/editar/{id}")
	public String editarUbicacion(HttpSession sesion, Model modelo,
			@ModelAttribute("ubicacion") Ubicacion ubicacion,
			@PathVariable("id") Long id) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if (idUsuario == null) {
			return "redirect:/login";
		}
		modelo.addAttribute("ubicacion", this.servicioUbicacion.obtenerUbicacionPorId(id));
		return "editarUbicacion.jsp";
	}
	
	@PutMapping("/editar/{id}")
	public String procesarEditarPintura(HttpSession sesion, Model modelo,
			@Valid @ModelAttribute("ubicacion") Ubicacion ubicacion,
			BindingResult validaciones) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if (idUsuario == null) {
			return "redirect:/login";
		}
	
		if(validaciones.hasErrors()) {
			modelo.addAttribute("ubicacion", ubicacion);
			return "editarUbicacion.jsp";
		}
		servicioUbicacion.agregarUbicacion(ubicacion);
		return "redirect:/ubicaciones";
	}
	
	@DeleteMapping("/eliminar/{idUbicacion}")
	public String procesarEliminarPintura(HttpSession sesion,
			@PathVariable("idUbicacion") Long idPintura) {
		Long idUsuario = (Long) sesion.getAttribute("idUsuario");
		if (idUsuario == null) {
			return "redirect:/login";
		}
		servicioPinturas.eliminar(idUbicacion);
		return "redirect:/ubicaciones";
	}
	
}
